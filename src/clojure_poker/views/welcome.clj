(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core :only [defpage]])
  (:use [hiccup.element]))

(defpage "/" []
  (let [shuffled-deck (shuffle deck)
	player-1 (take 5 shuffled-deck)
	player-2 (take 5 (drop 5 shuffled-deck))]
    (common/layout
     [:h3 "Player 1"]
     (common/hand player-1)
     [:h3 "Player 2"]
     (common/hand player-2)
     [:h3 "Winner is " (winner-of { :player-1 player-1 :player-2 player-2} )]
     (link-to {:class "big blue nice radius button"} "#" "Generate test case"))))