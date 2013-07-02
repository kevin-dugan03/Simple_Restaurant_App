<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />

	<form action="findCustomer.do" method="post">

    <table id="findCustomer">
    <tr><td>First Name:</td>
    <td><input type="text" name="firstName" /></td>
    </tr>
    
    <tr>
    <td>Last Name:</td>
    <td><input type="text" name="lastName" /></td>
    </tr>
    
    <tr>
    <td>Phone Number:</td>
    <td><input type="text" name="phone" /></td>
    </tr>
    
    <tr>
    <td><center>
    <input type="button" class="findCustomerInfo" name="find" 
        value="Find Customer" onClick="javascript:validateFindInfo(this.form)"/>
    </center></td>

    <td><center>
    <input type="submit" class="findCustomerInfo" name="find" 
        value="View all Customers" />
    </center></td>
    </tr>
    </table>
    </form>
    
  <c:import url="footer.html" />