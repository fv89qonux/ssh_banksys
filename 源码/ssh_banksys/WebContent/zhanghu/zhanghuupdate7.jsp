<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript">

  function  check2(){
 var v2 = document.getElementById('zhanghuid').value;

 if(v2.length!=13){
 	 alert('账户的长度必须为13位');
 	 document.getElementById('zhanghuid').value = "";
 }
 

 }

    </script>



<div class="pageContent">
<form method="post" name=form1 action="method!zhanghuupdate8" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">

			<input name="id" type="hidden" value="${bean.id }">
			
			
			<div class="unit">
				<label>转账账户</label>
				   <input type="text" name="zhanghu" class="required number" id="zhanghuid" onblur="check2()"/> 
			</div>
			
			
			<div class="unit">
				<label>转账金额</label>
				   <input type="text" name="jine" class="required number" /> 
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

