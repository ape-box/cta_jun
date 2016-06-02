(ns cta-jun.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [cta-jun.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[cta_jun started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[cta_jun has shutdown successfully]=-"))
   :middleware wrap-dev})
