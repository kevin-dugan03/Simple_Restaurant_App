<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:set var="currentUser" value="${pageContext.request.userPrincipal.name}" scope="session" />
  <p>Current User: ${currentUser}</p>
  <c:import url="navigation.html" />
  <c:import url="footer.html" />
