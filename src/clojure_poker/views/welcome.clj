(ns clojure-poker.views.welcome
  (:require [clojure-poker.views.common :as common])
  (:use [noir.core :only [defpage]]))

(defpage "/" []
         (common/layout
           [:p "Welcome to clojure-poker"]))
