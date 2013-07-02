<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
  
	<c:forEach items="${orders}" var="order">
	  <div id="allOrdersCustomerInfo">
		<h3><u>Customer Details</u></h3>
	 	<p>${order.customer.first} 
	 	${order.customer.last}</p>
		<p>${order.customer.street}</p>
		<p>${order.customer.city}, 
		${order.customer.state}   
		${order.customer.zip}</p>
		<p>${order.customer.email}</p>
		<p>${order.customer.phone }</p>
	  </div>
	  
		<div id="allOrdersPizzaInfo">
		 <div id="pizzaDetails">
		 <hr></hr>
		 <c:forEach items="${order.pizzas}" var="pizza">
		  <p>Size: ${pizza.size}</p>
		  <p>Crust: ${pizza.crust}</p>
		  <p>Toppings:
		   <c:forEach items="${pizza.toppings}" step="1" varStatus="loop" >
	  		    ${pizza.toppings[loop.index]}
	  		</c:forEach></p>
	  		<hr></hr>
		 </c:forEach>
		</div>
		  
		  <div id="orderPrice">
			<h3>Total Price:</h3>
			<p>$${order.price}</p>
		  </div>
		  </div>
		  <br style="clear:both;" />
		  <hr style="border: 3px outset" />
	</c:forEach>
	
  <c:import url="footer.html" />