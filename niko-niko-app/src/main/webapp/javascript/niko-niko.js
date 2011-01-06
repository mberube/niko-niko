$(document).ready(function() {  
	
	var newUserInputSelection = 'input[id="newUser"]:first';
	
	initInput($(newUserInputSelection));  
    
    $(newUserInputSelection).focus(function() {  
    	focusInput(this);
    });  
    
    $(newUserInputSelection).blur(function() {  
        addUser(this.value);
        unfocusInput(this);
        
    }); 
    
    $(newUserInputSelection).keypress(function (e) {  
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {  
			$(this).blur();     	
	    } 
	});  
});


function initInput(jQueryInputElement){
	jQueryInputElement.addClass("idleField"); 
}


function focusInput(inputElement){
	 $(inputElement).removeClass("idleField").addClass("focusField");  
     if (inputElement.value == inputElement.defaultValue){  
    	 inputElement.value = '';  
     }  
     if(inputElement.value != inputElement.defaultValue){  
    	 inputElement.select();  
     }  
}

function unfocusInput(inputElement){
	$(inputElement).removeClass("focusField").addClass("idleField");  
	inputElement.value =  inputElement.defaultValue;  
}


function addUser(newUsername){
	newUsername = $.trim(newUsername);
    if (newUsername != ''){
        $("#users").html(newUsername);
    } 
}