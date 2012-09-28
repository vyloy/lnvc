<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="dialogue_tab_container">
	<table border="0" cellspacing="1" cellpadding="0" class="tab_data_table">
		<colgroup>
			<col style="width: 30%"  />
			<col style="width: 70%" />
		</colgroup>
		<tr class="tab_data_table_header">
			<td>用户名</td>
			
			<td>角色</td>
		</tr>
		<!--s:set name="su" value="selectedUser.get(${param.role})"/-->
		<s:iterator value="allUsers" id="user">
			<tr class="dialogue_tab_tr" >
				<td><s:property value="#user.username" /></td>
				<td>
				<s:if test="conferenceRoles!=null">
				<s:iterator value="conferenceRoles" id="role">
					<s:if test="#role!=null">
						<s:if test="selectedUser.get(#role.key).contains(#user.id)">
						<input type="checkbox" checked="checked" name="userRole" value="<s:property value="#role.key" />_<s:property value='#user.id' />_<s:property value='#user.lccAccount'/>" />
						</s:if>
						<s:else>
						<input type="checkbox" name="userRole" value="<s:property value="#role.key" />_<s:property value='#user.id' />_<s:property value='#user.lccAccount'/>" />
						</s:else>
						<s:property value="#role.value" />
					</s:if>
				</s:iterator>
				</s:if>
				&nbsp;
				</td>
				
			</tr>
		</s:iterator>
	</table>
</div>
