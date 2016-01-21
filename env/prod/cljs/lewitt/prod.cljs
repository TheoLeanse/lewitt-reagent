(ns lewitt.prod
  (:require [lewitt.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
