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
	<form onsubmit="return navTabSearch(this);" action="method!yewulist" method="post">
	<div class="searchBar">
		<table class="searchContent">

			<tr>
				
				<td>
					账户号：<input type="text"  name="zhanghuhao" value="${zhanghuhao}"  size="12"/>
				</td>
				
				<td>
					操作的业务员：<input type="text"  name="username" value="${username}" size="12"/>
				</td>
				<td>
					操作类型：
					<select name="type">
					<option value="">所有类型</option>
					<option value="销户" <c:if test="${type=='销户' }">selected</c:if>  >销户</option>
					<option value="转账" <c:if test="${type=='转账' }">selected</c:if>  >转账</option>
					<option value="取款"  <c:if test="${type=='取款' }">selected</c:if>  >取款</option>
					<option value="存款"  <c:if test="${type=='存款' }">selected</c:if>  >存款</option>
					<option value="更新账户"  <c:if test="${type=='更新账户' }">selected</c:if>  >更新账户</option>
					<option value="创建账户"  <c:if test="${type=='创建账户' }">selected</c:if>  >创建账户</option>
					</select>
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
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="120">账户号</th>
				<th width="100">操作的业务员</th>
				<th width="100">用户姓名</th>
				<th width="100">操作类型</th>
				<th width="100">操作描述</th>

				<th width="100">操作时间</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${list}" var="bean"  >
			<tr target="sid_user" rel="${bean.id}">
				<td>
				${bean.zhanghu.zhanghuhao }
				</td>
				<td>
				${bean.user.username }
				</td>
				<td>
				${bean.zhanghu.truename }
				</td>
				<td>
					${bean.type }
				</td>
				<td>
				${bean.content }
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
