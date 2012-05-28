(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [clojure-poker.models.poker])
  (:use [noir.core :only [defpage]]))

(defpage "/" []
         (common/layout
	  [:p "Welcome to clojure-poker"]
	  [:p (common/hand [(card-of :ace :diamonds)
		            (card-of :king :diamonds)
		            (card-of :queen :diamonds)
		            (card-of :jack :diamonds)
		            (card-of :ten :diamonds)])]))
