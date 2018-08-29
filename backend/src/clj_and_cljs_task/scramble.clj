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
  (let [chrs (transient (apply vector (repeat chrs-size 0)))]
    (str-processor str1 (fn [idx]
                          (assoc! chrs idx (inc (nth chrs idx)))))
    (str-processor str2 (fn [idx]
                          (let [el (dec (nth chrs idx))]
                            (assoc! chrs idx el)
                            (>= el 0))))))
