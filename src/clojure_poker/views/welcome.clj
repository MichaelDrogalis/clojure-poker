(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core])
  (:use [hiccup.element]))

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
      [:div.seven.columns
       (player-row "Player 1" player-1)
       (player-row "Player 2" player-2)]
      [:div.five.columns
       [:div.row
	[:div.twelve.columns
	 [:div.panel
	[:h3 "Winner is " (winner-of { :player-1 player-1 :player-2 player-2} )]
	(link-to {:class "big blue nice radius button"} "#" "Generate test case")]]]
       [:div.row
	[:div.twelve.columns
	 [:div#test-case.panel]]]]])))