/* Foundation v2.2.1 http://foundation.zurb.com */

var delay = 300;

function generateTestCase() {
    $("#test-case").show().addClass("brush: clojure");
    SyntaxHighlighter.highlight();
}

$(document).ready(function() {
    $("#card-1").animate({
	left: '+=250', top: '+=50'
    }, delay).promise().done(function() {
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
                    }, delay).promise().done(function() {
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
