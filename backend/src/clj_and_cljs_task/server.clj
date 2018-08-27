(ns clj-and-cljs-task.server
  (:require [org.httpkit.server :refer :all]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.cors :refer [wrap-cors]]
            [clj-and-cljs-task.scramble :refer :all]
            [clojure.data.json :as json])
  (:gen-class))

(defonce server (atom nil))

(defn handle-scramble [str1 str2]
  (try
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/write-str {:scramble? (scramble? str1 str2)})}
    (catch Throwable t
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (json/write-str {:error (.toString t)})})))

(defroutes all-routes
  (GET "/scramble" [str1 str2] (handle-scramble str1 str2)))
  
(def handler (-> all-routes
                 (wrap-cors :access-control-allow-origin [#".*"]
                            :access-control-allow-methods [:get]
                            :access-control-allow-credentials true)
                 (wrap-defaults api-defaults)))

(defn -main
  "The main function starts the http server"
  [& args]
  (reset! server (run-server #'handler {:port 8080}))
  (println "Server started at port: 8080"))                               

  
