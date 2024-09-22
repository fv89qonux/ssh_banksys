<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>银行业务模拟系统</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function aa(){

window.close();
}
</script>
</head>
<body>
<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<span style="color: blue;font-size: 18px;font-weight: bold;">银行业务模拟系统</span>
						</div>
						<div id="darkbannerwrap">
						</div>
						<form action="method!login" method="post">
						<!--
						 fieldset将表单内容的相关元素分组
						浏览器会以特殊方式来显示它们，它们可能有特殊的边界、
						3D效果，或者甚至可创建一个子表单来处理这些元素。
						 -->
						<fieldset class="form">
							${errorMessage}
                        	<p>
                        	<!-- 可以使label标签内的区域指向
                        	label标签for属性指代的对象的事件的对象
                        	 -->
								<label for="user_name">账号:</label>
								<input name="username" id="user_name" type="text">
							</p>
							<p>
								<label for="user_password">密码:</label>
								<input name="password" id="user_password" type="password">
							</p>
							
							<p>
								<label for="user_name">用户角色：</label>
								<select name="role" >
								
								<option value="0"><span style="font-size: 10px;">业务员</span></option>
								<option value="1"><span style="font-size: 10px;">系统管理员</span></option>
								</select>
							</p>
							<button type="submit" class="positive" name="Submit">
								登录</button>
								
								<button type="button" class="positive"  onclick="aa()" >
								退出</button>
                            						</fieldset>
						
						
					</form></div>
				</div>   
  
<div id="wrapperbottom_branding">

</div>

</body>
</html>