<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
  
    <b><i><u>Order Details</u></i></b>
	<center>${customerMessage}</center>
	<c:if test="${customer.zip != 0}">
	<div class="placement">
      <form action="checkout.do" method="post">
        <table>
          <tr>
     		<td>${customer.first} ${customer.last}</td>
          </tr>
          <tr>
        	<td>${customer.street}</td>
          </tr>
          <tr>
        	<td>${customer.city}, ${customer.state}  ${customer.zip}</td>
          </tr>
          <tr>
        	<td>${customer.email}</td>
          </tr>
          <tr>
        	<td>${customer.phone}</td>
          </tr>
          <tr><td colspan="2">
			<input type="submit" class="orderButton" value="Place Order"/>
		  </td></tr>
	  </table>
	    <input type="hidden" name="first" value="${customer.first}" />
	  	<input type="hidden" name="last" value="${customer.last}" />
	  	<input type="hidden" name="phone" value="${customer.phone}" />
	  </form>
	  </div>
	  
	  <div id="morePizza">
	  <form action="morePizza.do" method="post">
	  	<input type="hidden" name="first" value="${customer.first}" />
	  	<input type="hidden" name="last" value="${customer.last}" />
	  	<input type="hidden" name="phone" value="${customer.phone}" />
	  	
	  	<table>
	  		<tr><td colspan="2"><center>${pizzaMessage}</center></td>
	  		<td></td></tr>
	  		<tr><td>Size:</td>
	  		<td>${pizza.size}</td></tr>
	  		<tr><td>Crust:</td>
	  		<td>${pizza.crust}</td></tr>
	  		<tr><td>Toppings:</td>
	  		<td>
	  		<c:forEach items="${pizza.toppings}" step="1" varStatus="loop" >
	  		    ${pizza.toppings[loop.index]}
	  		</c:forEach>
	  		</td></tr>
	  		<tr><td colspan="2"><input type = "submit" class="orderButton" value="Add More Pizza"/></td></tr>
	  	</table>
	  </form>
	  </div>
      </c:if>
      
   <c:import url="footer.html" />