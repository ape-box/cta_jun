(ns cta-jun.twhandler
  (:use
   [twitter.oauth]
   [twitter.api.restful]))


(def my-creds (make-oauth-creds "*app-consumer-key*"
                                "*app-consumer-secret*"
                                "*user-access-token*"
                                "*user-access-token-secret*"))

(defn normalize-hashtags [tweet-hashtags]
  "hashes comes as a vector of sets, this function is a shorthand to extract a list with only the hashtags values"
  (map (fn [e] (:text e))
       tweet-hashtags))

(defn extract-hashtags [tweets-timeline]
  "as imputs this function take the body of the twitter api https://dev.twitter.com/rest/reference/get/statuses/user_timeline, and return a merged vector of all the hashtags of all the tweets"
  (vec (apply concat (map (fn [t] (:hashtags (:entities t))) tweets-timeline))))

(defn get-timeline [user-screen-name last-n-tweets]
  "shorhand function to extract the content of the twitter api rest resource statuses/user_timeline"
  (:body (statuses-user-timeline :oauth-creds my-creds :params {:screen-name user-screen-name :count last-n-tweets})))

(defn show-user [user-screen-name]
  (:body (users-show :oauth-creds my-creds :params {:screen-name user-screen-name})))

(defn show-user-hashtags [user-screen-name]
  (normalize-hashtags (extract-hashtags (get-timeline user-screen-name 500))))
