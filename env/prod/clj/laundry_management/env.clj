(ns laundry-management.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[laundry-management started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[laundry-management has shut down successfully]=-"))
   :middleware identity})
