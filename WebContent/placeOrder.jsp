<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
  
	<div id="orderDetails">
		<div id="customerDetails">
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
		
		<div id="pizzaDetails">
		<hr></hr>
		<c:forEach items="${order.pizzas}" var="pizza" >
			<p style="color:red">Pizza Price: ${pizza.price}</p>
				<p style="color:red">Size: ${pizza.size} | 
				Crust: ${pizza.crust}</p>
				<p style="color:red">Toppings:
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
<c:import url="footer.html" />