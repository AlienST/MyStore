<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 引入后台共公头部 -->
<%@ include file="../../public/head.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
	<!-- 后台首页顶部 -->
	<div data-options="region:'north',title:'商城后台管理',split:true" style="height:85px;">
	<p>欢迎您，管理员:</p>
	</div>     
    
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'${store}/itemAdd'}">新增商品</li>
	         		<li data-options="attributes:{'url':'${store}/itemList'}">查询商品</li>
	         		<li data-options="attributes:{'url':'${store}/itemParamList'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'${store}/contentCategory'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'${store}/content'}">内容管理</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	<center><h6>欢迎来到商城后台管理！</h6></center>
		    </div>
		</div>
    </div>
    
    <!-- 后台首页底部 -->
    <div data-options="region:'south',title:'我的商城2.2',split:true" style="height:70px;">
        <center><p><a href="${store}/itemSearch" target="_blank">搜索商品</a><a href="${store}/home" target="_blank">新的后台首页</a></p></center>
    </div>
    
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
	
	
	
	
});
</script>



</body>
</html>