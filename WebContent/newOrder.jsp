<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:import url="header.html" />
  <c:import url="navigation.html" />

   <form action="addPizza.do" id="orderForm">
      <table id="customerData">
        <tr><td colspan ="2"><center><b>Current Active Customer</b></center></td></tr>
        <tr><td>First Name:</td>
          <td><input type="text" name="firstName" value="${customer.first}"/></td>
        </tr>
        <tr><td>Last Name:</td>
          <td><input type="text" name="lastName" value="${customer.last}"/></td>
        </tr>
        <tr><td>Phone Number:</td>
          <td><input type="text" name="phone" value="${customer.phone}"/></td>
        </tr>
        <tr><td colspan="2" style="color:red"><i>(Clear customer information if needed.)</i></td></tr>
      </table>

      <table id ="pizza_table">
        <tr><td colspan ="2"><center><b>Pizza Information</b></center></td></tr>
        <tr><td>
          <fieldset class="alignment">
            <legend>Pizza Size</legend>
            <input type ="radio" name ="group1" value ="Small">Small<br>
            <input type ="radio" name ="group1" value="Medium">Medium<br>
            <input type ="radio" name ="group1" value ="Large">Large<br>
            <input type ="radio" name ="group1" value ="Extra Large">Extra Large<br>
          </fieldset></td>

        <td>
          <fieldset class="alignment">
            <legend>Crust Type</legend>
            <input type = "radio" name ="group2" value ="Thin">Thin<br>
            <input type ="radio" name ="group2" value="Thick">Thick<br>
            <input type ="radio" name ="group2" value ="Hand Tossed">Hand Tossed<br>
            <input type ="radio" name ="group2" value ="Stuffed Crust">Stuffed Crust<br>
          </fieldset>
        </td></tr>

        <tr><td colspan="2">
          <fieldset id ="toppings_data">
            <legend>Toppings</legend>
             <table class="alignment">
               <tr><td><input type ="checkbox" name ="group3" value ="Pepperoni">Pepperoni</td>
                 <td><input type ="checkbox" name ="group3" value="Sausage">Sausage</td>
                 <td><input type ="checkbox" name ="group3" value="Buffalo">Buffalo</td>
               </tr>
               <tr><td><input type ="checkbox" name ="group3" value ="Ham">Ham</td>
                 <td><input type ="checkbox" name ="group3" value ="Bacon">Bacon</td>
                 <td><input type ="checkbox" name ="group3" value ="Zebra">Zebra</td>
               </tr>
               <tr><td><input type = "checkbox" name ="group3" value ="Mushroom">Mushroom</td>
                 <td><input type ="checkbox" name ="group3" value="Onion">Onion</td>
                 <td><input type ="checkbox" name ="group3" value="Artichokes">Artichokes</td>
               </tr>
               <tr><td><input type ="checkbox" name ="group3" value ="Olive">Olive</td>
                 <td><input type ="checkbox" name ="group3" value ="Peppers">Peppers</td>
                 <td><input type ="checkbox" name ="group3" value ="Jalapenos">Jalapenos</td>
               </tr>
               <tr><td><input type = "checkbox" name ="group3" value ="Pineapple">Pineapple</td>
                 <td><input type ="checkbox" name ="group3" value="Extra Cheese">Extra Cheese</td>
                 <td><input type ="checkbox" name ="group3" value="Feta Cheese">Feta Cheese</td>
               </tr>
             </table>
          </fieldset></td></tr>
        <tr>
          <td colspan="2"><br>
          <center><input type="button" class="orderButton" value="Add Pizza"
                               onClick="javascript:validatePizza(this.form)"/>
          </center></td>
          </tr>
    </table>
    </form>
  <c:import url="footer.html" />
