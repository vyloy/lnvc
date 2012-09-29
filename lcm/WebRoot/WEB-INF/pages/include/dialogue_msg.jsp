<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="dialog_msg_div">
  	<s:iterator value="fieldErrors.values" id="value" status="status">
  		<s:if test="#status.index==0">
  			<s:property value="#value[0]" />
  		</s:if>
  	</s:iterator>
  	<s:if test="exception.message!=null">
  		<s:property value="exception.message" />
  	</s:if>
</div>