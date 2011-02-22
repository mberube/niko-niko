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
        {id:"username",name:"Username",field:"username",width:120},
        {id:"day1",  field:"day1",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day2",  field:"day2",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day3",  field:"day3",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day4",  field:"day4",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day5",  field:"day5",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day6", field:"day6",formatter:SmileyFormatter, editor:SmileyCellEditor},
        {id:"day7",  field:"day7",formatter:SmileyFormatter, editor:SmileyCellEditor}

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

        slickGrid.onCellChange = function(row,cell,dataContext) {
         var username = dataContext.username;
         var date = this.getColumns()[cell].name;
         var dateId = this.getColumns()[cell].id;
         var mood = dataContext[dateId];
         $.post('calendar/mood', {user: username,date:date,mood:mood});
        }

        this.fill();


	}


	this.fill = function() {
		$.getJSON('calendar/week', "", function(week) {

            var data = [];
            for (var i=0; i < week.rows.length; i++) {

                var d = (data[i] = {});

				d["username"] = week.rows[i]["username"];
                d["day1"] = week.rows[i]["moods"][0];
                d["day2"] = week.rows[i]["moods"][1];
                d["day3"] = week.rows[i]["moods"][2];
                d["day4"] = week.rows[i]["moods"][0];
                d["day5"] = week.rows[i]["moods"][0];
                d["day6"] = week.rows[i]["moods"][0];
                d["day7"] = week.rows[i]["moods"][0];

			}
            var columns = slickGrid.getColumns();
            for (var i=0; i < week.columnTitles.length; i++) {
                columns[i+1]["name"] =  week.columnTitles[i]["date"];
			}

            slickGrid.setColumns(columns)
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
