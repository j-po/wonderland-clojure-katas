(ns alphabet-cipher.coder
 (:require [clojure.set :refer [map-invert]]))

(def char_to_num (zipmap (seq "abcdefghijklmnopqrstuvwxyz") (range 26)))
(def num_to_char (map-invert char_to_num))

(defn sum_char [a b]
 (num_to_char (mod (apply + (map char_to_num [a b])) 26)))

(defn diff_char [a b]
  (num_to_char (mod (apply - (map char_to_num [a b])) 26)))

(defn encode [keyword message]
  (loop [codering (cycle keyword)
         msg message
         acc []]
    (if (empty? msg)
      (apply str acc)
      (recur
        (rest codering)
        (rest msg)
        (conj acc
         (apply sum_char
          (map first [msg codering])))))))
    ;(print (str keyword " " message " " (str (seq  (take 6 codering)))))))

(defn decode [keyword message]
  (loop [codering (cycle keyword)
         msg message
         acc []]
    (if (empty? msg)
      (apply str acc)
      (recur
        (rest codering)
        (rest msg)
        (conj acc
         (apply diff_char
          (map first [msg codering])))))))
