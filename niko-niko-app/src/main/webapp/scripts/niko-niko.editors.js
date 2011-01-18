(function($) {

      var NikoNikoEditor = {
        SmileyFormatter :function  (row, cell, value, columnDef, dataContext) {
            if(value == 1) { return "<img src='images/bad.gif' class='miniSmiley'>"}
            if(value == 2) { return "<img src='images/bof.gif' class='miniSmiley'>"}
            if(value == 3) { return "<img src='images/good.gif' class='miniSmiley'>"}
            if(value == 4) { return "x"}
            return "?";
        } ,




        SmileyCellEditor2 : function(args) {
            var $input, $picker;
            var defaultValue;
            var scope = this;

            this.init = function() {
                $input = $("<img src='images/good.gif' class='miniSmiley' > ");

                $input.appendTo(args.container);

                $picker = $("<span class='editor-percentcomplete-picker' />").appendTo(args.container);
                $picker.append("<div class='editor-percentcomplete-helper'><div class='editor-percentcomplete-wrapper'><div class='editor-percentcomplete-slider' /><div class='editor-percentcomplete-buttons' /></div></div>");

                $picker.find(".editor-percentcomplete-buttons").append("<button val=1>Unhappy</button><br/><button val=2>Normal</button><br/><button val=3>Happy</button>");

                $input.focus().select();

                $picker.find(".editor-percentcomplete-buttons button").bind("click", function(e) {
                    $input.val($(this).attr("val"));
                    $picker.find(".editor-percentcomplete-slider").slider("value", $(this).attr("val"));
                })
            };

            this.destroy = function() {
                $input.remove();
                $picker.remove();
            };

            this.focus = function() {
                $input.focus();
            };

            this.loadValue = function(item) {
                $input.val(defaultValue = item[args.column.field]);
                $input.select();
            };

            this.serializeValue = function() {
                return parseInt($input.val(),10) || 0;
            };

            this.applyValue = function(item,state) {
                item[args.column.field] = state;
            };

            this.isValueChanged = function() {
                return (!($input.val() == "" && defaultValue == null)) && ((parseInt($input.val(),10) || 0) != defaultValue);
            };

            this.validate = function() {
                if (isNaN(parseInt($input.val(),10)))
                    return {
                        valid: false,
                        msg: "Please enter a valid positive number"
                    };

                return {
                    valid: true,
                    msg: null
                };
            };

            this.init();
        }


    };

    $.extend(window, NikoNikoEditor);

})(jQuery);