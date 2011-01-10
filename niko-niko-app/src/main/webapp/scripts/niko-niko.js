var addUserBoxSelector = 'input[id="newUser"]:first';
var calendarSelector = '#calendar:first';

var backend;
var calendar;
var addUserBox;


$(document).ready(function() {

    backend = new Backend();
    calendar = new Calendar();
    calendar.initialize();

    addUserBox = new AddUserBox();
    addUserBox.initialize();

});

function Calendar(){


    this.initialize =  function(){
          this.fill();
    }

    this.fill = function(){
         $(calendarSelector).table({
            data : backend.users()
            });

    }

}


function AddUserBox(){


    this.initialize =  function(){

        $(addUserBoxSelector).addClass("unfocusAddUserBox");

        $(addUserBoxSelector).focus(function() {
             $(this).removeClass("unfocusAddUserBox").addClass("focusAddUserBox");
             this.value = '';

        });

        $(addUserBoxSelector).blur(function() {
            backend.saveUser(this.value);
            $(this).removeClass("focusAddUserBox").addClass("unfocusAddUserBox");
	        this.value =  this.defaultValue;
        });

        $(addUserBoxSelector).keypress(function (keyPressed) {
            var isEnterKeyPressed =  (keyPressed.which && keyPressed.which == 13) || (keyPressed.keyCode && keyPressed.keyCode == 13);
            if (isEnterKeyPressed) {$(this).blur();}
        });

    }


}



function Backend (){

    this.innerList = new Array();

    this.saveUser = function (newUsername){
        var username = $.trim(newUsername);
        this.innerList[this.innerList.length] = new Array(username);
        calendar.fill();
    }

    this.users = function(){
          return this.innerList;
    }
}


