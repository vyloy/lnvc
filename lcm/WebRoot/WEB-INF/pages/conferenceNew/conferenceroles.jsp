<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
{"list":[<s:iterator value="conferenceRoles" id="role">{"key":"<s:property value="#role.key"/>","rolevalue":"<s:property value="#role.value"/>"},</s:iterator>]}