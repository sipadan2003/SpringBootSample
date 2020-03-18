<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.http.HttpStatus" %>
<% response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()); %>
{"code":2006,"message":"Unsupported media type"}