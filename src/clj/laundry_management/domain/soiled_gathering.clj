(ns laundry-management.domain.soiled-gathering
  (:require [clojure.spec.alpha :as s]
            [java-time :as time]))

(s/def ::client-id uuid?)
(s/def ::product-id uuid?)
(s/def ::quantity pos-int?)
(s/def ::soiled-weight (s/and float? pos?))
(s/def ::gathered-at time/instant?)

(s/def ::soiled-gathering (s/keys ::req [::client-id ::product-id ::quantity ::soiled-weight ::gathered-at]))

(defn valid? [soiled-gathering]
  (s/valid? ::soiled-gathering soiled-gathering))

(defn make [client-id product-id quantity soiled-weight gathered-at]
  {::client-id client-id
   ::product-id product-id
   ::quantity quantity
   ::soiled-weight soiled-weight
   ::gathered-at gathered-at})
