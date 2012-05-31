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
	player-4-cards (take 2 (drop 6 cards))]
    (do
      (session/put! :player-1-cards player-1-cards)
      (session/put! :player-2-cards player-2-cards)
      (session/put! :player-3-cards player-3-cards)
      (session/put! :player-4-cards player-4-cards)
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
	 [:div#card-8.facedown-card.card]]]
       [:div.row
	[:div.two.columns
	 (link-to {:class "big nice red radius button"} "#" "Fold")]
	[:div.two.columns
	 (link-to {:class "big nice green radius button"} "#" "Play")]
	[:div.eight.columns]]
       ))))

(defn player-cards [player]
  (response/json (session/get player "Not found")))

(defpage "/session/player-1" []
  (player-cards :player-1-cards))

(defpage "/session/player-2" []
  (escape-html (player-cards :player-2-cards)))

(defpage "/session/player-3" []
  (escape-html (player-cards :player-3-cards)))

(defpage "/session/player-4" []
  (escape-html (player-cards :player-4-cards)))

