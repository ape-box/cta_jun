(ns cta-jun.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[cta_jun started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[cta_jun has shutdown successfully]=-"))
   :middleware identity})
