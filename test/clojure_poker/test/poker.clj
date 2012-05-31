(ns clojure-poker.test.poker
  (:use [clojure-poker.models.poker])
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

(deftest is-a-full-house
  (is (= true (full-house? [(card-of :two :clubs)
		   	    (card-of :two :hearts)
			    (card-of :two :spades)
			    (card-of :six :diamonds)
			    (card-of :six :clubs)])))
  (is (= false (full-house? [(card-of :two :clubs)
		   	     (card-of :two :hearts)
			     (card-of :two :spades)
			     (card-of :seven :diamonds)
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
		           (card-of :ace :clubs)])))
  (is (= true (one-pair? [(card-of :five :spades)
		          (card-of :king :clubs)
		          (card-of :jack :hearts)
		          (card-of :five :clubs)
		          (card-of :two :spades)]))))

(deftest hand-comparisons
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :three :hearts)
		           (card-of :four :spades)
		           (card-of :five :clubs)
		           (card-of :six :clubs)]
		:player-2  [(card-of :seven :clubs)
		            (card-of :three :hearts)
		            (card-of :four :spades)
		            (card-of :six :spades)
		            (card-of :six :clubs)]}]
    (is (= #{:player-1} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :three :hearts)
		           (card-of :four :spades)
		           (card-of :five :clubs)
		           (card-of :six :clubs)]
		:player-2  [(card-of :two :hearts)
		            (card-of :three :diamonds)
		            (card-of :four :hearts)
		            (card-of :five :spades)
		            (card-of :six :spades)]}]
    (is (= #{:player-1 :player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :three :hearts)
		           (card-of :four :spades)
		           (card-of :five :clubs)
		           (card-of :six :clubs)]
		:player-2  [(card-of :seven :hearts)
		            (card-of :three :diamonds)
		            (card-of :four :hearts)
		            (card-of :five :spades)
		            (card-of :six :spades)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :ace :clubs)
		           (card-of :three :clubs)
		           (card-of :four :clubs)
		           (card-of :five :clubs)
		           (card-of :six :clubs)]
		:player-2  [(card-of :king :hearts)
		            (card-of :three :hearts)
		            (card-of :four :hearts)
		            (card-of :five :hearts)
		            (card-of :six :hearts)]}]
    (is (= #{:player-1} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :three :clubs)
		           (card-of :four :clubs)
		           (card-of :five :clubs)
		           (card-of :six :clubs)]
		:player-2  [(card-of :four :hearts)
		            (card-of :five :hearts)
		            (card-of :six :hearts)
		            (card-of :seven :hearts)
		            (card-of :eight :hearts)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :two :spades)
		           (card-of :two :hearts)
		           (card-of :two :diamonds)
		           (card-of :six :clubs)]
		:player-2  [(card-of :five :hearts)
		            (card-of :five :clubs)
		            (card-of :five :spades)
		            (card-of :five :diamonds)
		            (card-of :eight :hearts)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :two :spades)
		           (card-of :three :hearts)
		           (card-of :four :diamonds)
		           (card-of :six :clubs)]
		:player-2  [(card-of :ace :hearts)
		            (card-of :ace :clubs)
		            (card-of :king :spades)
		            (card-of :queen :diamonds)
		            (card-of :nine :hearts)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :two :clubs)
		           (card-of :two :spades)
		           (card-of :two :hearts)
		           (card-of :four :diamonds)
		           (card-of :six :clubs)]
		:player-2  [(card-of :ace :hearts)
		            (card-of :ace :clubs)
		            (card-of :ace :spades)
		            (card-of :queen :diamonds)
		            (card-of :nine :hearts)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :ace :clubs)
		           (card-of :ace :spades)
		           (card-of :ace :hearts)
		           (card-of :five :diamonds)
		           (card-of :five :clubs)]
		:player-2  [(card-of :king :hearts)
		            (card-of :king :clubs)
		            (card-of :king :spades)
		            (card-of :nine :diamonds)
		            (card-of :nine :hearts)]}]
    (is (= #{:player-1} (winner-of players))))
  (let
      [players {:player-1 [(card-of :three :hearts)
		           (card-of :three :spades)
		           (card-of :ace :hearts)
		           (card-of :five :diamonds)
		           (card-of :five :clubs)]
		:player-2  [(card-of :king :hearts)
		            (card-of :king :clubs)
		            (card-of :king :spades)
		            (card-of :nine :diamonds)
		            (card-of :nine :hearts)]}]
    (is (= #{:player-2} (winner-of players)))))

(deftest high-card-statuses
  (let
      [players {:player-1 [(card-of :ace :hearts)
		           (card-of :king :spades)
		           (card-of :queen :hearts)
		           (card-of :jack :diamonds)
		           (card-of :nine :clubs)]
		:player-2  [(card-of :two :hearts)
		            (card-of :two :clubs)
		            (card-of :king :hearts)
		            (card-of :jack :diamonds)
		            (card-of :nine :hearts)]}]
    (is (= #{:player-2} (winner-of players))))
  (let
      [players {:player-1 [(card-of :ace :hearts)
		           (card-of :king :spades)
		           (card-of :queen :hearts)
		           (card-of :jack :diamonds)
		           (card-of :eight :clubs)]
		:player-2 [(card-of :ace :hearts)
		           (card-of :king :spades)
		           (card-of :queen :hearts)
		           (card-of :jack :diamonds)
		           (card-of :nine :clubs)]}]
    (is (= #{:player-2} (winner-of players)))))
