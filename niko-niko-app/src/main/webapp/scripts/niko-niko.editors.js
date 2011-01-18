(function($) {


      var NikoNikoEditor = {
        SmileyFormatter :function  (row, cell, value, columnDef, dataContext) {

            return "<img src='" + GetSmileySrc(value)+ "' class='miniSmiley'>";
        } ,

         GetSmileySrc :function  ( value) {
            if(value == 1) { return 'images/bad.gif'}
            if(value == 2) { return 'images/bof.gif'}
            if(value == 3) { return 'images/good.gif'}
            if(value == 4) { return 'images/x.png'}
            return 'images/interrogation.png' ;
        } ,


        SmileyCellEditor2 : function(args) {
            var $smileyImg, $picker;
            var defaultValue;
            var scope = this;

            this.init = function() {
                $smileyImg = $("<img class='miniSmiley'>");

                $smileyImg.appendTo(args.container);

                $picker = $("<span class='editor-percentcomplete-picker' />").appendTo(args.container);

                $picker.append("<div class='editor-percentcomplete-helper'><div class='editor-percentcomplete-wrapper'><div class='editor-percentcomplete-slider' /><div class='editor-percentcomplete-buttons' /></div></div>");

                $picker.find(".editor-percentcomplete-buttons").append("<button val=1>Unhappy</button><br/><button val=2>Normal</button><br/><button val=3>Happy</button>");

                $picker.find(".editor-percentcomplete-buttons button").bind("click", function(e) {
                    $smileyImg.val($(this).attr("val"));
                    $smileyImg.attr('src',GetSmileySrc($(this).attr("val")));


                })
            };

            this.destroy = function() {
                $smileyImg.remove();
                $picker.remove();
            };

            this.focus = function() {

            };

            this.loadValue = function(item) {
                $smileyImg.val(defaultValue = item[args.column.field]);
                $smileyImg.attr('src',GetSmileySrc(item[args.column.field]));
            };

            this.serializeValue = function() {
                return parseInt($smileyImg.val()) || 0;
            };

            this.applyValue = function(item,state) {
                item[args.column.field] = state;
            };

            this.isValueChanged = function() {
                return (!($smileyImg.val() == "" && defaultValue == null)) && ((parseInt($smileyImg.val()) || 0) != defaultValue);
            };

            this.validate = function() {
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