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
  (common/layout
   [:div#poker-table.row
    [:div.twelve.columns
     [:div#card-1.facedown-card]
     [:div#card-2.facedown-card]
     [:div#card-3.facedown-card]
     [:div#card-4.facedown-card]
     [:div#card-5.facedown-card]
     [:div#card-6.facedown-card]
     [:div#card-7.facedown-card]
     [:div#card-8.facedown-card]
     [:div#card-9.facedown-card]
     [:div#card-10.facedown-card]
     [:div#card-11.facedown-card]
     [:div#card-12.facedown-card]
     [:div#card-13.facedown-card]
     [:div#card-14.facedown-card]
     [:div#card-15.facedown-card]
     [:div#card-16.facedown-card]
     [:div#card-17.facedown-card]
     [:div#card-18.facedown-card]
     [:div#card-19.facedown-card]
     [:div#card-20.facedown-card]]]))