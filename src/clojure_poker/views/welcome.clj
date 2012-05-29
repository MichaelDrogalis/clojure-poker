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
      [:div.six.columns
       (player-row "Player 1" player-1)
       (player-row "Player 2" player-2)]
      [:div.six.columns
       [:div.row
	[:div.twelve.columns
	 [:div.panel
	[:h3 "Winner is " (winner-of { :player-1 player-1 :player-2 player-2} )]
	(link-to {:class "big blue nice radius button"} "javascript:generateTestCase();" "Generate test case")]]]
       [:div.row
	[:div.twelve.columns
	 [:pre#test-case]]]]])))

(defpage "/to-test-case" []
  (escape-html
   "(deftest bug-report-test-case
      (let
	  [players {:player-1 [(card-of :ace :hearts)
			       (card-of :king :spades)
			       (card-of :queen :hearts)
			       (card-of :jack :diamonds)
			       (card-of :nine :clubs)]
		    :player-2  [(card-of :two :hearts)
				(card-of :two :clubs)
				(card-of :king :hearts)
				(card-of :jack :diamonds)
				(card-of :nine :hearts)]}]
	(is (= #{:player-2} (winner-of players)))))"))
