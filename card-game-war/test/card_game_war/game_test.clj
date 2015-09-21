(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all])
  (:import [card_game_war.game Card]))


;; fill in  tests for your game
(deftest test-play-round
  (let [AC (Card. :clubs :ace)
        KC (Card. :clubs :king)
        QC (Card. :clubs :queen)
        JC (Card. :clubs :jack)
        AS (Card. :spades :ace)
        AD (Card. :diamonds :ace)
        AH (Card. :hearts :ace)]
  (testing "the highest rank wins the cards in the round"
    (is (= (Card. :clubs 9) (play-round (Card. :clubs 9) (Card. :clubs 7)))))
  (testing "queens are higher rank than jacks"
    (is (pos? (compare QC JC))))
  (testing "kings are higher rank than queens"
    (is (pos? (compare KC QC))))
  (testing "aces are higher rank than kings"
    (is (pos? (compare AC KC))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (pos? (compare AC AS))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (pos? (compare AD AC))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (pos? (compare AH AD))))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= cards (play-game cards [])))))

