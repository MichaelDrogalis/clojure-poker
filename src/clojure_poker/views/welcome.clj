(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core :only [defpage]]))

(defpage "/" []
         (common/layout
	  [:p "Welcome to clojure-poker"]
	  [:p (common/hand [(card-of :ace :clubs)
		            (card-of :king :clubs)
		            (card-of :queen :clubs)
		            (card-of :jack :clubs)
		            (card-of :ten :clubs)])]))
