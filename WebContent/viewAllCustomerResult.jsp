<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
    <table>
        <tr>
        <th>Customer #</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Street</th>
        <th>Email</th>
        <th>Phone</th>
        </tr>    
          <c:forEach var="cust" items="${customers}">
            <tr>
              <td>${cust.id}</td>
              <td>${cust.first}</td>
              <td>${cust.last}</td>
              <td>${cust.street}</td>
           	  <td>${cust.email}</td>
              <td>${cust.phone}</td>
           	</tr>
            </c:forEach>
   </table>
  <c:import url="footer.html" />