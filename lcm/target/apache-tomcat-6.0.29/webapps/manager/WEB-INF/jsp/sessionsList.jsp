<?xml version="1.0" encoding="ISO-8859-1"?>
<%--
 Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator" %>
<%@page import="org.apache.catalina.manager.JspHelper" %>
<%@page import="org.apache.catalina.Session" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% String path = (String) request.getAttribute("path");
   String submitUrl = ((HttpServletRequest)pageContext.getRequest()).getRequestURI() + "?path=" + path;
   Collection activeSessions = (Collection) request.getAttribute("activeSessions");
%>
<html>
<head>
<style>
H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}A.name {color : black;}HR {color : #525D76;}  table {
    width: 100%;
  }
  td.page-title {
    text-align: center;
    vertical-align: top;
    font-family:sans-serif,Tahoma,Arial;
    font-weight: bold;
    background: white;
    color: black;
  }
  td.title {
    text-align: center;
    vertical-align: top;
    font-family:sans-serif,Tahoma,Arial;
    font-style:italic;
    font-weight: bold;
    background: #D2A41C;
     font-size:100px;
     height:120px;
  }
  td.header-left {
    text-align: left;
    vertical-align: top;
    font-family:sans-serif,Tahoma,Arial;
    font-weight: bold;
    background: #FFDC75;
     font-size:70px;
  }
  td.header-center {
    text-align: center;
    vertical-align: top;
    font-family:sans-serif,Tahoma,Arial;
    font-weight: bold;
    background: #FFDC75;
     font-size:70px;
  }
  td.row-left {
    text-align: left;
    vertical-align: middle;
    font-family:sans-serif,Tahoma,Arial;
    color: black;
     font-size:50px;  }
  td.row-center {
    text-align: center;
    vertical-align: middle;
    font-family:sans-serif,Tahoma,Arial;
    color: black;
     font-size:50px;  }
  td.row-right {
    text-align: right;
    vertical-align: middle;
    font-family:sans-serif,Tahoma,Arial;
    color: black;
     font-size:50px;  }
  TH {
    text-align: center;
    vertical-align: top;
    font-family:sans-serif,Tahoma,Arial;
    font-weight: bold;
    background: #FFDC75;
  }
  TD {
    text-align: center;
    vertical-align: middle;
    font-family:sans-serif,Tahoma,Arial;
    color: black;
  }
</style>
<title>/manager</title>
</head>

<body bgcolor="#FFFFFF">
<table border="1" cellspacing="0" cellpadding="3">
<tr style="height:100px;">
 <td colspan="5" class="title">Monitor</td>
</tr>
<tr style="height:80px;">
 <td class="header-left">Path</td>
 <td class="header-left">Display Name</td>
 <td class="header-center">Running</td>
 <td class="header-center">Sessions</td>
 <td class="header-left">Commands</td>
</tr>

<tr style="height:80px;">
 <td class="row-left" bgcolor="#C3F3C3" rowspan="2">/videoserver</td>
 <td class="row-left" bgcolor="#C3F3C3" rowspan="2">Manager Application</td>
 <td class="row-center" bgcolor="#C3F3C3" rowspan="2">true</td>
 <td class="row-center" bgcolor="#C3F3C3" rowspan="2"><%= JspHelper.formatNumber(activeSessions.size()) %></td>
 <td class="row-left" bgcolor="#C3F3C3" rowspan="2">null</td>
 </tr></table>

</body>
</html>
