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

(defn four-of-a-kind? [hand]
  (n-of-a-kind? 4 hand))
  
(defn straight-flush? [hand]
  (and (straight? hand) (flush? hand)))

(defn royal-flush? [hand]
  (and (flush? hand)
       (= (into #{} (map :name (map :rank hand)))
	  (into #{} (take-last 5 (map :name ranks))))))

(defn hand-status [hand]
  (cond (royal-flush? hand) :royal-flush
	(straight-flush? hand) :straight-flush))