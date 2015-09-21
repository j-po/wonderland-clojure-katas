(ns card-game-war.game
  (:require [medley.core :refer [greatest]]))

;; feel free to use these cards or use your own data structure
(def suits {:spades 0 :clubs 1 :diamonds 2 :hearts 3})
(def ranks (zipmap [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace] (range 2 15)))
(defrecord Card [suit rank]
  java.lang.Comparable
  (compareTo [this a]
    (if (= rank (:rank a))
      (apply compare (map suits [suit (:suit a)]))
      (apply compare (map ranks [rank (:rank a)])))))
(def cards
  (for [suit (keys suits)
        rank (keys ranks)]
    (Card. suit rank)))

(defn play-round [player1-card player2-card]
  (greatest player1-card player2-card))

(defn play-game [player1-cards player2-cards]
  (loop [p1 player1-cards
         p2 player2-cards]
    (cond
      (empty? p1) p2
      (empty? p2) p1
      :else (let [c1 (first p1)
                  c2 (first p2)
                  round (play-round c1 c2)]
              (if (= round c1) ; if player 1 wins the round
                (recur (concat (rest p1) '(c1 c2)) (rest p2))
                ; else, if player 2 wins
                (recur (rest p1) (concat (rest p2) '(c2 c1))))))))

(play-round (Card. :clubs :queen) (Card. :clubs 7))
