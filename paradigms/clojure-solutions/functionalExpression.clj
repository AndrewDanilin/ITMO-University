; HW 9: Functional expressions
(defn operation [f] (fn [& operands] (fn [vars] (apply f (map #(% vars) operands)))))

(defn constant [number] (fn [vars] number))
(defn variable [name] (fn [vars] (vars name)))

(def multiply (operation *))
(def subtract (operation -))
(def divide (operation (fn [a b] (/ (double a) (double b)))))
(def add (operation +))
(def negate (operation -))
(def sin (operation (fn [a] (Math/sin a))))
(def cos (operation (fn [a] (Math/cos a))))

(def signToOpFunctional {
              '+ add,
              '- subtract,
              '/ divide,
              'negate negate,
              '* multiply,
              'sin sin,
              'cos cos
               })

(defn fromListToFunctionalExpr [expressionList] (cond
                                        (list? expressionList) (
                                            apply (signToOpFunctional (first expressionList)) (map fromListToFunctionalExpr (rest expressionList))
                                            )
                                        (number? expressionList) (constant expressionList)
                                        (symbol? expressionList) (variable (str expressionList))
                                        ))
(defn parseFunction [expression] (fromListToFunctionalExpr (read-string expression)))








