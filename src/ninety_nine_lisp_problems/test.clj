(ns ninety-nine-lisp-problems.test)

(defn testing-let [x]
  (let [a x, b 10]
    [a b]))

(let [[a b c d e] [1 2 3 4 5]]
  [a b]) ;; => [1 2]
(use '[clojure.set :only [superset?]])
(superset? #{0} #{0})

(first (reverse [1 2 3 4 5])) ;; => 5
(#(first (reverse %)) [1 2 3 4 5]) ;; => 5
((fn [x]
   (first (reverse x)))
 [1 2 3 4 5]
 ) ;; => 5
((comp first reverse) [1 2 3 4 5]) ;; => 5

(apply str (re-seq #"[A-Z]+" "bA1B3Ce ")) ;; => "ABC"

(#(first (rest (reverse %))) [1 2 3 4 5]) ;; => 4
((fn [x]
   (second (reverse x)))
 [1 2 3 4 5]) ;; => 4

(map (constantly 9) [1 2 3]) ;; => (9 9 9)
(reduce + (map (constantly 1) [1 2 3])) ;; => 3
(reduce + (map (constantly 1) [1 2 3 10 11 12 13 14 15])) ;; => 9
(#(reduce + (map (constantly 1) %)) [1 2 3 10 11 12 13 14 15]) ;; => 9

(reduce + [1 2 3]) ;; => 6

(filter odd? #{1 2 3 4 5}) ;; => (1 3 5)
(#(filter odd? %) #{1 2 3 4 5}) ;; => (1 3 5)
(filter odd? [1 2 3 4 5]) ;; => (1 3 5)
(filter even? [1 2 3 4 5]) ;; => (2 4)

(reduce conj () [1 2 3 4 5]) ;; => (5 4 3 2 1)
(reduce #(cons %2 %1) () [1 2 3 4 5]) ;; => (5 4 3 2 1)
(reduce #(conj %1 %2) () [1 2 3 4 5]) ;; => (5 4 3 2 1)
(#(reduce conj () %) [1 2 3 4 5]) ;; => (5 4 3 2 1)
((fn [s]
   (loop [result []
          s s]
     (if (seq s)
       (recur (concat [(first s)] result) (rest s))
       result)))
 [1 2 3 4 5]) ;; => (5 4 3 2 1)

(loop [x 5
       result []]
  (if (> x 0)
    (recur (dec x) (conj result (+ 2 x)))
    result)) ;; => [7 6 5 4 3]
(loop [x 10]
  (when (> x 1)
    (recur (- x 2))))

;;palindrome
(#(= (seq %) (reverse (seq %))) '(1 2 3 4 5)) ;; => false
(#(= (seq %) (reverse (seq %))) "racecar") ;; => true
(#(if(< (count %1)3)
    true
    (if(=(first %1)(last %1))
      (recur (-> %1 rest butlast))
      false)
    )
  "racecar"
  )
(#(= (seq %) (reverse %)) "racecar")

(#(take % (map last(iterate (fn [[x y]]
                              [y (+ x y)])
                            [0 1])))
  8) ;; => (1 1 2 3 5 8 13 21)
(#(take % (iterate (fn [[x y]]
                              [y (+ x y)])
                            [0 1]))
  8) ;; => ([0 1] [1 1] [1 2] [2 3] [3 5] [5 8] [8 13] [13 21])

(#(last(sort %&)) 1 8 3 4) ;; => 8
((fn [& x]
   (-> x sort last))
 1 8 3 4) ;; => 8

(#(apply str (re-seq #"[A-Z]+" %)) "HeLlO, WoRlD!") ;; => "HLOWRD"
((fn [xs]
   (reduce str (re-seq #"[A-Z]" xs)))
 "HeLlO, WoRlD!") ;; => "HLOWRD"

((fn [x]
   (sort(apply concat(#(repeat 2 %) x))))
 [1 2 3]) ;; => (1 1 2 2 3 3)
((fn [x]
   (sort(apply concat(#(repeat 2 %) x))))
 [[1 2] [3 4]]) ;; => ([1 2] [1 2] [3 4] [3 4])
(#(interleave % %) [[1 2] [3 4]]) ;; => ([1 2] [1 2] [3 4] [3 4])
(#(sort (into % %)) [[1 2] [3 4]]) ;; => ([1 2] [1 2] [3 4] [3 4])

(some #{2 7 6} [5 6 7 8]) ;; => 6
(some #(when (even? %) %) [5 6 7 8]) ;; => 6

(apply str (#(map first (partition-by identity %)) "Leeeeeerrroyyy")) ;; => "Leroy"
(#(map first (partition-by identity %)) [1 1 2 3 3 2 2 3]) ;; => (1 2 3 2 3)
(#(reduce (fn [a b]
            (if (= (last a) b)
              a
              (conj a b)))
          [] %)
  [1 1 2 3 3 2 2 3]) ;; => (1 2 3 2 3)

(range 1 4) ;; => (1 2 3)
(#(take (- %2 %1) (iterate inc %1)) 1 4) ;; => (1 2 3)
((fn [x y]
   (take (- y x) (iterate inc x)))
 1 4) ;; => (1 2 3)
((fn [from to]
   (take-while #(< % to)
               (iterate inc from)))
 1 4) ;; => (1 2 3)

;; factorial
(#(apply * (range 1 (inc %))) 5) ;; => 120
(#(reduce * (range 1 (inc %))) 5) ;; => 120
((fn [n]
  (loop [cnt n acc 1]
    (if (zero? cnt)
      acc
      (recur (dec cnt) (* acc cnt)))))
 5) ;; => 120

(loop [sum 0
       cnt 10]
  ; If count reaches 0 then exit the loop and return sum
  (if (= cnt 0)
    sum
    ; Otherwise add count to sum, decrease count and
    ; use recur to feed the new values back into the loop
    (recur (+ cnt sum) (dec cnt)))) ;; => 55

(take 10 (iterate #(+ 3 %) 1)) ;; => (1 4 7 10 13 16 19 22 25 28)

(partition-by identity [1 1 1 2 3 3 3 3 3 4 4 5 6 7 7 7]) ;; => ((1 1 1) (2) (3 3 3 3 3) (4 4) (5) (6) (7 7 7))
(map (fn [i]
       seq [(count i) (first i)])
     (partition-by identity '(a a a b c c c c c d d e f g g g))) ;; => ([3 a] [1 b] [5 c] [2 d] [1 e] [1 f] [3 g])

(#(apply hash-map (interleave %1 %2)) [:a :b :c] [1 2 3]) ;; => {:c 3, :b 2, :a 1}
((fn [x y]
   (apply hash-map (interleave x y)))
  [:a :b :c] [1 2 3]) ;; => {:c 3, :b 2, :a 1}

((fn [a b]
   (if (zero? b)
     a
     (recur b (mod a b))))
 1023 858) ;; => 33
((fn [a b]
   (if (= 0 b)
     a
     (recur b (mod a b))))
 1023 858) ;; => 33

((fn [x y]
   (set (filter (fn [z]
             (contains? y z))
           x)))
 #{0 1 2 3} #{2 3 4 5}) ;; => #{3 2}
((fn [a b]
   (set (filter #(contains? b %)
                a)))
 #{0 1 2 3} #{2 3 4 5}) ;; => #{3 2}
((comp set filter) #{0 1 2 3} #{2 3 4 5}) ;; => #{3 2}

(def my-car
  {:name "audi"
   :data {:cc 2990
          :bhp 350}})

((comp :bhp :data) my-car)
;;350

;;which is the equivalent of
(:bhp (:data my-car))
;;350


((fn [f a b]
   (cond(f a b) :lt (f b a) :gt
                  :else :eq))
 < 5 1) ;; => :gt
((fn [f a b]
   (cond(f a b) :lt (f b a) :gt
        :else :eq))
 (fn [x y] (< (count x) (count y)))
 "pear" "plum") ;; => :eq
(#(if (%1 %2 %3)
   :lt
   (if (%1 %3 %2)
     :gt
     :eq))
  < 5 1) ;; => :gt

(#(set (for [a %1 b %2] [a b])) #{1 2 3} #{4 5})
;; => #{[2 5] [3 4] [1 4] [1 5] [2 4] [3 5]}
(#(into #{} (for [x %1 y %2] [x y])) #{1 2 3} #{4 5})
;; => #{[2 5] [3 4] [1 4] [1 5] [2 4] [3 5]}

((fn [a b]
   (mapv #(Integer/parseInt (str %)) (str (* a b))))
 99 9)
;; => [8 9 1]
((fn [a b]
   (map #(Integer/parseInt (str %)) (str (* a b))))
 99 9)
;; => (8 9 1)
((fn [x y]
   (map #(Integer. (.toString %)) (str (* x y))))
 99 9)
;; => (8 9 1)
(#(reduce (fn [x y]
            (into x (vector (- (int y) 48)))) [] (str (* %1 %2)))
  99 9)
;; => [8 9 1]
((comp (partial map #(- (int %) 48)) str *) 99 9)
;; => (8 9 1)

(#(clojure.set/union
    (clojure.set/difference %1 %2)
    (clojure.set/difference %2 %1))
  #{1 2 3 4 5 6} #{1 3 5 7})
;; => #{7 4 6 2}
(#(clojure.set/union
    (clojure.set/difference %1 %2)
    (clojure.set/difference %2 %1))
  #{[1 2] [2 3]} #{[2 3] [3 4]})
;; => #{[3 4] [1 2]}
(#(set
    (concat
      (apply disj %1 %2) (apply disj %2 %1)))
  #{[1 2] [2 3]} #{[2 3] [3 4]})
;; => #{[3 4] [1 2]}

;; dot function
(#(reduce + (map * %1 %2)) [0 1 0] [1 0 0])
;; => 0
(#(reduce + (map * %1 %2)) [1 2 3] [4 5 6])
;; => 32
((fn [x y]
   (reduce + (map * x y)))
 [1 2 3] [4 5 6])
;; => 32
((fn [x y]
   (apply + (map * x y)))
 [1 2 3] [4 5 6])
;; => 32

(#(Integer/parseInt % 2) "111")
;; => 7
((fn [n]
   (Integer/parseInt n 2))
 "111")
;; => 7
(#(read-string (str "2r" %)) "111")
;; => 7

(require '[clojure.string :as str])
(#(let [x (str/split % #"")] (map (fn [y] (Integer/parseInt y)) x))
"111")

(loop [x 5
       result []]
  (if (> x 0)
    (recur (dec x) (conj result (+ 2 x)))
    result))

((fn [x y]
   (map #(vec [%2 %1]) (vec (repeat 3 x)) y))
 0 [:a :b :c])

(#(zipmap %2 (repeat %1)) 0 [:a :b :c])
;; => {:a 0, :b 0, :c 0}
(#(apply hash-map (interleave %2 (repeat %1))) 0 [:a :b :c])

((fn [x y]
   )
 '(4 5 6 7) 2)

((fn [x y]
   (last (take (inc y) x)))
 '(4 5 6 7) 2)
;; => 6
((fn [x y]
   (first (drop y x)))
 '(4 5 6 7) 2)
;; => 6
((fn [x y]
   ((vec x) y))
 '(4 5 6 7) 2)
;; => 6

((fn [x y]
   (loop [i y
          result []]
     (if (< i (count x))
       (recur (* i 2) (conj result (nth x (- i 1))))
       result)))
 [1 2 3 4 5 6 7 8] 3)
((fn [x y]
   (loop [i y
          result []]
     (if (<= i (count x))
       (recur (+ i y) (conj result (nth x (- i 1))))
       result)))
 [1 2 3 4 5 6 7 8] 3)
;; => [3 6]
((fn [x y]
   (loop [i y
          z []]
     (if (<= i (count x))
       (recur (+ i y) (conj z (nth x (- i 1))))
       (remove (set z) x))))
 [1 2 3 4 5 6 7 8] 3)
;; => (1 2 4 5 7 8)
(#(apply concat (partition-all (dec %2) %2 %1))
  [1 2 3 4 5 6 7 8] 3)
;; => (1 2 4 5 7 8)

;; Split-at
(#(apply conj (list (vec (nthrest %2 %1))) (list (vec (take %1 %2))))
  3 [1 2 3 4 5 6])
;; => ([1 2 3] [4 5 6])
(#(apply conj [(take %1 %2)] [(nthrest %2 %1)])
  3 [1 2 3 4 5 6])
;; => [(1 2 3) (4 5 6)]
((fn [n xs]
   (list (take n xs) (drop n xs)))
 3 [1 2 3 4 5 6])
;; => ((1 2 3) (4 5 6))
((juxt take drop)
 3 [1 2 3 4 5 6])
;; => [(1 2 3) (4 5 6)]
((fn [n s]
   [(take n s) (drop n s)])
 3 [1 2 3 4 5 6])