<%@ page language="java" import="java.util.*,listener.MySessionContext" pageEncoding="utf-8"%>
<%
session.invalidate();
String sessionId = request.getParameter("jsessionid");  
MySessionContext context = MySessionContext.getInstance();
if(context.getSession(sessionId)!=null)
context.getSession(sessionId).invalidate();
%>