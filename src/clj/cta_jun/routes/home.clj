(ns cta-jun.routes.home
  (:require [cta-jun.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [cta-jun.twhandler :as tw]))

(defn home-page []
  (layout/render "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defn user-page [request]
  (let [screen-name (get (:query-params request) "screen-name") ud (tw/show-user screen-name)]
    (layout/render "user.html" {:screen-name screen-name
                                :profile_image_url (:profile_image_url ud)
                                :user-data (map (fn [k] (str (name k) ": " (k ud))) (keys ud))})))

(defn user-hastags [request]
  (let [screen-name (get (:query-params request) "screen-name") hashtags (tw/show-user-hashtags screen-name)]
    (layout/render "hashtags.html" {:screen-name screen-name
                                    :hashtags hashtags})))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/user" request (user-page request))
  (GET "/hashtags" request (user-hastags request)))

