/* Foundation v2.2.1 http://foundation.zurb.com */

function generateTestCase() {
    $("#test-case").show().addClass("brush: clojure");
    SyntaxHighlighter.highlight();
}

$(document).ready(function() {
    $("#card-1").animate({
	left: '+=250', top: '+=50', duration: 300
    }).promise().done(function() {
        $("#card-2").animate({
            left: '+=250', top: '+=300', duration: 300
        }).promise().done(function() {
            $("#card-3").animate({
                left: '-=250', top: '+=300', duration: 300
            }).promise().done(function() {
		$("#card-4").animate({
 	            left: '-=250', top: '+=50', duration: 300
                });
            });
        });
    });
});
