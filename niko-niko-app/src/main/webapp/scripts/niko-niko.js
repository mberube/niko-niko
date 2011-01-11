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

function Calendar() {

	this.initialize = function() {
		this.fill();
	}

	this.fill = function() {
		backend.users()
		
//		$(calendarSelector).table({
//			replace : false,
//			//data : backend.users()
//			data : [["bob"], ["bob3"]]
//		});

	}

}

function AddUserBox() {

	this.initialize = function() {

		$(addUserBoxSelector).addClass("unfocusAddUserBox");

		$(addUserBoxSelector).focus(
				function() {
					$(this).removeClass("unfocusAddUserBox").addClass(
							"focusAddUserBox");
					this.value = '';

				});

		$(addUserBoxSelector).blur(
				function() {
					backend.saveUser(this.value);
					$(this).removeClass("focusAddUserBox").addClass(
							"unfocusAddUserBox");
					this.value = this.defaultValue;
				});

		$(addUserBoxSelector)
				.keypress(
						function(keyPressed) {

							var isEnterKeyPressed = (keyPressed.which && keyPressed.which == 13)
									|| (keyPressed.keyCode && keyPressed.keyCode == 13);
							if (isEnterKeyPressed) {

								if (navigator.appName == 'Microsoft Internet Explorer')
									$(calendarSelector).focus();
								else
									$(this).blur();
							}
						});

	}

}

function Backend() {

	this.innerList = new Array();

	this.saveUser = function(newUsername) {
		var username = $.trim(newUsername);
		this.innerList[this.innerList.length] = new Array(username);
		calendar.fill();
	}
	this.users = function() {
		$.getJSON('calendar/users', "", function(users) {
			$(calendarSelector).table({
				replace : false,

				// transform array into array of arrays
				data : users.map(function(user) {
					return [user];
				})
			});
		});
	}
}
