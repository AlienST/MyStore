package com.store.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.FtpUtil;
import com.store.common.utils.IDUtils;
import com.store.common.utils.StoreResult;
import com.store.mapper.TbItemDescMapper;
import com.store.mapper.TbItemMapper;
import com.store.mapper.TbItemParamItemMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemDesc;
import com.store.pojo.TbItemExample;
import com.store.pojo.TbItemExample.Criteria;
import com.store.pojo.TbItemParam;
import com.store.pojo.TbItemParamItem;
import com.store.service.ItemService;
/**
 * 商品管理service
 * @author 麦苗
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${ftp_address}")
	private String address;
	@Value("${ftp_port}")
	private int port;
	@Value("${ftp_username}")
	private String username;
	@Value("${ftp_password}")
	private String password;
	@Value("${ftp_basepath}")
	private String basepath;
	@Value("${nginx_photourl}")
	private String photourl;


	@Autowired  //注入代理对象
	private TbItemMapper itemMapper;

	@Autowired  //注入
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	/**
	 * 接收商品ID，放回一个pojo对象
	 */
	@Override
	public TbItem geItemById(long itemId) {
		System.out.println("进入ItemServiceImpl-geItemById");
		//根据主键查询
		//TbItem item= itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example=new TbItemExample();
		//创建一个item查询对象
		Criteria criter= example.createCriteria();
		//添加查询条件;相当于" and id = ? " 把itemId传进去
		criter.andIdEqualTo(itemId);
		List<TbItem> itemList=itemMapper.selectByExample(example);
		if (itemList!=null&&itemList.size()>0) {
			TbItem item=itemList.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 返回返回Item表中数据(Easyui对象数据)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		EUDataGridResult dataGridResult=new EUDataGridResult();
		TbItemExample example=new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> itemList= itemMapper.selectByExample(example);
		//取出分页信息，就是取Total
		PageInfo<TbItem> pageInfo=new PageInfo<>(itemList);
		long total=pageInfo.getTotal();
		dataGridResult.setTotal(total);
		dataGridResult.setRows(itemList);
		return dataGridResult;
	}

	/**
	 * 商品图片的上传
	 * @param 
	 * @throws IOException 
	 */
	@Override
	public Map uploadItemPhoto(MultipartFile upLoadFile) {
		//返回格式 error:* message:*
		Map<String, String> map=new HashMap<>();
		try {
			String oldName=upLoadFile.getOriginalFilename();  //获取原始文件名
			System.out.println("打印原始文件名:"+oldName+"服务器地址："+address+basepath);
			String ext=oldName.substring(oldName.lastIndexOf("."))  ; //获取文件扩展名
			//UUID.randomUUID().toString() + "." + ext;  
			String newName=IDUtils.getImageName()+ext; //根据当前时间+随机数生成新的文件名
			System.out.println("新的文件名："+newName);
			//DateTime()方法由 org.joda.time.DateTime.DateTime()提供，在parent.pom中依赖
			String imageDate=new DateTime().toString("/yyyy/MM/dd");
			boolean result=	FtpUtil.uploadFile(address, port, username, password, basepath, imageDate, newName, upLoadFile.getInputStream());
			System.out.println("上传结束！");
			if (!result) {
				map.put("error", "1");
				System.out.println("图片上传失败");
				map.put("message", "图片上传失败！");
				return map;
			}
			map.put("error", "0");
			//修改项
			map.put("url", photourl+imageDate+"/"+newName);
			System.out.println("打印回调图片地址:"+photourl+imageDate+"/"+newName);
			System.out.println("图片上传成功！");
			return map;

		} catch (IOException e) {
			map.put("error", "1");
			System.out.println("图片流上传异常");
			map.put("message", "图片流上传异常！");
			return map;
		}
	}

	
	
	/**
	 * 保存添加的商品
	 * @throws Exception 
	 */
	@Override
	public StoreResult saveItem(TbItem item,String ItemDesc, String itemParams ) throws Exception {
		System.out.println("进入ItemServiceImpl-saveItem");
		long itemId=IDUtils.getItemId(); //生成商品ID
		//以下设置是提交表单中没有的
		item.setId(itemId);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		//添加商品描述信息，在同一个事务
		StoreResult result= saveItemDesc(itemId, ItemDesc);
		if (result.getStatus()!=200) {
			throw new Exception(); //如果异常了，Spring会自动回滚事务
		}		
		//添加商品规格参数
		result=saveItemParam(itemId, itemParams);
		if (result.getStatus()!=200) {
			throw new Exception(); //如果异常了，Spring会自动回滚事务
		}	
		System.out.println("打印表单ID:"+item.getId());
		//返回一个StoreResult对象(带有status=200,msg=ok,data)
		return StoreResult.ok();
	}

	
	/**
	 * 添加商品描述与保存商品同步
	 * @param itmeId 商品的id
	 * @param ItemDesc 商品的描述
	 * @return
	 */
	public StoreResult saveItemDesc(Long itemId ,String ItemDesc){
		TbItemDesc Desc=new TbItemDesc();
		Desc.setItemId(itemId);
		Desc.setItemDesc(ItemDesc);
		Desc.setCreated(new Date());
		Desc.setUpdated(new Date());
		itemDescMapper.insert(Desc);
		return StoreResult.ok();		
	}
	
	/**
	 * 添加商品的规格参数
	 * @param itmeId    商品ID
	 * @param itemParams  商品参数
	 * @return
	 */
	public StoreResult saveItemParam(Long itmeId,String itemParams){
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(itmeId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		paramItemMapper.insert(itemParamItem);
		return StoreResult.ok();		
	}

	
	
	/**
	 * 删除商品
	 * @param ids 商品的ID
	 * @return
	 */
	@Override
	public StoreResult deleteItem(long ids) {
		//deleteByPrimaryKey
		int result= itemMapper.deleteByPrimaryKey(ids);
		System.out.println("删除成功返回的int值:"+result);
		return StoreResult.ok();
	}

	
	

	/**
	 * 修改商品信息
	 * @param id 商品ID
	 * @return
	 */
	@Override
	public StoreResult updateItem(TbItem item) {
		item.setStatus((byte)1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		int result= itemMapper.updateByPrimaryKey(item);	
		System.out.println("修改成功返回的int值:"+result);
		return StoreResult.ok();
	}
	
	
	
	
	
	
	
}
