(ns ninety-nine-lisp-problems.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;(defn Example []
;  (def x '(a b c))
;  (def y (rest x))
;  (rest y)
;  )
;(Example)

;(defn my-last []
;  ; (def i (atom 1))
;  (def x '(a b c d e))
;  (def c (count x))
;  (println "my last :" (nth x (- c 1)))
;  (println "my last :" (last x))
;  (println "my last :" (first (reverse x)))
;  ; (while (< @i c)
;  ;   (do
;  ;     (x (rest x))
;  ;     (println x)
;  ;     (swap! i inc)))
;  )
;(my-last)

;(defn my-but-last [x]
;  ; (def x '(a b c d e))
;  (if (empty? x)
;    nil
;    (println "my but last :" (butlast (reverse x)))))

;(defn element-at [k]
;  (def x '(a b c d e))
;  (nth x (- k 1)))

(defn findNumber []
  )

(defn reverse-list [x]
  (if (false? (list? x))
    nil
    (reverse x))
)

(defn isPal [n]
  (= (seq n) (reverse (seq n)))
  )

(defn my-flatten [x]
  (if (false? (list? x))
    nil
    (flatten x))
  )

(defn compress [x]
  (if (false? (list? x))
    nil
    (dedupe x))
)

(defn pack [x]
  (if (false? (list? x))
    nil
    (partition-by identity x))
  )

; (defn encode [x]
;   (def y (pack x))
;   (def i (atom 0))
;   (def c (count y))
;   (def l (list))
;   (while (< @i c)
;     (do 
;       (println (conj (nth y @i) (cons (count (nth y @i)) l)))
;       (swap! i inc))
;     )
;   )
(defn encode [x]
  (let [y (pack x)] 
    (map (fn [x]
           (seq [(count x) (first x)]))
         y))
  )

(defn encode-modified [x]

  )

(defn duplicate [x]
  (sort (flatten (repeat 2 x))))

(defn repli [x n]
  (sort (flatten (repeat n x))))

(defn dropElementN [x n]
  (get (set x) n))

(defn testing-let [x]
  (let [a x, b 5]
    (+ a b)))