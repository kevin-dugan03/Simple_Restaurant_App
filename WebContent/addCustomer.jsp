<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />
  
	<form action="addCustomer.do" method="post">
        <table class="placement">
           <tr><td colspan="2"><center><h2>Enter Customer Information</h2></center>
           </td></tr>
           <tr><td>First Name:</td>
           <td><input type="text" name = "first_name" /></td></tr>
           <tr><td>Last Name:</td>
           <td><input type = "text" name = "last_name" /></td></tr>
                
           <tr><td>Email Address:</td>
           <td><input type = "text" name = "email_address" /></td></tr>
                
           <tr><td>Street Address:</td>
           <td><input type = "text" name = "street_address" /></td></tr>
                 
           <tr><td>City:</td>
           <td><input type = "text" name = "city" /></td></tr>
                 
           <tr><td>State:</td>
           <td><input type = "text" name = "state" /></td></tr>
                 
           <tr><td>Zip Code:</td>
           <td><input type = "text" name = "zip"/></td></tr>
                
           <tr><td>Phone Number:</td>
           <td><input type = "text" name = "phone"/></td></tr>
                 
           <tr><td><br>
           <center>
             <input type="button" class="addCustInfo" value="Save Customer Data" 
                        onClick="javascript:validate(this.form)"/>
           </center>
           </td>
           <td><br>
           <center>
             <input type="button" class="addCustInfo" value="Reset Form" 
                        onClick="javascript:resetCustomer()" />
           </center>
           </td></tr>
         </table>
        </form>
  <c:import url="footer.html" />