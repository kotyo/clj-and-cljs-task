(ns frontend.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [clojure.core.async :as async]
            [cljs-http.client :as http]))

(enable-console-print!)

(defn- check-scramble [data owner]
  (let [str1 (.-value (om/get-node owner "str1"))
        str2 (.-value (om/get-node owner "str2"))]
    (om/transact! data :status #(identity "Loading..."))
    (async/go
      (let [{:keys [status body]}
            (async/<! (http/get "http://localhost:8080/scramble"
                                {:query-params {:str1 str1 :str2 str2}}))
            {:keys [error scramble?]} body
            message
            (cond
              (= status 0) "Communication Error."
              (not= status 200) "Server Error."
              (some? error) "Invalid Format. Use only lower letters [a-z]!"
              (nil? scramble?) "Malformed answer."
              scramble? "Can be scrambled."
              :else "Can't be scrambled.")]
        (om/transact! data :status #(identity message))))))

(defn- text-changed [data owner]
  (om/transact! data :status #(identity "")))

(defn- scramble-component [data owner]
  (reify om/IRender
    (render [_]
      (html [:div
             [:h1 "Let's see if we can get the second string from the first one by scrambling the characters in it"]
             [:h4 "First String:"]
             [:input {:type "text" :ref "str1"
                      :on-change #(text-changed data owner)}]
             [:h4 "Second Stirng:"]
             [:input {:type "text" :ref "str2"
                      :on-change #(text-changed data owner)}]
             [:br][:br]
             [:h5 (:status data)]
             [:button {:on-click #(check-scramble data owner)} "Scramble?"]]))))

(om/root
 scramble-component
 {:status ""}
 {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
