(ns clojure-poker.core
  (:use [clojure.math.combinatorics]))

(defrecord Rank [name value])
(defrecord Card [rank suit])

(def ranks (map #(Rank. %1 %2) [:two :three :four :five :six :seven :eight :nine :ten :jack :queen :king :ace] (range)))
(def suits #{:clubs :diamonds :hearts :spades})

(def deck
     (mapcat (fn [rank]
	       (map (fn [suit] (Card. rank suit)) suits))
	     ranks))

(defn card-of [name suit]
  (first (filter #(and (= (:name (:rank %)) name)
		       (= (:suit %) suit))
		 deck)))

(defn n-of-a-kind? [n hand]
  (let [values (map :value (map :rank hand))]
    (boolean (some #(= (count %) n)
		   (partition-by identity values)))))

(defn one-pair? [hand]
  (n-of-a-kind? 2 hand))

(defn two-pair? [hand]
  (= (count (filter identity (map one-pair? (combinations hand 2)))) 2))
  
(defn three-of-a-kind? [hand]
  (n-of-a-kind? 3 hand))

(defn straight? [hand]
  (let [card-vals (into #{} (map :value (map :rank hand)))
	min-val   (apply min card-vals)
	max-val   (apply max card-vals)
	val-range (range min-val (inc max-val))
        straight-length 5]
    (and (= (into #{} val-range) card-vals)
         (= (count val-range) straight-length))))

(defn flush? [hand]
  (apply = (map :suit hand)))

(defn full-house? [hand]
  (and (three-of-a-kind? hand) (one-pair? hand)))

(defn four-of-a-kind? [hand]
  (n-of-a-kind? 4 hand))
  
(defn straight-flush? [hand]
  (and (straight? hand) (flush? hand)))

(defn royal-flush? [hand]
  (and (flush? hand)
       (= (into #{} (map :name (map :rank hand)))
	  (into #{} (take-last 5 (map :name ranks))))))

(defn compute-score [hand]
  (cond (royal-flush? hand) 1000
	(straight-flush? hand) 900
	(four-of-a-kind? hand) 800
	(full-house? hand) 700
	(flush? hand) 600
	(straight? hand) 500
	(three-of-a-kind? hand) 400
	(two-pair? hand) 300
	(one-pair? hand) 200))

(defn winner-of [players]
  (let [scores
	(into #{}
	      (mapcat
	       (fn [[player players-hand]]
		 { player (compute-score players-hand) })
	       players))]
    #{(first (apply max-key count scores))}))