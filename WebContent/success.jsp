<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
<style type="text/css">
.gvSelect {
	width: 20px;
	background-color: #cccccc;
}

.gvColumn {
	text-align: left;
	width: 20%;
	background-color: #cccccc;
}
tr{background-color:#fff; height:30px;}
th{border-right:1px solid #fff; border-bottom:1px solid #fff;}
td{border-right:1px solid #fff; border-bottom:1px solid #fff;}
tr:hover td{background:none;}
tr:hover{background-color:#CCCC00;}

</style>
<script type="text/javascript" src="js/weijs.js"></script>
<script type="text/javascript">
 	function mysubmit() {
		var theform = document.frm;
		$WQuery(theform,'data');
		return false;
	}
	function deleteall() {
		var p = document.getElementById('data');
		deleteAllChild(p);
	}
	function del(a,c,n){
		$WRemoveParent(a,'tr');
	}
</script>
</head>
<body>
	<h1>Home success page!!!</h1>
	<br>${dage}
	<br>
	<div>${uname}:${upwd}</div>
	<div>
		请输入用户名2:<input type="text" name="user.name" value="${user.name}" />
		pwd2:<input type="text" name="user.password" value="${user.password}" />
	</div>
		
	<c:forEach var="row" items="${acs}">
		${row.areaCode},${row.areaName}<br>
	</c:forEach>
	<form name="frm" id="frm" method="post" action="ajaxaction">
		<input type="text" name="code" value="1" /> 
		<input type="button" value="submit" onclick="mysubmit()"/> 
		<input type="button" value="delete"	onclick="deleteall()" />
		<table width="100%" class="tablebox" id="tbl" border="1" cellspacing="0" rules="all"
			style="border-color: #E6E6E6; width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th class="gvColumn">地区编码</th>
					<th class="gvColumn">地区名称</th>
					<th class="gvColumn">曾用编码</th>
					<th class="gvColumn">操作</th>
				</tr>
			</thead>
			<tbody id="data">
				<tr id="tr1" style="display: none; color:cue;" id="trfirst">
					<td class="gvColumn">@areaCode@</td>					
					<td class="gvColumn">@areaName@</td>
					<td class="gvColumn">@areaCodeDeprecated@</td>
					<td class="gvColumn"><a href="javascript:void(0)" onclick="del(this,'@areaCode@','@areaName@')">删除</a></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>