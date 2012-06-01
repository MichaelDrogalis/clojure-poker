(defproject clojure-poker "1.0.0-SNAPSHOT"
  :description "An implementation of the rules of poker."
  :dependencies [[org.clojure/clojure "1.4.0"]
		 [noir "1.3.0-beta3"]
		 [org.clojure/math.combinatorics "0.0.2"]]
  :plugins [[lein-swank "1.4.4"]]
  :main clojure-poker.server)