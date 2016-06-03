(ns cta-jun.twhandler
  (:use
   [twitter.oauth]
   [twitter.api.restful]))


#_(def my-creds (make-oauth-creds "*app-consumer-key*"
                                "*app-consumer-secret*"
                                "*user-access-token*"
                                "*user-access-token-secret*"))

(defn get-hashtags [tweets]
  (map (fn [e] (:text e))
       tweets))

(defn get-hashtags-raw [tweets-timeline]
  (vec (apply concat (map (fn [t] (:hashtags (:entities t))) tweets-timeline))))

(defn get-timeline [user-screen-name last-n-tweets]
  (:body (statuses-user-timeline :oauth-creds my-creds :params {:screen-name user-screen-name :count last-n-tweets})))
