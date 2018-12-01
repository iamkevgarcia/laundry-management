(ns user
  (:require [laundry-management.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [laundry-management.core :refer [start-app]]
            [laundry-management.db.core]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'laundry-management.core/repl-server))

(defn stop []
  (mount/stop-except #'laundry-management.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'laundry-management.db.core/*db*)
  (mount/start #'laundry-management.db.core/*db*)
  (binding [*ns* 'laundry-management.db.core]
    (conman/bind-connection laundry-management.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


