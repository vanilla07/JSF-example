$(function() {
	$('#slides').slidesjs({
		width: 1600,
        height: 500,
        play: {
        	active: true,
        	auto: true,
        	interval: 15000,
        	swap: true
        }
	});
	setInterval(function() {
		if ($("#img1").css("left") !== "0px") {
			$("#description1").fadeIn();
			$("#description2").fadeOut();
			$("#description3").fadeOut();
		} else if ($("#img2").css("left") !== "0px") {
			$("#description1").fadeOut();
			$("#description2").fadeIn();
			$("#description3").fadeOut();
		} else if ($("#img3").css("left") !== "0px") {
			$("#description1").fadeOut();
			$("#description2").fadeOut();
			$("#description3").fadeIn();
		}
	}, 200);
});