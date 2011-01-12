var addUserBoxSelector = 'input[id="newUser"]:first';
var calendarSelector = '#calendar:first';

var calendar;
var addUserBox;

$(document).ready(function() {
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
		$.getJSON('calendar/users', "", function(users) {
			$(calendarSelector).table({
				replace : false,

				// transform array into array of arrays
				data : users.map(function(user) {
					return [ user ];
				})
			});
		});
	}
}

function AddUserBox() {
	$THIS = this;
	
	this.initialize = function() {

		$(addUserBoxSelector).addClass("unfocusAddUserBox");

		$(addUserBoxSelector).focus(
			function() {
				$(this).removeClass("unfocusAddUserBox").addClass("focusAddUserBox");
				this.value = '';
			});

		$(addUserBoxSelector).blur(
			function() {
				$THIS.saveUser(this.value);
				$(this).removeClass("focusAddUserBox").addClass("unfocusAddUserBox");
				this.value = this.defaultValue;
			});

		$(addUserBoxSelector).keypress( 
			function(keyPressed) {
				var isEnterKeyPressed = (keyPressed.which && keyPressed.which == 13)
					|| (keyPressed.keyCode && keyPressed.keyCode == 13);
				if (isEnterKeyPressed) {
					if (navigator.appName == 'Microsoft Internet Explorer') {
						$(calendarSelector).focus();
					} else {
						$(this).blur();
					}
				}
		});
	}

	this.saveUser = function(newUsername) {
		var username = $.trim(newUsername);
		if(username == "") {
			return;
		}
		$.post('calendar/users', {username: newUsername}, function() {
			calendar.fill();
		});
	}
}
