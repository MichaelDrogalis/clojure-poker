(ns clojure-poker.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "clojure-poker"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))

(defpartial card [current-card]
  [:span
   {:class (str (name (:name (:rank current-card))) "-of-" (name (:suit current-card)))}])
(defpartial hand [cards]
  [:section.hand
   (map card cards)])