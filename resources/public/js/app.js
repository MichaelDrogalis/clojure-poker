/* Foundation v2.2.1 http://foundation.zurb.com */

var delay = 300;

function generateTestCase() {
    $("#test-case").show().addClass("brush: clojure");
    SyntaxHighlighter.highlight();
}

function showPlayer1Card1() {
    $.get("/session/player-1",
    function(data) {
	var card = data[0];
	var suit = card["suit"];
        var name = card["rank"]["name"];
        
        $("#card-1").addClass(name + "-of-" + suit);
	$("#card-1").removeClass("facedown-card");
    });
}

function showPlayer1Card2() {
    $.get("/session/player-1",
    function(data) {
	var card = data[1];
	var suit = card["suit"];
        var name = card["rank"]["name"];
        
        $("#card-5").addClass(name + "-of-" + suit);
	$("#card-5").removeClass("facedown-card");
    });
}

function showPlayer2Cards() {
    $.get("/session/player-2",
    function(data) {
	var card = data[0];
	var suit = card["suit"];
        var name = card["rank"]["name"];
        
        $("#card-2").addClass(name + "-of-" + suit);
	$("#card-2").removeClass("facedown-card");

	var card = data[1];
	var suit = card["suit"];
        var name = card["rank"]["name"];
        
        $("#card-6").addClass(name + "-of-" + suit);
	$("#card-6").removeClass("facedown-card");
    });
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
