// input
$("#searchInput").focus(function () {
	$(".input-group").addClass("focus");
})

$("#searchInput").blur(function () {
	$(".input-group").removeClass("focus");
})


// tooltip
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

// -navbar-top-color-toggle	
$(".navbar-toggle").click(function () {
	$(".navbar-f-top").toggleClass("bgcolor");
})

// scrollTop
// chat the scrollTop
// $(function () {
// 	window.onscroll = function () {
// 		console.log($(document).scrollTop())
// 	}
// })


$('#onClick1').click(function(){
	$('html,body').animate({scrollTop:$('#buttonTop').offset().top}, 200);});
$('#onClick1').click(function () {
	$('#navbar').removeClass("in");
});
$('#onClick1').click(function () {
	$('.navbar-f-top').removeClass("bgcolor");
});

// animate
// $('.introduce').addClass('animated fadeInUp');



