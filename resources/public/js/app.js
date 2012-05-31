/* Foundation v2.2.1 http://foundation.zurb.com */

var delay = 250;

function hideCommunityCards() {
    $("#flop-1").hide();
    $("#flop-2").hide();
    $("#flop-3").hide();
    $("#turn").hide();
    $("#river").hide();
}

function dealFlopCards() {
    $("#flop-1").show();
    $("#flop-2").show();
    $("#flop-3").show();

    $("#flop-1").animate({
	left: '-=200', top: '+=125'
    }, delay).promise().done(function() {
	$("#flop-2").animate({
	    left: '-=100', top: '+=125'
	}, delay).promise().done(function() {
  	    $("#flop-3").animate({
	        top: '+=125'
            }, delay).promise().done(function() {
		showCard("/session/flop", 0, "#flop-1");
		showCard("/session/flop", 1, "#flop-2");
		showCard("/session/flop", 2, "#flop-3");
            }).promise().done(function() {
		dealTurnAndRiver();
            });
	});
    });
}

function dealTurnAndRiver() {
    $("#turn").show();
    $("#river").show();

    $("#turn").animate({
	left: '+=100', top: '+=125'
    }, delay).promise().done(function() {
        showCard("/session/turn", 0, "#turn")
    }).promise().done(function() {
	    $("#river").animate({
		left: '+=200', top: '+=125'
            }, delay).promise().done(function() {
		showCard("session/river", 0, "#river");
	    });
	}, delay);
}

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

function showPlayer3Cards() {
    showCard("/session/player-3", 0, "#card-3");
    showCard("/session/player-3", 1, "#card-7");
}

function showPlayer4Cards() {
    showCard("/session/player-4", 0, "#card-4");
    showCard("/session/player-4", 1, "#card-8");
}

function showEveryonesHand() {
    showPlayer2Cards();
    showPlayer3Cards();
    showPlayer4Cards();
    
    dealFlopCards();
}

$(document).ready(function() {
    hideCommunityCards();
    
    $("#play-hand").click(function() {
	showEveryonesHand();
    });
    
    $("#card-1").animate({
	left: '+=350', top: '+=50'
    }, delay).promise().done(showPlayer1Card1).promise().done(function() {
        $("#card-2").animate({
            left: '+=350', top: '+=300'
        }, delay).promise().done(function() {
            $("#card-3").animate({
                left: '-=350', top: '+=300'
            }, delay).promise().done(function() {
		$("#card-4").animate({
 	            left: '-=350', top: '+=50'
                }, delay).promise().done(function() {
		    $("#card-5").animate({
 	                left: '+=375', top: '+=50'
                    }, delay).promise().done(showPlayer1Card2).promise().done(function() {
			$("#card-6").animate({
                            left: '+=375', top: '+=300'
			}, delay).promise().done(function() {
                	    $("#card-7").animate({
                                left: '-=325', top: '+=300'
                            }, delay).promise().done(function() {
			        $("#card-8").animate({
                                    left: '-=325', top: '+=50'
                                }, delay);
                            });
			});
		    });
                });
            });
        });
    });
});
