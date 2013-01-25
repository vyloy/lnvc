<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'conflist.jsp' starting page</title>
		<sd:head />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/tab.css">
		<script type="text/javascript" src="js/jquery.js">
</script>
		<script type="text/javascript" src="js/tab.js">
</script>
	</head>
	<body>
		<s:include value="/WEB-INF/pages/include/tab_top.jsp">
			<s:param name="title">
				<s:text name="page.systemparas.set" />
			</s:param>
			<s:param name="selectAll">1</s:param>
			<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CUSTOMER_ADMIN">
				<s:param name="editUrl">app/systemParasAction_toEdit_editParas.action*440*250*edit</s:param>
				<s:param name="addUrl">app/systemParasAction_toAdd_editParas.action*440*250*add</s:param>
				<s:param name="deleteUrl">app/systemParasAction_delete_parasList.action*delete</s:param>
			</security:authorize>
			<s:param name="searchUrl">search</s:param>
		</s:include>
		<div class="tab_content_container">
			<s:if test="errorMsg.equals('')">
				<form action="app/systemParasAction_delete_parasList.action"
					class="content_form">
					<table class="tab_data_table" cellpadding="0" cellspacing="1"
						border="0" bordercolor="#A8C7CE">
						<colgroup>
							<col style="width: 4%" />
							<col style="width: 20%" />
							<col style="width: 20%" />
							<col style="width: 20%" />
							<col style="width: 30%" />
						</colgroup>
						<tr class="tab_data_table_header">
							<td>
								<div>
									<s:text name="page.text.choose" />
								</div>
							</td>
							<td>
								<div>
									<s:text name="page.systemparas.module" />
								</div>
							</td>
							<td>
								<div>
									<s:text name="page.systemparas.key" />
								</div>
							</td>
							<td>
								<div>
									<s:text name="page.systemparas.value" />
								</div>
							</td>
							<td>
								<div>
									<s:text name="page.systemparas.desc" />
								</div>
							</td>
						</tr>
						<s:iterator value="paralist" id="paras">
							<tr class="tab_data_tr">
								<td>
									<div>
										<input type="checkbox" name="choose"
											value="<s:property value='#paras.id' />" />
									</div>
								</td>
								<td title="<s:property value='#paras.module' />">
									<div>
										<s:property value="#paras.module" />
									</div>
								</td>
								<td title="<s:property value='#paras.key' />">
									<div>
										<s:property value="#paras.key" />
									</div>
								</td>
								<td title="<s:property value='#paras.value' />">
									<div>
										<s:property value="#paras.value" />
									</div>
								</td>
								<td title="<s:property value='#paras.description' />">
									<div>
										<s:property value="#paras.description" />
									</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</form>
				<%--
    <jsp:include flush="true" page="/WEB-INF/pages/include/tab_bottom.jsp" />
    --%>
			</s:if>
			<s:else>
				<div class="msgDiv">
					<s:property value="errorMsg" />
				</div>
			</s:else>
		</div>
	</body>
</html>
