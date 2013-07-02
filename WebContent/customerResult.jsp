<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
  
   <c:choose>
   		<c:when test="${message == ('Customer already exists.')}">
   			<h2><i>${message}</i></h2>
   		</c:when>
   		<c:when test="${message == ('Customer unable to be added')}">
   			<h2><i>${message}</i></h2>
   		</c:when>
   		<c:when test="${message == ('Customer not found. Try again or add new customer.') }">
   			<h2><i>${message}</i></h2>
   		</c:when>
   		<c:otherwise>
   			<center><h2><i>${message}</i></h2></center><br>
     		
     		<table id ="addCustomerResult">
        	 <tr><td colspan="2"><center><b>Customer Information</b></center>
        	 <br>
        	 </td></tr>
        	 
        	 <tr><td>Customer Number:</td>
             <td>${customer.id}</td></tr>
         
         	 <tr><td>First Name:</td>
             <td>${customer.first}</td></tr>
                
        	 <tr><td>Last Name:</td>
             <td>${customer.last}</td></tr>
                
             <tr><td>Email Address:</td>
             <td>${customer.email}</td></tr>
                
             <tr><td>Street Address:</td>
             <td>${customer.street}</td></tr>
                 
             <tr><td>City:</td>
             <td>${customer.city}</td></tr>
                 
             <tr><td>State:</td>
             <td>${customer.state }</td></tr>
                 
             <tr><td>Zip Code:</td>
             <td>${customer.zip}</td></tr>
                 
             <tr><td>Phone Number:</td>
             <td>${customer.phone}</td></tr>
             
             <tr><td colspan="2">
               <a href="newOrder.jsp">Place New Order for this Customer</a>
             </td></tr>
           </table>
   		</c:otherwise>
   </c:choose>
  <c:import url="footer.html" />