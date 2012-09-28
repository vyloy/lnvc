<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="index_bottom_container">
		<img src="images/index_bottom_left.gif" class="index_bottom_left">
		<img src="images/index_bottom_right.gif" class="index_bottom_right">
		<div class="index_bottom_rights">
			<s:text name="page.bottom.rights" />
		</div>
		<div class="index_bottom_today">
			<s:text name="page.bottom.today" />
			<s:if test="locale.toString()=='zh_CN'">
				<s:date name="now" format="yyyy年MM月dd日 EEE"/>
			</s:if>
			<s:elseif test="locale.toString()=='en_US'">
				<s:date name="now" format="dd/MM/yyyy EEE" />
			</s:elseif>
		</div>
	</div>
