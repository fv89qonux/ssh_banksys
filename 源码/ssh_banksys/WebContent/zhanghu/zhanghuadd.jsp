<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

  function  check2(){
 var v2 = document.getElementById('daikuannianxianid').value;

 if(v2.length!=6){
 	 alert('密码的长度必须为6位');
 	 document.getElementById('daikuannianxianid').value = "";
 }
 

 }
 
 
 
    </script>



<div class="pageContent">
<form method="post" name=form1 action="method!zhanghuadd2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
		
				
			<div class="unit">
				<label>账户密码:</label>
				  <input type="text"  name="password" id="daikuannianxianid" onblur="check2()" >
			</div>
			
			<div class="unit">
				<label>用户姓名:</label>
				  <input type="text"  name="truename" class="required" >
			</div>
			
			
			
			<div class="unit">
				<label>联系方式:</label>
				   <input type="text"  name="lianxifangshi"  class="required" >
			</div>
			
			<div class="unit">
				<label>地址:</label>
				   <input type="text"  name="dizhi" class="required" >
			</div>
			
			
			<div class="unit">
				<label>账户备注:</label>
				  <textarea rows="7" cols="50" name="beizhu"></textarea>
			</div>
			
			
			
	</div>
<div class="formBar">
      <ul>
            <li>
                 <div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div>
            </li>
            <li>
                  <div class="button"><div class="buttonContent"><button type="Button" class="close">取消</button></div></div>
            </li>
      </ul>
</div>
</form>
</div>

