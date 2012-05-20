(ns clojure-poker.test.core
  (:use [clojure-poker.core])
  (:use [clojure.test]))

(deftest deck-has-52-cards
  (is
   (= (count deck) 52)))

(deftest deck-has-all-suites-of-aces
  (is (some #{(card-of :ace :clubs)} deck))
  (is (some #{(card-of :ace :diamonds)} deck))
  (is (some #{(card-of :ace :hearts)} deck))
  (is (some #{(card-of :ace :spades)} deck)))

(deftest royal-flush
  (is (= true (royal-flush? [(card-of :ten :clubs)
			     (card-of :jack :clubs)
			     (card-of :queen :clubs)
			     (card-of :king :clubs)
			     (card-of :ace :clubs)])))
  (is (= false (royal-flush? [(card-of :ten :diamonds)
			      (card-of :jack :clubs)
			      (card-of :queen :clubs)
			      (card-of :king :clubs)
			      (card-of :ace :clubs)])))
  (is (= false (royal-flush? [(card-of :ten :clubs)
			      (card-of :jack :clubs)
			      (card-of :queen :clubs)
			      (card-of :king :clubs)
			      (card-of :nine :clubs)]))))

(deftest straight
  (is (= true (straight? [(card-of :ten :clubs)
			  (card-of :jack :diamonds)
			  (card-of :queen :hearts)
			  (card-of :king :clubs)
			  (card-of :ace :clubs)])))
  (is (= false (straight? [(card-of :two :clubs)
			  (card-of :jack :diamonds)
			  (card-of :queen :hearts)
			  (card-of :king :clubs)
			  (card-of :ace :clubs)]))))