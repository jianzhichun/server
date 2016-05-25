function initProcessbar(txt, img) {
			var bfb = $(txt).val();
			bfb = bfb * 300 / 100;
			$(img).animate({
				width : bfb + "px"
			})

		};
		function onclickProcessbar(ep, txt, img) {
			$(img).animate({
				width : ep + "px"
			});
			var bfb = $(txt).val();
			bfb = Math.floor(ep * 100 / 300);
			$(txt).val(bfb);
		};
		function generateProcessbar(prob, txt, img) {
			initProcessbar(txt, img)
			$(prob).on("click", function(e) {
				x = $(this).offset();
				var ep = e.pageX - x.left;
				onclickProcessbar(ep, txt, img)
			})
			$(txt).blur(function() {
				initProcessbar(txt, img)
			})

		}

		function CheckNum(obj) { //验证整数
			obj = jQuery(obj);
			var tmptxt = obj.val().replace(/\D|^/g, ''); //利用正则表达式
			var maxNum = jQuery.trim(obj.attr("maxNum")); //最大值
			if (maxNum) {
				if (parseInt(maxNum) >= parseInt(tmptxt)) {
					obj.val(tmptxt);
				} else {
					obj.val("");
				}
				return;
			}
			obj.val(tmptxt);
		}

		$(function() {

			$("input.num").on("keyup", function() {//键盘输入事件
				CheckNum($(this));
			}).bind("paste", function() {//粘贴事件
				CheckNum($(this));
			}).css("ime-mode", "disabled");

			//time
			$("#calendar").bootstrapDatepickr({
				date_format : "Y-m-d"
			});
			$("#calendar1").bootstrapDatepickr({
				date_format : "Y-m-d"
			});

			//title
			generateProcessbar(".bfbt", "#title_w", ".bfbt img")
			//title_py
			generateProcessbar(".bfbtpy", "#titlepy_w", ".bfbtpy img")
			//author
			generateProcessbar(".bfba", "#author_w", ".bfba img")
			//author_py
			generateProcessbar(".bfbapy", "#authorpy_w", ".bfbapy img")
			//description
			generateProcessbar(".bfbd", "#description_w", ".bfbd img")
			//content
			generateProcessbar(".bfbc", "#content_w", ".bfbc img")

		})

		//suggest
		$("#searchInput")
				.autocomplete(
						{
							// serviceUrl: '/autosuggest/service/url',
							serviceUrl : 'search/suggest',
							lookupFilter : function(suggestion, originalQuery,
									queryLowerCase) {
								var re = new RegExp(
										'\\b'
												+ $.Autocomplete.utils
														.escapeRegExChars(queryLowerCase),
										'gi');
								return re.test(suggestion.value);
							},
							onHint : function(hint) {
								$('#autocomplete-ajax-x').val(hint);
							},
						});