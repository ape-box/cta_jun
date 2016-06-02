(ns user
  (:require [mount.core :as mount]
            cta-jun.core))

(defn start []
  (mount/start-without #'cta-jun.core/repl-server))

(defn stop []
  (mount/stop-except #'cta-jun.core/repl-server))

(defn restart []
  (stop)
  (start))


