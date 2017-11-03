<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../../../public/head.jspf"%>
<style type="text/css">
<style type="text/css">
body {
	margin: 1px;
}

.searchbox {
	margin: -3;
}
</style>
</head>

<body>
	 欢迎进入搜索商品<input id="dd" type="text" class="easyui-datebox" required="required">
	<table id="dg"></table>
	<input class="easyui-searchbox" data-options="prompt:'Please Input Value',searcher:doSearch" style="width:300px"></input>
	<script>
		function doSearch(value){
			alert('You input: ' + value);
		}
	</script>
</body>


</html>
