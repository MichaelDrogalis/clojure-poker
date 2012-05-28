(ns clojure-poker.models.poker
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

(defn hand-values [hand]
  (map :value (map :rank hand)))

(defn n-of-a-kind? [n hand]
  (let [values (hand-values hand)]
    (boolean (some #(= (count %) n)
		   (partition-by identity (sort values))))))

(defn high-card-value [hand-vals]
  (apply max hand-vals))

(defn low-card-value [hand-vals]
  (apply min hand-vals))

(defn one-pair? [hand]
  (n-of-a-kind? 2 hand))

(defn two-pair? [hand]
  (= (count (filter identity (map one-pair? (combinations hand 2)))) 2))
  
(defn three-of-a-kind? [hand]
  (n-of-a-kind? 3 hand))

(defn straight? [hand]
  (let [hand-vals (sort (hand-values hand))
	min-val   (low-card-value hand-vals)
	max-val   (high-card-value hand-vals)
	val-range (range min-val (inc max-val))
        straight-length 5]
    (and (= val-range hand-vals)
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

(defn highest-value-score [hand base-score]
  (+ base-score (high-card-value (hand-values hand))))

(defn highest-paired-score [hand base-score]
  (let [values (hand-values hand)]
    (+ base-score
       (first (apply max-key count (partition-by identity (sort values)))))))

(defn high-card-score [hand]
  (let [rank-names (map #(:name (:rank %)) hand)]
    (letfn [(score [rank-name]
		   (cond (= rank-name :ace)   3968
			 (= rank-name :king)  1984
			 (= rank-name :queen) 992
			 (= rank-name :jack)  496
			 (= rank-name :ten)   248
			 (= rank-name :nine)  124
			 (= rank-name :eight) 62
			 (= rank-name :seven) 31
			 (= rank-name :six)   16
			 (= rank-name :five)  8
			 (= rank-name :four)  4
			 (= rank-name :three) 2
			 (= rank-name :two)   1))]
      (apply + (map score rank-names)))))

(defn one-pair-score [hand]
  (highest-paired-score hand 8000))

(defn two-pair-score [hand]
  (let [base-score 9000
	values (hand-values hand)]
    (+ base-score
       (apply max (flatten (filter #(= (count %) 2)
				   (partition-by identity (sort values))))))))

(defn three-of-a-kind-score [hand]
  (highest-paired-score hand 10000))
  
(defn straight-score [hand]
  (highest-value-score hand 11000))

(defn flush-score [hand]
  (highest-value-score hand 12000))

(defn full-house-score [hand]
  (highest-value-score hand 13000))

(defn four-of-a-kind-score [hand]
  (highest-paired-score hand 14000))

(defn straight-flush-score [hand]
  (highest-value-score hand 15000))

(defn royal-flush-score [_]
  16000)
  
(defn compute-score [hand]
  (cond (royal-flush? hand) (royal-flush-score hand)
	(straight-flush? hand) (straight-flush-score hand)
	(four-of-a-kind? hand) (four-of-a-kind-score hand)
	(full-house? hand) (full-house-score hand)
	(flush? hand) (flush-score hand)
	(straight? hand) (straight-score hand)
	(three-of-a-kind? hand) (three-of-a-kind-score hand)
	(two-pair? hand) (two-pair-score hand)
	(one-pair? hand) (one-pair-score hand)
	:else (high-card-score hand)))

(defn winner-of [players]
  (let [scores
	(into #{}
	      (mapcat (fn [[player hand]] { player (compute-score hand) })
		      players))
	high-score (second (apply max-key second scores))]
    (into #{} (map first (filter #(= high-score (second %)) scores)))))
