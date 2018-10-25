(ns bakery.core-test
  (:require [clojure.test :refer :all]
            [bakery.core :refer :all]))

(deftest test-1
  (testing "brownie"
    (is (= 2.00 (compute-price [["Brownie" 1]] (get-products))))))

(deftest test-2
  (testing "cookie, brownies and cheesecake"
    (is (= 16.25 (compute-price [["Cookie" 1] ["Brownie" 4] ["Key Lime Cheesecake" 1]] (get-products))))))

(deftest test-3
  (testing "cookies"
    (is (= 8.00 (compute-price [["Cookie" 8]] (get-products))))))

(deftest test-3
  (testing "cookie, brownie, cheesecake and donuts"
    (is (= 12.25 (compute-price [["Cookie" 1] ["Brownie" 1] ["Key Lime Cheesecake" 1] ["Mini Gingerbread Donut" 2]] (get-products))))))

(deftest test-4
  (testing "empty shopping list"
    (is (= 0 (compute-price [] (get-products))))))

(def test-5
  (testing "repeated item"
    (is (= 4.00 (compute-price [["Brownie" 1] ["Brownie" 1]] (get-products))))))
