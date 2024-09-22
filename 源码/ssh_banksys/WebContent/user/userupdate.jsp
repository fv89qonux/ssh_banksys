<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
<form method="post" name=form1 action="method!userupdate2" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<div class="pageFormContent nowrap" layoutH="80">
	
							<dl>
							<dt >
							用户名:${bean.username }<input  type="hidden" name="id"  value="${bean.id }"/>
							</dt>
							
						</dl>
						<dl>
							<dt >
							真实姓名
							</dt>
							<dd >
								<input class="required" type="text" name="truename" size="20" maxlength="20" value="${bean.truename }"/>
							
							</dd>
				</dl>
				
				
				<dl>
							<dt >
							地址
							</dt>
							<dd >
								<input class="required " type="text" name="dizhi" size="20" maxlength="20" value="${bean.dizhi }"/>
							
							</dd>
						</dl>
						
						<dl>
							<dt >
							联系方式
							</dt>
							<dd >
								<input class="required " type="text" name="lianxifangshi" size="20" maxlength="20" value="${bean.lianxifangshi }"/>
							
							</dd>
						</dl>
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

