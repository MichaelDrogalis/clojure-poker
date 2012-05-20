(ns clojure-poker.core)

(defrecord Rank [name value])
(defrecord Card [rank suit])

(def ranks (map #(Rank. %1 %2) [:two :three :four :five :six :seven :eight :nine :ten :jack :queen :king :ace] (range)))
(def suits #{:clubs :diamonds :hearts :spades})

(def straight-length 5)

(def deck
     (mapcat (fn [rank]
	       (map (fn [suit] (Card. rank suit)) suits))
	     ranks))

(defn card-of [name suit]
  (first (filter #(and (= (:name (:rank %)) name)
		       (= (:suit %) suit))
		 deck)))

(defn straight? [hand]
  (let [card-vals (into #{} (map :value (map :rank hand)))
	min-val   (apply min card-vals)
	max-val   (apply max card-vals)]
    (and (= (into #{} (range min-val (inc max-val))) card-vals)
         (= (count (range min-val (inc max-val))) straight-length))))

(defn flush? [hand]
  (apply = (map :suit hand)))

(defn four-of-a-kind? [hand]
  (let [values (map :value (map :rank hand))]
    (boolean (some #(= (count %) 4)
		   (partition-by identity values)))))

(defn straight-flush? [hand]
  (and (straight? hand) (flush? hand)))

(defn royal-flush? [hand]
  (and (flush? hand)
       (= (into #{} (map :name (map :rank hand)))
	  (into #{} (take-last 5 (map :name ranks))))))