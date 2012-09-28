<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
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

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/tab.css">
		<script type="text/javascript">
			function playsound(url){
				//alert(url);
				PLAYER.URL = "/tempfile/" + url;
			}
			function download(url){
				//alert(url);
				window.open("/tempfile/" + url); 
			}
		</script>
		<sd:head />
	</head>

	<body>
		<table border="0">
			<tr>
				<td style="text-align: right">
					<a href="javascript:void(-1)" onclick="javascript:location=location">刷新</a>
				</td>
			</tr>
			<tr>
				<td>
					<object id="PLAYER" width="100%" height="45"
						classid="clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6">
					</object>
				</td>
			</tr>
			<tr>
				<td>
					<s:form action="app/recordMediaAction_searchRecord_recordMediaList.action"
						theme="simple">
			按主叫查询:
			<s:textfield name="fromlcc" theme="simple" />
			按被叫查询:
			<s:textfield name="tolcc" theme="simple" />
			请选择日期:
			<sd:datetimepicker name="searchDate" displayFormat="yyyy-MM-dd" />
						<s:submit value="查询" />

					</s:form>
				</td>
			</tr>
			<tr>
				<td>
					<table class="tab_data_table" cellpadding="0" cellspacing="1"
						border="0" bordercolor="#A8C7CE">
						<caption>
							调度台电话录音列表
						</caption>
						<colgroup>
							<col style="width: 12%" />
							<col style="width: 12%" />
							<col style="width: 12%" />
							<col style="width: 12%" />
						</colgroup>
						<tr class="tab_data_table_header">
							<td>
								主叫号码
							</td>
							<td>
								被叫号码
							</td>
							<td>
								日期
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="records" var="record">
							<tr class="tab_data_tr">
								<td title="<s:property value="#record.fromlcc" />">
									<s:property value="#record.fromlcc" />
								</td>
								<td title="<s:property value="#record.tolcc" />">
									<s:property value="#record.tolcc" />
								</td>
								<td title="<s:property value="#record.date" />">
									<s:property value="#record.date" />
								</td>
								<td>
									<button
										onclick="javascript:playsound('<s:property value="#record.filename" />');">
										播放
									</button>
									<button
										onclick="javascript:download('<s:property value="#record.filename" />');">
										下载
									</button>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
