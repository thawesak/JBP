/*
 *	Document   : validate.js
 *	Created on : 2 Nov 2555, 16:23:32
 *	Author     : NIPIT
 *	Description:
 *		Validate input form with trim function.
*/

function ValidateForm(form){

	valid = true;
	var x = form.email.value;

	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	form.name.value = trim(form.name.value);
	form.email.value = trim(form.email.value);
	form.phone.value = trim(form.phone.value);
	form.subject.value = trim(form.subject.value);
	form.message.value = trim(form.message.value);
	form.ct_captcha2.value = trim(form.ct_captcha2.value);

	if(!notEmpty(form.name))
	{
		alert('You have not entered your name. Please fill in the required field (*).')
		form.name.focus();
		valid = false;
	}else if(!notEmpty(form.email)){
		alert('You have not filled in your email address. Please fill in the required field (*).')
		form.email.focus();
		valid = false;
	}else if(!filter.test(x)) {
		alert('Please kindly enter a valid email address. For example, john@hotmail.com');
		form.email.focus();
		valid = false;
	}else if(!notEmpty(form.phone)){
		alert('You have not entered your phone number. Please fill in the required field (*).')
		form.phone.focus();
		valid = false;
	}else if(!notEmpty(form.subject)){
		alert('You have not entered your subject. Please fill in the required field (*).')
		form.subject.focus();
		valid = false;
	}else if(!notEmpty(form.message)){
		alert('You have not filled in your message. Please fill in the required field (*).')
		form.message.focus();
		valid = false;
	}else if(!notEmpty(form.ct_captcha2)){
		alert('You have not filled in security code. Please fill in the required field (*).')
		form.ct_captcha2.focus();
		valid = false;
	}

	return valid;

}

function notEmpty(elem){
	var str = elem.value;
	if(str.length == 0){
		//alert("You must fill in all required fields (*)");
		return false;
	} else {
		return true;
	}
}

function trim(str) {
	str = str.replace(/^\s+/, '');
	for (var i = str.length - 1; i >= 0; i--) {
		if (/\S/.test(str.charAt(i))) {
			str = str.substring(0, i + 1);
			break;
		}
	}
	return str;
}
