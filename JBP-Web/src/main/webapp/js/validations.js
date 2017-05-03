// ===================================================================
// Author: Matt Kruse <matt@mattkruse.com>
// WWW: http://www.mattkruse.com/
//
// NOTICE: You may use this code for any purpose, commercial or
// private, without any further permission from the author. You may
// remove this notice from your final code if you wish, however it is
// appreciated by the author if at least my web site address is kept.
//
// You may *NOT* re-distribute this code in any way except through its
// use. That means, you can include it in your product, or your web
// site, or any other form where the code is actually being used. You
// may not put the plain javascript up on your site for download or
// include it in your javascript libraries for download. 
// If you wish to share this code with others, please just point them
// to the URL instead.
// Please DO NOT link directly to my .js files from your site. Copy
// the files to your server and use them there. Thank you.
// ===================================================================

function LTrim(str){if(str==null){return null;}for(var i=0;str.charAt(i)==" ";i++);return str.substring(i,str.length);}
function RTrim(str){if(str==null){return null;}for(var i=str.length-1;str.charAt(i)==" ";i--);return str.substring(0,i+1);}
function Trim(str){return LTrim(RTrim(str));}
function LTrimAll(str){if(str==null){return str;}for(var i=0;str.charAt(i)==" " || str.charAt(i)=="\n" || str.charAt(i)=="\t";i++);return str.substring(i,str.length);}
function RTrimAll(str){if(str==null){return str;}for(var i=str.length-1;str.charAt(i)==" " || str.charAt(i)=="\n" || str.charAt(i)=="\t";i--);return str.substring(0,i+1);}
function TrimAll(str){return LTrimAll(RTrimAll(str));}
function isNull(val){return(val==null);}
function isBlank(val){if(val==null){return true;}for(var i=0;i<val.length;i++){if((val.charAt(i)!=' ')&&(val.charAt(i)!="\t")&&(val.charAt(i)!="\n")&&(val.charAt(i)!="\r")){return false;}}return true;}
function isInteger(val){if(isBlank(val)){return false;}for(var i=0;i<val.length;i++){if(!isDigit(val.charAt(i))){return false;}}return true;}
function isNumeric(val){return(parseFloat(val,10)==(val*1));}
function isArray(obj){return(typeof(obj.length)=="undefined")?false:true;}
function isDigit(num){if(num.length>1){return false;}var string="1234567890";if(string.indexOf(num)!=-1){return true;}return false;}
function setNullIfBlank(obj){if(isBlank(obj.value)){obj.value="";}}
function setFieldsToUpperCase(){for(var i=0;i<arguments.length;i++){arguments[i].value = arguments[i].value.toUpperCase();}}
function disallowBlank(obj){var msg=(arguments.length>1)?arguments[1]:"";var dofocus=(arguments.length>2)?arguments[2]:false;if(isBlank(getInputValue(obj))){if(!isBlank(msg)){alert(msg);}if(dofocus){if(isArray(obj) &&(typeof(obj.type)=="undefined")){obj=obj[0];}if(obj.type=="text"||obj.type=="textarea"||obj.type=="password"){obj.select();}obj.focus();}return true;}return false;}
function disallowModify(obj){var msg=(arguments.length>1)?arguments[1]:"";var dofocus=(arguments.length>2)?arguments[2]:false;if(getInputValue(obj)!=getInputDefaultValue(obj)){if(!isBlank(msg)){alert(msg);}if(dofocus){if(isArray(obj) &&(typeof(obj.type)=="undefined")){obj=obj[0];}if(obj.type=="text"||obj.type=="textarea"||obj.type=="password"){obj.select();}obj.focus();}setInputValue(obj,getInputDefaultValue(obj));return true;}return false;}
function commifyArray(obj,delimiter){if(typeof(delimiter)=="undefined" || delimiter==null){delimiter = ",";}var s="";if(obj==null||obj.length<=0){return s;}for(var i=0;i<obj.length;i++){s=s+((s=="")?"":delimiter)+obj[i].toString();}return s;}
function getSingleInputValue(obj,use_default,delimiter){switch(obj.type){case 'radio': case 'checkbox': return(((use_default)?obj.defaultChecked:obj.checked)?obj.value:null);case 'text': case 'hidden': case 'textarea': return(use_default)?obj.defaultValue:obj.value;case 'password': return((use_default)?null:obj.value);case 'select-one':
if(obj.options==null){return null;}if(use_default){var o=obj.options;for(var i=0;i<o.length;i++){if(o[i].defaultSelected){return o[i].value;}}return o[0].value;}if(obj.selectedIndex<0){return null;}return(obj.options.length>0)?obj.options[obj.selectedIndex].value:null;case 'select-multiple':
if(obj.options==null){return null;}var values=new Array();for(var i=0;i<obj.options.length;i++){if((use_default&&obj.options[i].defaultSelected)||(!use_default&&obj.options[i].selected)){values[values.length]=obj.options[i].value;}}return(values.length==0)?null:commifyArray(values,delimiter);}alert("FATAL ERROR: Field type "+obj.type+" is not supported for this function");return null;}
function getSingleInputText(obj,use_default,delimiter){switch(obj.type){case 'radio': case 'checkbox': 	return "";case 'text': case 'hidden': case 'textarea': return(use_default)?obj.defaultValue:obj.value;case 'password': return((use_default)?null:obj.value);case 'select-one':
if(obj.options==null){return null;}if(use_default){var o=obj.options;for(var i=0;i<o.length;i++){if(o[i].defaultSelected){return o[i].text;}}return o[0].text;}if(obj.selectedIndex<0){return null;}return(obj.options.length>0)?obj.options[obj.selectedIndex].text:null;case 'select-multiple':
if(obj.options==null){return null;}var values=new Array();for(var i=0;i<obj.options.length;i++){if((use_default&&obj.options[i].defaultSelected)||(!use_default&&obj.options[i].selected)){values[values.length]=obj.options[i].text;}}return(values.length==0)?null:commifyArray(values,delimiter);}alert("FATAL ERROR: Field type "+obj.type+" is not supported for this function");return null;}
function setSingleInputValue(obj,value){switch(obj.type){case 'radio': case 'checkbox': if(obj.value==value){obj.checked=true;return true;}else{obj.checked=false;return false;}case 'text': case 'hidden': case 'textarea': case 'password': obj.value=value;return true;case 'select-one': case 'select-multiple':
var o=obj.options;for(var i=0;i<o.length;i++){if(o[i].value==value){o[i].selected=true;}else{o[i].selected=false;}}return true;}alert("FATAL ERROR: Field type "+obj.type+" is not supported for this function");return false;}
function getInputValue(obj,delimiter){var use_default=(arguments.length>2)?arguments[2]:false;if(isArray(obj) &&(typeof(obj.type)=="undefined")){var values=new Array();for(var i=0;i<obj.length;i++){var v=getSingleInputValue(obj[i],use_default,delimiter);if(v!=null){values[values.length]=v;}}return commifyArray(values,delimiter);}return getSingleInputValue(obj,use_default,delimiter);}
function getInputText(obj,delimiter){var use_default=(arguments.length>2)?arguments[2]:false;if(isArray(obj) &&(typeof(obj.type)=="undefined")){var values=new Array();for(var i=0;i<obj.length;i++){var v=getSingleInputText(obj[i],use_default,delimiter);if(v!=null){values[values.length]=v;}}return commifyArray(values,delimiter);}return getSingleInputText(obj,use_default,delimiter);}
function getInputDefaultValue(obj,delimiter){return getInputValue(obj,delimiter,true);}
function isChanged(obj){return(getInputValue(obj)!=getInputDefaultValue(obj));}
function setInputValue(obj,value){var use_default=(arguments.length>1)?arguments[1]:false;if(isArray(obj)&&(typeof(obj.type)=="undefined")){for(var i=0;i<obj.length;i++){setSingleInputValue(obj[i],value);}}else{setSingleInputValue(obj,value);}}
function isFormModified(theform,hidden_fields,ignore_fields){if(hidden_fields==null){hidden_fields="";}if(ignore_fields==null){ignore_fields="";}var hiddenFields=new Object();var ignoreFields=new Object();var i,field;var hidden_fields_array=hidden_fields.split(',');for(i=0;i<hidden_fields_array.length;i++){hiddenFields[Trim(hidden_fields_array[i])]=true;}var ignore_fields_array=ignore_fields.split(',');for(i=0;i<ignore_fields_array.length;i++){ignoreFields[Trim(ignore_fields_array[i])]=true;}for(i=0;i<theform.elements.length;i++){var changed=false;var name=theform.elements[i].name;if(!isBlank(name)){var type=theform.elements[i].type;if(!ignoreFields[name]){if(type=="hidden"&&hiddenFields[name]){changed=isChanged(theform[name]);}else if(type=="hidden"){changed=false;}else{changed=isChanged(theform[name]);}}}if(changed){return true;}}return false;}
function clearField(type, fieldName, index) {
	if (type == "TextBox") {
		eval("frmData."+fieldName+".value=''");
	} else if (type == "Combo") {
		eval("frmData."+fieldName+".selectedIndex=0");
	} else if (type == "Radio") {
		eval("frmData."+fieldName+"["+index+"].checked = true");
	} else if (type == "CheckBox") {
		eval("frmData."+fieldName+".checked = false");
	}
}
function filterInput(filterType, evt, allowDecimal, decNum){ 
    var keyCode, Char, inputField, filter = ''; 
    var alpha = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'; 
    var num   = '0123456789'; 
    var minus = '-';
    if(window.event){ 
        keyCode = window.event.keyCode; 
        evt = window.event; 
    }else if (evt)keyCode = evt.which; 
    else return true; 
    if(filterType == 0) filter = alpha; 
    else if(filterType == 1) filter = num; 
    else if(filterType == 2) filter = alpha + num; 
    else if(filterType == 3) filter = num + minus; 
    if(filter == '')return true; 
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget; 	
    var allowKey = new Array(0,8,9,13,27,35,36,37,38,39,40);
    var i;
    for(i=0; i<allowKey.length; i++){
    	if(keyCode==null || keyCode==allowKey[i])
    		return true; 
    }
    Char = String.fromCharCode(keyCode); 
    if((filter.indexOf(Char) > -1)) {
		if (decNum && inputField.value.indexOf('.') > -1) {
			var str = inputField.value;
			var afterDec = str.substring(inputField.value.indexOf('.'));
			var currDecNum =afterDec.length;
			if (currDecNum <= decNum) {
				return true; 
			} else {
				return false; 
			}
		}  else {
			return true; 
		}		
		return true; 
    } else if((filterType == 1 || filterType == 3) && allowDecimal && (Char == '.') && inputField.value.indexOf('.') == -1) return true;
	else return false; 
}
function decimalFormatting(evt, pre, suf){
	
	var keyCode, Char, inputField;
	var filter = '0123456789';
	var start = getStart(evt);
	var end = getEnd(evt);
	
	if(window.event){ 
        keyCode = window.event.keyCode; 
        evt = window.event; 
    }else if (evt)keyCode = obj.which; 
    else return true; 
	
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget; 	
    if((keyCode==null) || (keyCode==0) || (keyCode==8) || (keyCode==9) || (keyCode==13) || (keyCode==27) )return true; 
    Char = String.fromCharCode(keyCode);
    
    var text = inputField.value;
    var pointIndex;
   
    if(end - start > 0){
		text = text.substring(0,start)+text.substring(end,text.length);
	}
    pointIndex = text.indexOf('.');
    if((filter.indexOf(Char) != -1)) {
    	
    	if(pointIndex == -1){
    		if(text.length >= pre && !(Char == '.'))
    			return false;
    	}else{
    		if(pointIndex-pre >= 0 && start <= pointIndex)
    			return false;
    		if(text.length-1-pointIndex >= suf && start > pointIndex)
    			return false;
    	}
    	
    }else if((Char == '.') && pointIndex == -1 && inputField.value.length > 0){
    	return true;
    }else{
    	return false;
    }
    
    return true;

}
function getStart(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}
function getEnd(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveStart('character', -o.value.length)
		return r.text.length
	} else return o.selectionEnd
}
function validateDecimalFormat(obj, pre, suf){
	var num = /^\d/;
	var message = new Array();
	var count = 0;
	var text = obj.value;
	
	if(text != ''){
		
		if(!num.test(text)){
			message[count] = 'value is not decimal format.';
			count++;
		}else if(text.indexOf('.') == -1){
			if(text.length > pre){
				message[count] = 'precision number rather than '+pre+' position.';
				count++;
			}
		}else{
			var parts = text.split('.');

			if(parts.length > 2){
				message[count] = 'value is not decimal format.';
				count++;
			}else if(parts.length == 2){
				if(parts[1].length == 0){
					message[count] = 'value is not decimal format.';
					count++;
				}else if(parts[0].length > pre){
					message[count] = 'precision number rather than '+pre+' position.';
					count++;
				}else if(parts[1].length > suf){
					message[count] = 'pointing number rather than '+suf+' position.';
					count++;
				}
			}
		}
		
	}
	
	return message;
		
}
function numberOnly(event) {
   return filterInput(1, event);
}
function decimalOnly(event) {
   return filterInput(1, event, true);
}
function decimalLimitedOnly(event, len) {
   return filterInput(1, event, true, len);
}
function letterEnOnly(event) {
   return filterInput(0, event);
}
function letterEnNumberOnly(event) {
   return filterInput(2, event);
}
function decimalMunusLimitedOnly(event, len) {
   return filterInput(3, event, true, len);
}


function formatDecimal(data) {
	data = deFormatNumber("" + data);
	var strRight ;
	var strLeft =  data.substring(0, data.indexOf('.'));
	var flag = false;
	var minut = '';
	if(data.indexOf('.')<0){
	   strLeft  = data;
	   strRight = "00";
	}else{
	  var length;
	  var index = data.indexOf('.');
	  strRight = data.substring(index,data.length);
	  length = data.substring(index,data.length).length-1;
	  strRight = data.substring(index+1,index+3);

	  if(length==1){
	     strRight = strRight+"0";
	  }else if(length >= 3){
	     if(parseInt(data.substring(index+3,index+4))>=5){
		   strRight =  parseInt(strRight)+1;
		 }
	  }
	      
	}

	var  n =  strLeft.length;
	if(strLeft.substring(0,1)=='-'){
	 strLeft = strLeft.substring(1,n) 
	 flag = true;
	 n -= 1;
	}
	var tmp = "";
	
	for(var i = 0;  i < n; ++i) {
		if ( i !=0 && (i %3) == 0 && i ) {
			tmp = strLeft.charAt( n - i -1) + "," + tmp;
		} else {
			tmp = strLeft.charAt( n - i -1)  + tmp;
		}
	}
	if(flag) minut='-';
	return minut + tmp + "." +strRight;
}





function deFormatDecimal(data){
	if (data) {
		var n = data.length;
		var tmp = "";
		for (var i = 0; i < n; ++i ) {
			if (data.charAt(i) != ',' ) tmp += data.charAt(i);
		}
		return tmp;
	} else {
		return "";
	}
}
function formatDecimalObj(obj) {
	obj.value = formatDecimal(obj.value);
}
function deFormatDecimalObj(obj) {
	obj.value = deFormatDecimal(obj.value);
}


function formatNumberObj(obj) {
	obj.value = formatNumber(obj.value);
}
function deFormatNumberObj(obj) {
	obj.value = deFormatNumber(obj.value);
}


function formatNumber(data) {
	data = deFormatNumber("" + data);
	var strRight ;
	var strLeft =  data.substring(0, data.indexOf('.'));
	var flag = false;
	var minut = '';
	if(data.indexOf('.')<0){
	   strLeft = data;
	   strRight="";
	}else{
	  strRight = data.substring(data.indexOf('.'),data.length);
	}
	var  n =  strLeft.length;
	if(strLeft.substring(0,1)=='-'){
	 strLeft = strLeft.substring(1,n) 
	 flag = true;
	 n -= 1;
	}
	var tmp = "";
	
	for(var i = 0;  i < n; ++i) {
		if ( i !=0 && (i %3) == 0 && i ) {
			tmp = strLeft.charAt( n - i -1) + "," + tmp;
		} else {
			tmp = strLeft.charAt( n - i -1)  + tmp;
		}
	}
	if(flag) minut='-';
	return minut+tmp+strRight;
}



function deFormatNumber(data){
	if (data) {
		var n = data.length;
		var tmp = "";
		for (var i = 0; i < n; ++i ) {
			if (data.charAt(i) != ',' ) tmp += data.charAt(i);
		}
		return tmp;
	} else {
		return "";
	}
}

function keyEng(obj) {
           if((event.keyCode > 48 &&event.keyCode < 57) ){
		      event.returnValue = false;
			  obj.focus();
		    }else if (event.keyCode > 160 && event.keyCode != 13 &&event.keyCode != 32 ) {
		       //alert('Not support thai character.');
			   event.returnValue = false;
			   obj.focus();
		   }else{
		    	event.returnValue = true;
		  }
}
function keyThai(obj) {
         if((event.keyCode > 48 && event.keyCode < 57) ){
		      event.returnValue = false;
			  obj.focus();
		    }else if (event.keyCode < 160 && event.keyCode != 13 && event.keyCode != 32 ) {
			   event.returnValue = false;
			    obj.focus();
		   }else{
		    	event.returnValue = true;
		  }
}
function limitText(limitField, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    } 
}

function checklength(obj,length ){
	return ( obj.value.length < length );
}

var letters=' ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
var numbers='1234567890';
var signs=',.:;@-\'';
var mathsigns='+-=()*/';
var custom='<>#$%&?';
var allow = letters + numbers + signs + mathsigns + custom;
	
function isEngKeyPress(e) {
	var k;
	k=document.all?parseInt(e.keyCode): parseInt(e.which);
	return (allow.indexOf(String.fromCharCode(k))!=-1);
}

function isEngChar(c) {
	return (allow.indexOf(c)!=-1);
}
	
function isMaxByte(e,value,max){
	if(e.keyCode == 8 || e.keyCode == 46 
			|| e.keyCode == 37 || e.keyCode == 39) //delete, left, rigth
		return true;

	var engCounter = true;
	var byteCounter = 0;

    var loopCounter = value.length;
    for (loopCounter  ;loopCounter > 0 ;loopCounter -- ) { 
    	if(!isEngChar(value.substring(loopCounter-1, loopCounter))){
    		engCounter = false;
    		break;
    	}
 	}

    byteCounter = value.length;
 	if(!engCounter)
 		byteCounter = byteCounter * 3;
 
	var engKeyPressed = isEngKeyPress(e);
	if(byteCounter > max)
		return false;
	else{
		if(engCounter){
			if(engKeyPressed)
				byteCounter = byteCounter + 1;
			else
				byteCounter = (byteCounter * 3) + 3;
		} else 
			byteCounter = (byteCounter + 3);

		return byteCounter <= max;
	}
}

function limitTextUnicode( limitField, limitNum ) {
 	var engCounter = true;
    var value = limitField.value;
    var loopCounter = value.length;
    for (loopCounter  ;loopCounter > 0 ;loopCounter -- ) { 
    	if(!isEngChar(value.substring(loopCounter-1, loopCounter))){
    		engCounter = false;
    		break;
    	}
 	}
 	if(!engCounter){
	    limitNum = parseInt(limitNum/3);
	} 
	if (value.length > limitNum) {
	    alert('Valid range is between 0 and '+limitNum+' characters.');
	    limitField.value = value.substring(0, limitNum );
	} 
}

function decimalMinusFormatting(evt, pre, suf){
	
	var keyCode, Char, inputField;
	var filter = '0123456789';
	var start = getStart(evt);
	var end = getEnd(evt);
	
	if(window.event){ 
        keyCode = window.event.keyCode; 
        evt = window.event; 
    }else if (evt)keyCode = obj.which; 
    else return true; 
	
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget; 	
    if((keyCode==null) || (keyCode==0) || (keyCode==8) || (keyCode==9) || (keyCode==13) || (keyCode==27) )return true; 
    Char = String.fromCharCode(keyCode);
    
    var text = inputField.value;
    var pointIndex;
    
    if(end - start > 0){
		text = text.substring(0,start)+text.substring(end,text.length);
	}
    pointIndex = text.indexOf('.');
    minusIndex = text.indexOf('-');
    
    if((Char == "-") || minusIndex >= 0 ){
    	pre = pre+1;
    }
    
    if((filter.indexOf(Char) != -1)) {
    	
    	if(pointIndex == -1){
    		if(text.length >= pre && !(Char == '.'))
    			return false;
    	}else{
    		if(pointIndex-pre >= 0 && start <= pointIndex)
    			return false;
    		if(text.length-1-pointIndex >= suf && start > pointIndex)
    			return false;
    	}
    	
    }else if((Char == '.') && pointIndex == -1 && inputField.value.length > 0){
    	return true;
    }else if((Char == "-") && minusIndex == -1){ 
    	return true;
    }else{
    	return false;
    }
    
    return true;

}

function decimalSearchSpecailChar(evt, pre, suf){
	var keyCode, Char, inputField;
	var filter = '0123456789';
	var start = getStart(evt);
	var end = getEnd(evt);
	if(window.event){ 
        keyCode = window.event.keyCode; 
        evt = window.event; 
    }else if (evt)keyCode = obj.which; 
    else return true; 
	
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget; 	
    if((keyCode==null) || (keyCode==0) || (keyCode==8) || (keyCode==9) || (keyCode==13) || (keyCode==27) )return true; 
    Char = String.fromCharCode(keyCode);
    
	var text = inputField.value;
    var pointIndex;
    var ltIndex;
    var gtIndex;
	
    if(end - start > 0){
		text = text.substring(0,start)+text.substring(end,text.length);
	}
	
    pointIndex = text.indexOf('.');
    ltIndex = text.indexOf('<');
    gtIndex = text.indexOf('>');
    eqIndex = text.indexOf('=');
	
    var isSpecailChar = (( Char == ">" ||  Char == "<" ||  Char == "=" ) && ltIndex== -1 && gtIndex == -1 && start == 0) ;
	
	if( isSpecailChar ){
		return true;
	}else if( (filter.indexOf(Char) != -1) ) {
		if( text.indexOf("<")==0 || text.indexOf(">")==0 || text.indexOf("=")==0 ){
			pre = pre+1;
		}
    	if( pointIndex == -1 ){
    		if( text.length >= pre && !(Char == '.'))
    			return false;
    	}else{
    		if(pointIndex-pre >= 0 && start <= pointIndex)
    			return false;
    		if(text.length-1-pointIndex >= suf && start > pointIndex)
    			return false;
    	}
    }else if((Char == '.') && pointIndex == -1 && inputField.value.length > 0){
    	return true;
    }else{
    	return false;
    }
    return true;
}


