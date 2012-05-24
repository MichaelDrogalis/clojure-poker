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

(deftest is-a-royal-flush
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

(deftest is-a-straight-flush
  (is (= true (straight-flush? [(card-of :two :clubs)
		   	        (card-of :three :clubs)
			        (card-of :four :clubs)
			        (card-of :five :clubs)
			        (card-of :six :clubs)])))
  (is (= false (straight-flush? [(card-of :two :hearts)
		   	         (card-of :three :clubs)
			         (card-of :four :clubs)
			         (card-of :five :clubs)
			         (card-of :six :clubs)]))))

(deftest is-a-four-of-a-kind
  (is (= true (four-of-a-kind? [(card-of :two :clubs)
		   	        (card-of :two :hearts)
			        (card-of :two :spades)
			        (card-of :two :diamonds)
			        (card-of :six :clubs)])))
  (is (= false (four-of-a-kind? [(card-of :two :clubs)
		   	         (card-of :two :hearts)
			         (card-of :two :spades)
			         (card-of :six :diamonds)
			         (card-of :six :clubs)]))))
  
(deftest is-a-straight
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

(deftest is-a-flush
  (is (= true (flush? [(card-of :four :clubs)
		       (card-of :jack :clubs)
		       (card-of :queen :clubs)
		       (card-of :king :clubs)
		       (card-of :ace :clubs)])))
  (is (= false (flush? [(card-of :four :clubs)
		        (card-of :jack :clubs)
		        (card-of :queen :clubs)
		        (card-of :king :clubs)
		        (card-of :ace :hearts)]))))

(deftest is-three-of-a-kind
  (is (= true (three-of-a-kind? [(card-of :four :clubs)
		                 (card-of :four :hearts)
		                 (card-of :four :spades)
		                 (card-of :king :clubs)
		                 (card-of :ace :clubs)])))
  (is (= false (three-of-a-kind? [(card-of :four :clubs)
	 	                  (card-of :four :hearts)
		                  (card-of :five :spades)
		                  (card-of :king :clubs)
		                  (card-of :ace :clubs)]))))

(deftest is-two-pair
  (is (= true (two-pair? [(card-of :four :clubs)
		          (card-of :four :hearts)
		          (card-of :five :spades)
		          (card-of :five :clubs)
		          (card-of :ace :clubs)])))
  (is (= false (two-pair? [(card-of :four :clubs)
		           (card-of :four :hearts)
		           (card-of :five :spades)
		           (card-of :six :clubs)
		           (card-of :ace :clubs)])))
  (is (= true (two-pair? [(card-of :four :clubs)
		          (card-of :ace :hearts)
		          (card-of :five :spades)
		          (card-of :four :diamonds)
		          (card-of :ace :clubs)]))))

(deftest is-one-pair
  (is (= true (one-pair? [(card-of :four :clubs)
		          (card-of :four :hearts)
		          (card-of :five :spades)
		          (card-of :king :clubs)
		          (card-of :ace :clubs)])))
  (is (= false (one-pair? [(card-of :four :clubs)
		           (card-of :three :hearts)
		           (card-of :five :spades)
		           (card-of :king :clubs)
		           (card-of :ace :clubs)]))))

(deftest what-hand
  (is
   (= :royal-flush
      (hand-status
       [(card-of :ten :clubs)
	(card-of :jack :clubs)
	(card-of :queen :clubs)
	(card-of :king :clubs)
	(card-of :ace :clubs)])))
  (is
   (= :straight-flush
      (hand-status
       [(card-of :ten :clubs)
	(card-of :jack :clubs)
	(card-of :queen :clubs)
	(card-of :king :clubs)
	(card-of :nine :clubs)])))
  (is
   (= :four-of-a-kind
      (hand-status
       [(card-of :ten :clubs)
	(card-of :ten :spades)
	(card-of :ten :hearts)
	(card-of :ten :diamonds)
	(card-of :king :clubs)])))
  (is
   (= :straight
      (hand-status
       [(card-of :nine :clubs)
	(card-of :ten :spades)
	(card-of :jack :hearts)
	(card-of :queen :diamonds)
	(card-of :king :clubs)])))
  (is
   (= :flush
      (hand-status
       [(card-of :nine :clubs)
	(card-of :ten :clubs)
	(card-of :jack :clubs)
	(card-of :queen :clubs)
	(card-of :two :clubs)]))))
