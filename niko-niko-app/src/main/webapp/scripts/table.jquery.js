/*
 * jQuery Table Plugin
 * version: 1.1 (26-JAN-2010)
 * author: CREATIVE0809 <trance_creative@mail.ru>
 * 
 * Plugin provide functionality for creating table from multidimensional arrays, and manipulating rows in tables.
 * 
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */
(function($) {
	$.fn.table = function(options) {
		var defaults = {
			replace : false,
			data : []
		}, opts = $.extend(defaults, options);

		if (typeof opts.data != 'object')
			return this;

		var table = document.createElement('table');

		for ( var row_id in opts.data) {
			if (typeof opts.data[row_id] != 'object')
				continue;

			var row = table.insertRow(-1);

			for ( var col_id in opts.data[row_id]) {
				var col = row.insertCell(-1);
				$(col).html(opts.data[row_id][col_id]);
			}
		}
		this.each((function(opts, table) {
			return function() {
				if (opts.replace)
					$(this).replaceWith(table);
				else
					$(this).html(table);
			};
		})(opts, table));

		return this;
	};

	$.fn.tableInsertRows = function(options) {
		var defaults = {
			replace : false,
			index : -1,
			data : []
		}, opts = $.extend(defaults, options);

		if (typeof opts.data != 'object')
			return this;

		this.each((function(opts) {
			return function() {
				if (this.tagName != 'TABLE' || !opts.data.length)
					return;

				for ( var row_id in opts.data) {
					if (typeof opts.data[row_id] != 'object')
						continue;

					var curr_index;
					if (opts.index != -1) {
						curr_index = row_id * 1 + opts.index;
						if (opts.replace) {
							this.deleteRow(curr_index);
						}
					} else
						curr_index = -1;

					var row = this.insertRow(curr_index);

					for ( var col_id in opts.data[row_id]) {
						var col = row.insertCell(-1);
						$(col).html(opts.data[row_id][col_id]);
					}
				}
			};
		})(opts));

		return this;
	};

	$.fn.tableRemoveRows = function(options) {
		var defaults = {
			from : 0,
			length : 0
		}, opts = $.extend(defaults, options);

		this.each((function(opts) {
			return function() {
				if (this.tagName != 'TABLE')
					return;
				var size = this.rows.length;
				var from = opts.from < 0 ? size - opts.from : opts.from;
				var length = Math.abs(opts.length);

				for ( var row_id = from; row_id < from + length
						&& row_id < size; row_id++)
					this.deleteRow(from);
			};
		})(opts));

		return this;
	};
})(jQuery);