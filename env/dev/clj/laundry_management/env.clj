(ns laundry-management.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [laundry-management.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[laundry-management started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[laundry-management has shut down successfully]=-"))
   :middleware wrap-dev})
