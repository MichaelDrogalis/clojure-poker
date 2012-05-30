(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core])
  (:use [hiccup.element])
  (:use [hiccup.page])
  (:use [hiccup.util]))

(defpartial player-row [player-name cards]
  [:div.row
   [:div.twelve.columns
    [:div.panel
     [:div.row
      [:div.twelve.columns
       [:h3 player-name]]]
     [:div.row
      [:div.twelve.columns
       (common/hand cards)]]]]])

(defpage "/" []
  (let [shuffled-deck (shuffle deck)
	player-1 (take 5 shuffled-deck)
	player-2 (take 5 (drop 5 shuffled-deck))]
    (common/layout
     [:div.row
      [:div.twelve.columns
       (player-row "Player 1" player-1)
       (player-row "Player 2" player-2)]]
     [:div.row
      [:div.twelve.columns
       [:div.panel
	[:h4 "Winner is " (winner-of { :player-1 player-1 :player-2 player-2})]]
       (link-to {:class "big blue nice radius button"} "javascript:generateTestCase();" "Generate test case")]]
     [:div.row
	[:div.twelve.columns
	 [:pre#test-case
	  (escape-html
	   (str
	    "(deftest bug-report-test-case\n"
             "  (let\n"
               "    [players {:player-1 " (vector player-1) "\n"
                          "              :player-2 " (vector player-2) "}]\n"
        "    (is (= " (winner-of { :player-1 player-1 :player-2 player-2}) " (winner-of players)))))" ))]]])))

