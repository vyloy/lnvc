<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="dialogue_tab_container">
	<table border="0" cellspacing="1" cellpadding="0" class="tab_data_table">
		<colgroup>
			<col style="width: 6%"  />
			<col style="width: 32%" />
			<col style="width: 32%" />
			<col style="width: 30%" />
		</colgroup>
		<tr class="tab_data_table_header">
			<td><s:text name="page.text.choose" /></td>
			<td><s:text name="page.display.text.username" /></td>
			<td><s:text name="page.display.text.realname"/></td>
			<td><s:text name="page.display.text.dep"/></td>
		</tr>
		<s:set name="su" value="selectedUser.get(${param.role})"/>
		<s:iterator value="allUsers" id="user">
			<tr class="dialogue_tab_tr" >
				<s:if test="su.contains(#user.userId)">
					<td><input type="checkbox" checked="checked" name="userId" value="${param.role}_<s:property value='#user.id' />" /></td>
				</s:if>
				<s:else>
					<td><input type="checkbox" name="userId" value="${param.role}_<s:property value='#user.id' />" /></td>
				</s:else>
				<td><s:property value="#user.username" /></td>
				<td><s:property value="#user.realName" /></td>
				<td><s:property value="#user.department.departmentName" /></td>
			</tr>
		</s:iterator>
	</table>
</div>
