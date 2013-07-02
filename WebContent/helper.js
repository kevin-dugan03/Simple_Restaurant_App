function validate(form)
{
    if (form.first_name.value == "" || !(/^[a-z A-Z ]+$/.test(form.first_name.value)))
    {
        alert("Error in FIRST NAME field. Cannot be empty \nand must only contain letters");
        form.first_name.focus();
        return;
    }
    else if (form.last_name.value == "" || !(/^[a-zA-Z ]+$/.test(form.last_name.value)))
    {
        alert("Error in LAST NAME field. Cannot be empty \n and can only contain letters");
        form.last_name.focus();
        return;

    }
    else if (form.email_address.value == "" || !(/\S+@\S+\.\S+/.test(form.email_address.value)))
    {
        alert("EMAIL ADDRESS error. Must be in the form xxxx@xxxx.xxx");
        form.email_address.focus();
        return;

    }
    else if (form.street_address.value == "")
    {
        alert("STREET ADDRESS error. Cannot be empty.");
        form.street_address.focus();
        return;

    }
    else if (form.city.value == "")
    {
        alert("CITY field error. Cannot be empty.");
        form.city.focus();
        return;

    }
    else if (form.state.value == "" || !(form.state.value.length == 2))
    {
        alert("STATE field error. Cannot be empty and must be a 2 letter abbreviation.");
        form.state.focus();
        return;

    }
    else if (form.zip.value == "" || !(/(^\d{5}$)/.test(form.zip.value)))
    {
        alert("ZIP CODE field error. Cannot be empty and must have correct number of digits.");
        form.zip.focus();
        return;

    }
    else if (form.phone.value == "" || !(/^\d{3}-\d{3}-\d{4}$/).test(form.phone.value))
    {
        alert("PHONE NUMBER field error. Cannot be empty and \nmust be in the format: \nxxx-xxx-xxxx.");
        form.phone.focus();
        return;

    }

    form.submit();
}

function resetCustomer()
{
    document.getElementById("customerForm").reset();
}

function resetOrderForm()
{
	document.getElementById("orderForm").reset();
}

function validatePizza(form)
{
    var size = false;
    var crust = false;
    var toppings = false;

    if (!validateCustomerInfo(form))
    {
    	return;
    }
    
    for (var i = 0; i < form.group1.length; i++)
    {
        if (form.group1[i].checked)
        {
            size = true;
            break;
        }
    }
    
    if (!size)
    {
        alert("Select desired size");
        return;
    }
    
    for (var i = 0; i < form.group2.length; i++)
    {
        if (form.group2[i].checked)
        {
            crust = true;
            break;
        }
    }
    
    if (!crust)
    {
        alert("Select type of crust");
        return;
    }
    
    for (var i = 0; i < form.group3.length; i++)
    {
        if (form.group3[i].checked)
        {
            toppings = true;
            break;
        }
    }
    
    if (!toppings)
    {
    	var verify = confirm("Cheese only pizza?");
    	if (verify == true)
    	{
    		toppings = true;
    	}
    	else
    	{
    		return;
    	}
    }
    
    if (size == true && crust == true && toppings == true)
    {
    	form.submit();
    }
}
    
function validateFindInfo(form)
{
	if (validateCustomerInfo(form))
	{
		form.submit();
	}
}

function validateCustomerInfo(form)
{
	if (form.firstName.value == "" || !(/^[a-z A-Z ]+$/.test(form.firstName.value)))
    {
        alert("Error in FIRST NAME field. Cannot be empty \nand must only contain letters");
        form.firstName.focus();
        return false;
    }
    else if (form.lastName.value == "" || !(/^[a-zA-Z ]+$/.test(form.lastName.value)))
    {
        alert("Error in LAST NAME field. Cannot be empty \n and can only contain letters");
        form.lastName.focus();
        return false;
    }
    else if (form.phone.value == "" || !(/^\d{3}-\d{3}-\d{4}$/).test(form.phone.value))
    {
        alert("PHONE NUMBER field error. Cannot be empty and \nmust be in the format: \nxxx-xxx-xxxx.");
        form.phone.focus();
        return false;
    }
	
	return true;
}

function validateRegistration(form)
{
	if (form.email.value == "" || !(/\S+@\S+\.\S+/.test(form.email.value)))
    {
        alert("EMAIL ADDRESS error. Must be in the form xxxx@xxxx.xxx");
        form.email_address.focus();
        return;
    }
    else if (form.username.value == "" || !(/^[a-z A-Z0-9 ]+$/.test(form.username.value)))
    {
        alert("Error in USERNAME field. Cannot be empty.");
        form.username.focus();
        return;
    }
    else if (form.password1.value == "" || !(/^[a-zA-Z0-9 ]+$/.test(form.password1.value)))
    {
        alert("Error in PASSWORD field. Cannot be empty.");
        form.password1.focus();
        return;
    }
    else if (form.password2.value == "" || !(/^[a-zA-Z0-9 ]+$/.test(form.password2.value)))
    {
        alert("Error in PASSWORD field. Cannot be empty.");
        form.password2.focus();
        return;
    }
    else if (form.password1.value != form.password2.value)
    {
        alert("Passwords do not match.");
        form.password2.focus();
        return;
    }
	
	form.submit();
}

