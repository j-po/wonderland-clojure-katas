(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)
               (set)))

(def letters
  (set (seq "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))

(def exclude_alpha
  (zipmap
    alphabet
    (map #(disj letters %) alphabet)))

(defn replace_at
  "Replaces the value at the index."
;  [s n v] ; more efficient, but more annoying
  (for [i (range (count s))]
    (if (= n i)
      val
      (nth )))
;
  (map-indexed)
;    (loop [i 0
;           in s
;           out []]
;      (if (= i n)
;        ())))
  [n c word]
    (concat
     (take n word)
     [c]
     (nthrest word (inc n))))


(defn transformations [word]
  (let [word (seq word)]
  (filter
    (partial contains? words)
    (mapcat (map #() (word))))))

(defn conservative_transformations [word end_word]
  (filter
    (partial contains? words)
    (let [w (seq word)
          w2 (seq end_word)]
      (for [n (range (count w))]
        (replace_at n (nth n w2) w)))))

(conservative_transformations "door" "lock")

(for [n (range (count word))]) (map
  (partial assoc word n) ; replace the letter at n
  (exclude_alpha (nth word n))) ; with every other letter, in turn

(defn doublets [word1 word2]
  (loop [word word1
         doublet []]
    (if (= word word2)
      doublet
      (recur
        (if (= word nil)))))

(defn conservative_doublets [word1 word2])
