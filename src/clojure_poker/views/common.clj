(ns clojure-poker.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "clojure-poker"]
               (include-css "/css/reset.css")
	       (include-css "/css/theme.css")
	       (include-css "/css/ie.css")
	       (include-css "/css/foundation.css")
	       (include-css "/css/shCore.css")
	       (include-css "/css/shThemeDefault.css")
 	       (include-js "/js/jquery.min.js")
	       (include-js "/js/app.js")
	       (include-js "/js/foundation.js")
	       (include-js "/js/modernizr.foundation.js")
	       (include-js "/js/shCore.js")
	       (include-js "/js/shBrushClojure.js")]
              [:body
               [:div#wrapper
                content]]))

(defpartial card [current-card]
  [:div
   {:class (str "card " (name (:name (:rank current-card))) "-of-" (name (:suit current-card)))}])

(defpartial hand [cards]
  [:section.hand
    (map card cards)])