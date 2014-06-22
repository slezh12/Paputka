$(document).ready(function() {
	$('.search-button2').click(function() {
		
		$('.arrow').hide();
		
		$(this).stop().animate({'width':'150px'}, 500, function() {
			
			$('.content-wrapper2').addClass('switch-show');
			$('.search-box2').addClass('show-search-box');
			
			setTimeout(function(){
		
				$('.content-wrapper2').removeClass('switch-show');
				$('.search-button2').removeClass('show-search-button').addClass('hide-search-button');
				$('.search-box2').addClass('showed-search-box');
			
			},480);
			
		});
	});
	
	$('.search-box2 img').click(function() {
		
		
		$('.search-button2').removeClass('hide-search-button');
		$('.search-box2').addClass('hidden-search-box');
		$('.content-wrapper2').addClass('switch-hide');
		
		$('.search-button2').addClass('show-search-button').stop().delay(500).animate({'width':'50px'}, 500, function() {
			
			$('.content-wrapper2').removeClass('switch-hide');
			$('.search-button2').removeClass('show-search-button');
			$('.search-box2').removeClass('show-search-box showed-search-box hidden-search-box');
			
			$('.arrow').show();
			
		});
	});

	if(!Modernizr.input.placeholder){
		$("input").each( function(){
				
				thisPlaceholder = $(this).attr("placeholder");
				
				if(thisPlaceholder!=""){
					
					$(this).val(thisPlaceholder);
					
					$(this).focus(function(){ if($(this).val() == thisPlaceholder) $(this).val(""); });
					$(this).blur(function(){ if($(this).val()=="") $(this).val(thisPlaceholder); });
				}
		});
	}
});