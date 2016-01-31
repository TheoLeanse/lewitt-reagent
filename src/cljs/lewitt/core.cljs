(ns lewitt.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn random-path []
  (rand-nth
   ["M0 0 Q 100 0 100 100"
    "M 0 100 Q 0 0 100 0"
    "M 0 100 Q 100 100 100 0"
    "M 0 0 Q 0 100 100 100"
    "M 0 0 L 100 100"
    "M 0 100 L 100 0"]))

(defn lewitt-line []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d (random-path)}]])

(defn row [row-length]
  (take row-length (repeatedly #(lewitt-line))))
  
(def artwork (atom (row 60)))

(js/setInterval #(reset! artwork (row 60)) 400)

(defn home-page []
  [:div   
   [:div @artwork]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!)
  (accountant/dispatch-current!)
  (mount-root))
