(ns ninety-nine-lisp-problems.basic)

(def person
  {:first-name "Kelly"
   :last-name "Keen"
   :age 32
   :occupation "Programmer"})
;; Field accessors
(get person :occupation)
(person :occupation)
(:occupation person)
(:favorite-color person "beige")
;; Updating fields
(assoc person :occupation "Baker")
;; Removing a field
(dissoc person :age)


;; Nested entities
(def company
  {:name "WidgetCo"
   :address {:street "123 Main St"
             :city "Springfield"
             :state "IL"}})
(get-in company [:address :city])
(assoc-in company [:address :street] "303 Broadway")
(def employee
  [
   {:name "Alice"
    :address {
              :street "jl tebet barat"
              :city "jaksel"
              :state "DKI Jakarta"}}
  {:name "Bob"
    :address {
              :street "jl tebet timur"
              :city "jaksel"
              :state "DKI Jakarta"
              }}
   ]
   )
(get-in employee [0 :name])
(get-in employee [0 :address :city])

;; Define a record structure
(defrecord Person [first-name last-name age occupation])
;; Positional constructor - generated
(def kelly (->Person "Kelly" "Keen" 32 "Programmer"))
;; Map constructor - generated
(def kelly (map->Person
             {:first-name "Kelly"
              :last-name "Keen"
              :age 32
              :occupation "Programmer"}))


;;Sets
(def players #{"Alice", "Bob", "Kelly"})
(conj players "Fred")
(disj players "Bob" "Sal")
(contains? players "Kelly")
(conj (sorted-set) "Bravo" "Charlie" "Sigma" "Alpha")
(def new-players #{"Tim", "Sue"})
(into players new-players)
