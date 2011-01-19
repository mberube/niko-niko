(function($) {


      var NikoNikoEditor = {
        SmileyFormatter :function  (row, cell, value, columnDef, dataContext) {

            return "<img src='" + GetSmileySrc(value)+ "' class='mood-image'>";
        } ,

         GetSmileySrc :function  ( value) {
            if(value == 1) { return 'images/unhappy.png'}
            if(value == 2) { return 'images/normal.png'}
            if(value == 3) { return 'images/happy.png'}
            if(value == 4) { return 'images/nothere.png'}
            return 'images/dontknow.png' ;
        } ,


        SmileyCellEditor : function(args) {
            var $smileyImg, $picker;
            var defaultValue;

            this.init = function() {
                $smileyImg = $("<img class='mood-image'>");

                $smileyImg.appendTo(args.container);

                $picker = $("<span class='editor-mood-picker' />").appendTo(args.container);

                $picker.append("<div class='editor-mood-helper'>" +
                                    "<div class='editor-mood-wrapper'>" +
                                        "<div class='editor-mood-content' >" +
                                                "<div class='editor-mood-question'>What were your mood ?</div>" +
                                                "<div class='editor-mood-button'><input id='happy' value='3' type='image' src='images/happy.png' name='mood' class='mood-image' /><label for='happy'> Happy</label></div>"  +
                                                "<div class='editor-mood-button'><input id='normal' value='2' type='image' src='images/normal.png' name='mood' class='mood-image' /><label for='normal'> Normal</label></div>" +
                                                "<div class='editor-mood-button'><input id='unhappy' value='1' type='image'  src='images/unhappy.png' name='mood' class='mood-image' /><label for='unhappy'> Unhappy</label></div>" +
                                                "<div class='editor-mood-button'><input id='dontknow' value='0' type='image' src='images/dontknow.png' name='mood' class='mood-image'  /><label for='dontknow'> Don't know</label></div>" +
                                                "<div class='editor-mood-button'><input id='nothere' value='4' type='image' src='images/nothere.png' name='mood' class='mood-image' /><label for='nothere'> Not here</label></div>" +
                                         "</div>" +
                                    "</div>" +
                                "</div>");


                $picker.find(".editor-mood-content input[name='mood']").bind("click", function(e) {
                    $smileyImg.val($(this).attr("value"));
                    $smileyImg.attr('src',GetSmileySrc($(this).attr("value")));
                    $picker.hide();
                })

                $smileyImg.click(function(){
                    $picker.show();
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