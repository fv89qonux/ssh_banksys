<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<form id="pagerForm" method="post" action="${url }">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${ps}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="method!zhanghulist2" method="post">
	<div class="searchBar">
		<table class="searchContent">

			<tr>
				
				<td>
					账户号：<input type="text"  name="zhanghuhao" value="${zhanghuhao}"  size="12"/>
				</td>
				<td>
					账户状态：
					<select name="zhanghulock">
					<option value="">请选择账户状态</option>
					<option value="0" <c:if test="${zhanghulock==0 }">selected</c:if>  >正常使用</option>
					<option value="1" <c:if test="${zhanghulock==1 }">selected</c:if>  >已销户</option>
					<option value="2"  <c:if test="${zhanghulock==2 }">selected</c:if>  >已冻结</option>
					</select>
				</td>
				<td>
					用户姓名：<input type="text"  name="truename" value="${truename}" size="12"/>
				</td>
				<td>
					账户添加时间：<input type="text" class="date"  name="time1" value="${time1}" size="12"/>
					
				</td>
				
			
				<td>
					<div class="subBar">
					<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
					</ul>
					</div>
				</td>
				
			</tr>
			
		</table>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	

			<li><a class="delete" href="method!zhanghudelete2?id={sid_user}" target="ajaxTodo" title="确定要冻结吗?" ><span>冻结账户</span></a></li>
			
			<li><a class="delete" href="method!zhanghudelete3?id={sid_user}" target="ajaxTodo" title="确定要撤销冻结吗?" ><span>撤销冻结账户</span></a></li>
	

			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120">账户</th>
				<th width="120">账户状态</th>
				<th width="100">添加的业务员</th>
				<th width="100">账户姓名</th>
				<th width="100">联系方式</th>
				<th width="100">联系地址</th>
				<th width="100">金额</th>
				<th width="100">备注</th>
				<th width="100">添加时间</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="bean"  >
			<tr target="sid_user" rel="${bean.id}">
				<td>
				${bean.zhanghuhao }
				</td>
				<td>
				
				<c:if test="${bean.zhanghulock==0 }">正常使用</c:if>
				<c:if test="${bean.zhanghulock==1 }">已销户</c:if>
				<c:if test="${bean.zhanghulock==2 }">已冻结</c:if>

				
				</td>
				<td>
				${bean.user.username }
				</td>
				<td>
					${bean.truename }
				</td>
				<td>
				${bean.lianxifangshi }
				</td>
				<td>
					${bean.dizhi }
				</td>
				<td>
					${bean.jine}
				</td>
				
				<td>
				${bean.beizhu}
				</td>
				<td>
				${fn:substring(bean.createtime,0, 19)}
				</td>
				
				
			</tr>			
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			
			<span>共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${ps}" pageNumShown="10" currentPage="${pn}"></div>

	</div>
</div>

  </body>
</html>
