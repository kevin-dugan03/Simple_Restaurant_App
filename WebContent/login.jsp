<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  
  <c:if test="${message != null }">
    <h3 style="color:red"><u>****${message }****</u></h3>
  </c:if>
  
  <center><h3 style="color:red">Login</h3></center>
  <form action="j_security_check" method="post">
  	<table class="placement">
  	  <tr>
  	    <td>Username:</td>
  	    <td><input type="text" name="j_username" /></td>
  	  </tr>
  	  <tr>
  	    <td>Password:</td>
  	    <td><input type="password" name="j_password" /></td>
  	  </tr>
  	  <tr> 
  	    <td colspan="2"><input type="submit" value="Login" name="j_security_check" /></td>
  	  </tr>
  	</table>
  </form>
  <br>
  
  
  <center><h3 style="color:red">Registration</h3></center>
  <form action="register.do" method="post">
  	<table class="placement">
  		<!--  <tr>
  		  <td>First Name:</td>
  		  <td><input type="text" name="firstName" /></td>
  		</tr>
  		<tr>
  		  <td>Last Name:</td>
  		  <td><input type="text" name="lastName" /></td>
  		</tr>
  		<tr>-->
  		  <td>Email address:</td>
  		  <td><input type="text" name="email" /></td>
  	    </tr>
  	    <tr>
  	      <td>Choose Username:</td>
  		  <td><input type="text" name="username" /></td>
  	    </tr>
  	    <tr>
  	      <td>Choose Password:</td>
  	      <td><input type="password" name="password1" /></td>
  	    </tr>
  	    <tr>
  	      <td>Verify Password:</td>
  	      <td><input type="password" name="password2" /></td>
  	    </tr>
  		<tr>
  		  <td colspan="2"><input type="button"  value="Submit" onClick="javascript:validateRegistration(this.form)" /></td>
  		</tr>
  	</table>
  </form>
  <c:import url="footer.html" />