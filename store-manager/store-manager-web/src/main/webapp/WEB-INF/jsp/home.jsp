<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
 <%@ include file="../../public/head.jspf"%>
    <style type="text/css">  
        #menu {  
            width:60px;  
            /*border:1px solid red;*/  
        }  
        #menu ul {  
            list-style: none;  
            padding: 0px;  
            margin: 0px;  
        }  
        #menu ul li {  
            border-bottom: 1px solid #fff;  
              
        }  
        #menu ul li a {  
            /*先将a标签转换为块级元素，才能设置宽和内间距*/  
            display: block;  
            background-color: #00a6ac;  
            color: #fff;  
            padding: 5px;  
            text-decoration: none;  
        }  
        #menu ul li a:hover {  
            background-color: #008792;  
        }  
          
    </style>  
      
    <script type="text/javascript">  
        $(function(){  
            $("a[title]").click(function(){  
                var text = $(this).text();  
                var href = $(this).attr("title");             
                if($("#tt").tabs("exists", text)) {  
                    $("#tt").tabs("select", text);  
                } else {                     
                    $("#tt").tabs("add",{  
                        title:text,  
                        closable:true,  
                        content:'<iframe title=' + text + ' src=' + href + ' frameborder="0" width="100%" height="100%"/>'  
                    });  
                }  
                  
            });  
        });  
    </script>  
</head>  
  
    <body class="easyui-layout">  
        <div data-options="region:'north',title:'欢迎来到商城后台管理',split:true" style="height:85px;"></div>     
        <div data-options="region:'west',title:'系统操作',split:true" style="width:200px;">  
            <!-- 此处显示的是系统菜单 -->  
            <div id="menu" class="easyui-accordion" data-options="fit:true">     
                <div title="商品管理" data-options="iconCls:'icon-save'">     
                    <ul>  
                        <li><a href="#" title="${store}/Manager/itemAdd">新增商品</a>  
                        <li><a href="#" title="${store}/Manager/itemQuery">查询商品</a> 
                        <li><a href="#" title="send_product_query.action">搜索商品</a>  
                        <li><a href="#" title="send_product_query.action">规格参数</a>  
                    </ul>  
                </div>     
                <div title="网站内容管理" data-options="iconCls:'icon-save'">  
                    <ul>  
                        <li><a href="#" title="send_users_query.action">内容分类管理</a>  
                        <li><a href="#" title="send_users_query.action">内容管理</a>   
                    </ul>  
                </div>
                <div title="其他操作3" data-options="iconCls:'icon-reload'">
                	<ul>  
                        <li><a href="#">类别管理</a>  
                        <li><a href="#">商品管理</a>  
                    </ul>
                </div>     
            </div>     
        </div>     
        <div data-options="region:'center',title:'后台操作页面'" style="padding:1px;background:#eee;">  
            <div id="tt" class="easyui-tabs" data-options="fit:true">     
                <div title="系统缺省页面" style="padding:10px;"> 
                	    <div id="p" class="easyui-panel" title="浮动面板" style="width:500px;height:150px;padding:10px;background:#fafafa;" data-options="iconCls:'icon-save',closable:true, collapsible:true,minimizable:true,maximizable:true">   
    					<p>  此处以后显示相应的系统信息（当前操作系统的类型，当前项目的域名，硬件的相关配置或者显示报表</p>   
    					  
                     </div>               
                </div>    
                  
            </div>                      
        </div>
        <div data-options="region:'south',title:'此处显示版权信息',split:true" style="height:70px;">
        <center><p>声明: 此后台系统仅限于学习，禁止传播，版本号：0.0.1-2017-06-04</p></center>
        </div>
<!--         为添加商品提供一个新窗口 -->
        <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>      
    </body>    
  
</html>  