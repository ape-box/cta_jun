(ns cta-jun.twhandler
  (:use
   [twitter.oauth]
   [twitter.api.restful]))

(def my-creds (make-oauth-creds (System/getenv "OAUTH_APP_KEY")
                                (System/getenv "OAUTH_APP_SECRET")
                                (System/getenv "OAUTH_CONSUMER_KEY")
                                (System/getenv "OAUTH_CONSUMER_SECRET")))

(defn normalize-hashtags [tweet-hashtags]
  "hashes comes as a vector of sets, this function is a shorthand to extract a list with only the hashtags values"
  (map (fn [e] (:text e))
       tweet-hashtags))

(defn extract-hashtags [tweets-timeline]
  "as inputs this function take the body of the twitter api https://dev.twitter.com/rest/reference/get/statuses/user_timeline, and return a merged vector of all the hashtags of all the tweets"
  (vec (apply concat (map (fn [t] (:hashtags (:entities t))) tweets-timeline))))

(defn get-timeline [user-screen-name last-n-tweets]
  "shorthand function to extract the content of the twitter api rest resource statuses/user_timeline"
  (:body (statuses-user-timeline :oauth-creds my-creds :params {:screen-name user-screen-name :count last-n-tweets})))

(defn show-user [user-screen-name]
  "make a call to the twitter api and return only the data"
  (:body (users-show :oauth-creds my-creds :params {:screen-name user-screen-name})))

(defn show-user-hashtags [user-screen-name]
  "make a call to the twitter api and return the last 500 hashes, it doesn't count them or remove the duplicates"
  (normalize-hashtags (extract-hashtags (get-timeline user-screen-name 500))))
