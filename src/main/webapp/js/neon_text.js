/*------------------------------------------------------------*/
/*- textColor - Цвет текста ----------------------------------*/
/*- textSize - Размер текста ---------------------------------*/
/*- neonHighlight - цвет первой начала свечения --------------*/
/*- neonHighlightColor цвет неоновой подсветки ---------------*/
/*- neonHighlightHover - цвет неоновой подсветки при hover ---*/
/*- neonFontHover - цвет шрифта при hover --------------------*/
/*------------------------------------------------------------*/
jQuery.fn.neonText = function(options){
  var options = jQuery.extend({
		textColor: '#FFFFFF',
		textSize: '40pt', 
		neonHighlight: '#FFFFFF', 
		neonHighlightColor: '#FF00DE',
		neonHighlightHover: '#00FFFF', 
		neonFontHover: '#FFFFFF' 
	},options)
	return this.each(function() {
		jQuery(this).css('color', options.textColor)
			.css('font-size', options.textSize)
			.css('text-shadow','0 0 10px '+options.neonHighlight+',0 0 20px '+options.neonHighlight+',0 0 30px '+options.neonHighlight +',0 0 40px '+options.neonHighlightColor+',0 0 70px '+options.neonHighlightColor+',0 0 80px '+options.neonHighlightColor+',0 0 100px '+options.neonHighlightColor)
			.hover(
				function () {
					jQuery(this)
						.css('text-shadow','0 0 10px '+options.neonHighlight+',0 0 20px '+options.neonHighlight+',0 0 30px '+options.neonHighlight+',0 0 40px '+options.neonHighlightHover+',0 0 70px '+options.neonHighlightHover+',0 0 80px '+options.neonHighlightHover+',0 0 100px '+options.neonHighlightHover)
						.css('color', options.neonFontHover);
				},
				function () {
					jQuery(this)
						.css('color', options.textColor)
						.css('text-shadow','0 0 10px ' +options.neonHighlight + ',0 0 20px ' +options.neonHighlight + ',0 0 30px ' +options.neonHighlightColor + ',0 0 40px ' +options.neonHighlightColor +  ',0 0 70px ' +options.neonHighlightColor +  ',0 0 80px ' +options.neonHighlightColor + ',0 0 100px ' +options.neonHighlightColor)
				}
			);
	});
};
