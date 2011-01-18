(function($) {


      var NikoNikoEditor = {
        SmileyFormatter :function  (row, cell, value, columnDef, dataContext) {

            return "<img src='" + GetSmileySrc(value)+ "' class='mood-image'>";
        } ,

         GetSmileySrc :function  ( value) {
            if(value == 1) { return 'images/bad.gif'}
            if(value == 2) { return 'images/bof.gif'}
            if(value == 3) { return 'images/good.gif'}
            if(value == 4) { return 'images/x.png'}
            return 'images/interrogation.png' ;
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
                                                "What were your mood ?<br/><br/>" +
                                            "<div class='editor-mood-buttons'>" +
                                                "<input id='unhappy' value='1' type='image'  src='images/bad.gif' name='mood' class='mood-image' /><label for='unhappy'> Unhappy</label><br/>" +
                                                "<input id='normal' value='2' type='image' src='images/bof.gif' name='mood' class='mood-image' /><label for='normal'> Normal</label><br/>" +
                                                "<input id='happy' value='3' type='image' src='images/good.gif' name='mood' class='mood-image' /><label for='happy'> Happy</label><br/>"  +
                                                "<input id='dontknow' value='0' type='image' src='images/interrogation.png' name='mood' class='mood-image'  /><label for='dontknow'> Don't know</label><br/>" +
                                                "<input id='nothere' value='4' type='image' src='images/x.png' name='mood' class='mood-image' /><label for='nothere'> Not here</label><br/>" +

                                            "</div>" +
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