(ns lewitt.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn line-one []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M0 0 Q 100 0 100 100"}]])

(defn line-two []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M 0 100 Q 0 0 100 0"}]])

(defn line-three []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M 0 100 Q 100 100 100 0"}]])

(defn line-four []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M 0 0 Q 0 100 100 100"}]])

(defn line-five []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M 0 0 L 100 100"}]])

(defn line-six []
  [:svg {:width 100 :height 100}
   [:path {:stroke "black" :stroke-width 2 :fill "none" :d "M 0 100 L 100 0"}]])

(defn home-page []
  [:div (line-one) (line-two)(line-three)(line-four)(line-five)(line-six)])

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
