(ns laundry-management.test.domain.soiled-gathering-test
  (:require [clojure.test :refer :all]
            [laundry-management.domain.soiled-gathering :as soiled-gathering]
            [java-time :as t])
  (:import (java.util UUID)))

(deftest soiled-gathering-client-id
  (testing "it requires client id to be an uuid"
    (let [non-uuid 1
          a-soiled-gathering (soiled-gathering/make non-uuid 2 3 4 5)]
      (is (not (soiled-gathering/valid? a-soiled-gathering))))))

(deftest soiled-gathering-product-id
  (testing "it requires product id to be an uuid"
    (let [non-uuid 2
          a-soiled-gathering (soiled-gathering/make 1 non-uuid  3 4 5)]
      (is (not (soiled-gathering/valid? a-soiled-gathering))))))

(deftest soiled-gathering-quantity-id
  (testing "it requires quantity to be a positive integer"
    (let [a-client-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          a-product-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          negative-qty -4
          a-soiled-gathering (soiled-gathering/make a-client-id a-product-id negative-qty 4.1 (t/instant))]
      (is (not (soiled-gathering/valid? a-soiled-gathering))))))

(deftest soiled-gathering-soiled-weight-id
  (testing "it requires soiled weight to be a positive float"
    (let [a-client-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          a-product-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          qty 4
          negative-soiled-weight -4.1
          a-soiled-gathering (soiled-gathering/make a-client-id a-product-id qty negative-soiled-weight (t/instant))]
      (is (not (soiled-gathering/valid? a-soiled-gathering))))))

(deftest soiled-gathering-construction
  (testing "it can be constructed with valid data"
    (let [a-client-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          a-product-id (UUID/fromString "4fe5d828-6444-11e8-8222-720007e40350")
          num-of-articles 12
          weight 123.234
          gathered-at (t/instant)
          a-soiled-gathering (soiled-gathering/make a-client-id a-product-id num-of-articles weight gathered-at)]
      (is (soiled-gathering/valid? a-soiled-gathering)))))