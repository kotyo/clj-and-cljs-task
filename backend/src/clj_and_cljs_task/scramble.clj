(ns clj-and-cljs-task.scramble)

(def ^:private chr-offset (int \a))
(def ^:private chrs-size 26)

(defn- str-processor
  [st f]
  (loop [st st]
    (cond
      (empty? st) true
      (not (f (-> (first st) int (- chr-offset)))) false
      :else (recur (rest st)))))
  
(defn scramble?
  "Check if we can get the second string from the first one by
   scrambling the characters in it"
  [str1 str2]
  {:pre [(string? str1) (string? str2)]}
  (let [chrs (make-array Integer/TYPE chrs-size)]
    (str-processor str1 #(aset chrs %1 (inc (aget chrs %1))))
    (str-processor str2 #(>= (aset chrs %1 (dec (aget chrs %1)))
                             0))))
