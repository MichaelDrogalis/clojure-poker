/* Foundation v2.2.1 http://foundation.zurb.com */

var delay = 300;

function showCard(url, index, id) {
    $.get(url,
    function(data) {
	var card = data[index];
	var suit = card["suit"];
        var name = card["rank"]["name"];
        
        $(id).addClass(name + "-of-" + suit);
	$(id).removeClass("facedown-card");
    });
}

function showPlayer1Card1() {
    showCard("/session/player-1", 0, "#card-1");
}

function showPlayer1Card2() {
    showCard("/session/player-1", 1, "#card-5");
}

function showPlayer2Cards() {
    showCard("/session/player-2", 0, "#card-2");
    showCard("/session/player-2", 1, "#card-6");
}

function showEveryonesHand() {
    showPlayer2Cards();
}

$(document).ready(function() {
    $("#play-hand").click(function() {
	showEveryonesHand();
    });
    
    $("#card-1").animate({
	left: '+=250', top: '+=50'
    }, delay).promise().done(showPlayer1Card1).promise().done(function() {
        $("#card-2").animate({
            left: '+=250', top: '+=300'
        }, delay).promise().done(function() {
            $("#card-3").animate({
                left: '-=250', top: '+=300'
            }, delay).promise().done(function() {
		$("#card-4").animate({
 	            left: '-=250', top: '+=50'
                }, delay).promise().done(function() {
		    $("#card-5").animate({
 	                left: '+=275', top: '+=50'
                    }, delay).promise().done(showPlayer1Card2).promise().done(function() {
			$("#card-6").animate({
                            left: '+=275', top: '+=300'
			}, delay).promise().done(function() {
                	    $("#card-7").animate({
                                left: '-=225', top: '+=300'
                            }, delay).promise().done(function() {
			        $("#card-8").animate({
                                    left: '-=225', top: '+=50'
                                }, delay);
                            });
			});
		    });
                });
            });
        });
    });
});
