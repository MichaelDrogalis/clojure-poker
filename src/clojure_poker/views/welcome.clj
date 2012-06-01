(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core])
  (:require [noir.response :as response])
  (:require [noir.session :as session])
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
  (let [cards (shuffle deck)
	player-1-cards (take 2 cards)
	player-2-cards (take 2 (drop 2 cards))
	player-3-cards (take 2 (drop 4 cards))
	player-4-cards (take 2 (drop 6 cards))
	flop (take 3 (drop 8 cards))
	turn (take 1 (drop 11 cards))
	river (take 1 (drop 12 cards))
	winner (winner-of {:player-1 (concat player-1-cards flop turn river)
			   :player-2 (concat player-2-cards flop turn river)
			   :player-3 (concat player-3-cards flop turn river)
			   :player-4 (concat player-4-cards flop turn river)
			  })]
    (do
      (session/put! :player-1-cards player-1-cards)
      (session/put! :player-2-cards player-2-cards)
      (session/put! :player-3-cards player-3-cards)
      (session/put! :player-4-cards player-4-cards)
      (session/put! :flop flop)
      (session/put! :turn turn)
      (session/put! :river river)
      (session/put! :winner winner)
      
      (common/layout
       [:div#poker-table.row
	[:div.twelve.columns
	 [:div#card-1.facedown-card.card]
	 [:div#card-2.facedown-card.card]
	 [:div#card-3.facedown-card.card]
	 [:div#card-4.facedown-card.card]
	 [:div#card-5.facedown-card.card]
	 [:div#card-6.facedown-card.card]
	 [:div#card-7.facedown-card.card]
	 [:div#card-8.facedown-card.card]
	 [:div#flop-1.facedown-card.card]
	 [:div#flop-2.facedown-card.card]
	 [:div#flop-3.facedown-card.card]
	 [:div#turn.facedown-card.card]
	 [:div#river.facedown-card.card]
	 [:div#pot-1]]]
       [:div.row
	[:div#winner.twelve.columns]]
       [:div.row
	[:div.two.columns
	 (link-to {:class "big nice red radius button"} "#" "Fold")]
	[:div.two.columns
	 (link-to {:class "big nice green radius button" :id "play-hand"} "#" "Play")]
	[:div.eight.columns]]))))

(defn session-response [key]
  (response/json (session/get key "Not found")))

(defpage "/session/player-1" []
  (session-response :player-1-cards))

(defpage "/session/player-2" []
  (session-response :player-2-cards))

(defpage "/session/player-3" []
  (session-response :player-3-cards))

(defpage "/session/player-4" []
  (session-response :player-4-cards))

(defpage "/session/flop" []
  (session-response :flop))

(defpage "/session/turn" []
  (session-response :turn))

(defpage "/session/river" []
  (session-response :river))

(defpage "/session/winner" []
  (session-response :winner))