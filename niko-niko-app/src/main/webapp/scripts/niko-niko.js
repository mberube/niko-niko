var addUserBoxSelector = 'input[id="newUser"]:first';
var calendarSelector = '#calendar:first';

var calendar;
var addUserBox;


$(document).ready(function() {
	calendar = new Calendar();


	addUserBox = new AddUserBox();
	addUserBox.initialize();


});



function Calendar() {

    var slickGrid;

	this.constructor = function() {


        var columns = [
        {id:"username",
            name:"Username",
            field:"username",
            width:120
            },

        {id:"day1", name:"Mon 1 jan", field:"day1",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day2", name:"Tues 2 jan", field:"day2"},
        {id:"day3", name:"Wen 3 jan", field:"day3"},
        {id:"day4", name:"Thurs 4 jan", field:"day4"},
        {id:"day5", name:"Fri 5 jan", field:"day5"},
        {id:"day6", name:"Mon 6 jan", field:"day6"},
        {id:"day7", name:"Tues 7 jan", field:"day7"}

        ];

        var options = {
            enableCellNavigation: false,
            autoHeight: true,
            editable: true,
            enableAutoTooltips:false,
			enableCellNavigation: true,
            enableColumnReorder: false
        };

        slickGrid = new Slick.Grid($("#myGrid"), [], columns, options);

        this.fill();


	}




	this.fill = function() {
		$.getJSON('calendar/users', "", function(users) {

			var datafromServer = users.map(function(user) {
					return [ user ];  });
            var data = [];
            for (var i=0; i < datafromServer.length; i++) {

                var d = (data[i] = {});

				d["username"] = datafromServer[i][0];
                d["day1"] = 0;

			}

            slickGrid.setData(data,true);
            slickGrid.resizeCanvas();
            slickGrid.render();
		});
	}

    this.constructor();
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
