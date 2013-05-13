//Lightbox Code
$(document).ready(function() { 
	$("a.fancylightbox").fancybox({
		'titleShow'     : true,
		'titlePosition'	: 'over',
		'transitionIn'	: 'fade',
		'transitionOut'	: 'fade',
		'overlayOpacity': '0.5',
		'overlayColor'  :  '#333'
	});
	$("a[rel=portfolio]").fancybox({
		'titleShow'     : true,
		'titlePosition'	: 'over',
		'transitionIn'	: 'elastic',
		'transitionOut'	: 'elastic',
		'overlayOpacity': '0.5',
		'overlayColor'  :  '#333'
	});
	$(".videolightbox").fancybox({
		'titleShow'     : false,
		'transitionIn'		: 'fade',
		'transitionOut'		: 'fade',
		'type'				: 'iframe',
		'overlayOpacity': '0.5',
		'overlayColor'  :  '#333'
	});
	$(".buttonvideolightbox").fancybox({
		'titleShow'     : false,
		'transitionIn'		: 'fade',
		'transitionOut'		: 'fade',
		'type'				: 'iframe',
		'overlayOpacity': '0.5',
		'overlayColor'  :  '#333'
	});
	$(".iframe-popup").fancybox({
			'width'				: '75%',
			'height'			: '75%',
	        'autoScale'     	: false,
	        'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe'
		});
});

//Transparency Adjustments
$(document).ready(function() {  
            $('.transparent').each(function() {
                $(this).hover(
                    function() {
                        $(this).stop().animate({ opacity: 0.6 }, 400);
                    },
                   function() {
                       $(this).stop().animate({ opacity: 1.0 }, 400);
                   })
                });
});




//Hover Menu
$(document).ready(function()
{
	slide(".sf-menu", 7, 0, 150, .8);
});

function slide(navigation_id, pad_out, pad_in, time, multiplier)
{
	// creates the target paths
	var list_elements = navigation_id + " li.sliding-element";
	var link_elements = list_elements + " a";
	
	// initiates the timer used for the sliding animation
	var timer = 0;
	
	// creates the slide animation for all list elements 
	$(list_elements).each(function(i)
	{
		// margin left = - ([width of element] + [total vertical padding of element])
		$(this).css("margin-left","-180px");
		// updates timer
		timer = (timer*multiplier + time);
		$(this).animate({ marginLeft: "0" }, timer);
		$(this).animate({ marginLeft: "0" }, timer);
		$(this).animate({ marginLeft: "0" }, timer);
	});

	// creates the hover-slide effect for all link elements 		
	$(link_elements).each(function(i)
	{
		$(this).hover(
		function()
		{
			$(this).animate({ paddingLeft: pad_out }, 150);
		},		
		function()
		{
			$(this).animate({ paddingLeft: pad_in }, 150);
		});
	});
}




//IE7 Z-Index Fix
$(document).ready(function() {
    if ($.browser.msie && parseInt($.browser.version) == 7) {
        var zIndexNumber = 1000;

        $('div').each(function() {
            $(this).css('zIndex', zIndexNumber);
            zIndexNumber -= 10;
        });
    }
});




//Full Screen Background Image Load
$(window).load(function() {    

        var theWindow        = $(window),
            $bg              = $("#bg"),
            aspectRatio      = $bg.width() / $bg.height();

        function resizeBg() {

                if ( (theWindow.width() / theWindow.height()) < aspectRatio ) {
                    $bg
                        .removeClass()
                        .addClass('bgheight');
                } else {
                    $bg
                        .removeClass()
                        .addClass('bgwidth');
                }

        }

        theWindow.resize(function() {
                resizeBg();
        }).trigger("resize");

});

