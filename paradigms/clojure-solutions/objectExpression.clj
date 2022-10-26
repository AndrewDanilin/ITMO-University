; HW 10: Object expressions
(defn proto-get
  ([obj key] (proto-get obj key nil))
  ([obj key default]
   (cond
     (contains? obj key) (obj key)
     (contains? obj :prototype) (proto-get (obj :prototype) key default)
     :else default)))

(defn proto-call
  [this key & args]
  (apply (proto-get this key) this args))

(defn field [key]
  (fn
    ([this] (proto-get this key))
    ([this def] (proto-get this key def))))

(defn method
  [key] (fn [this & args] (apply proto-call this key args)))

(defn constructor
  [ctor prototype]
  (fn [& args] (apply ctor {:prototype prototype} args)))

(def _a (field :a))
(def _operands (field :operands))
(def _operation (field :operation))
(def _sign (field :sign))
(def _diffFunc (field :diffFunc))

(def evaluate (method :evaluate))
(def toString (method :toString))
(def diff (method :diff))
(def toStringSuffix (method :toStringSuffix))

(def Constant)
(def ConstantPrototype
  {:evaluate (fn [this _] (_a this))
   :toString (fn [this] (format "%.1f" (double (_a this))))
   :toStringSuffix (fn [this] (toString this))
   :diff (fn [_ _] (Constant 0))
   })

(def Constant (constructor (fn [this a] (assoc this :a a)) ConstantPrototype))

(def name (field :name))
(def VariablePrototype
  {:evaluate (fn [this vars] (vars (clojure.string/lower-case (subs (name this) 0 1))))
   :toString (fn [this] (name this))
   :toStringSuffix (fn [this] (toString this))
   :diff (fn [this diffVariable] (if (= (name this) diffVariable)
                                   (Constant 1)
                                   (Constant 0)
                                   ))})

(def Variable (constructor (fn [this name] (assoc this :name name)) VariablePrototype))

(def OperationPrototype
  {:evaluate (fn [this vars] (apply (_operation this) (map #(evaluate % vars) (_operands this))))
   :toString (fn [this] (str "(" (_sign this) " " (clojure.string/join " " (map toString (_operands this))) ")"))
   :toStringSuffix (fn [this] (str "(" (clojure.string/join " " (map toStringSuffix (_operands this))) " " (_sign this) ")"))
   :diff (fn [this diffVariable] ((_diffFunc this) diffVariable (_operands this)))
   })

(defn operation-constructor
  [ctor operation sign diffFunc]
  (let [newPrototype (assoc OperationPrototype :operation operation, :sign sign, :diffFunc diffFunc)]
    (constructor ctor newPrototype)))

(defn _Expression [this & operands]
  (assoc this
    :operands operands))

(def Subtract (operation-constructor
                _Expression
                -
                "-"
                (fn [diffVariable operands] (Subtract (diff (nth operands 0) diffVariable) (diff (nth operands 1) diffVariable)))))

(def Add (operation-constructor
           _Expression
           +
           "+"
           (fn [diffVariable operands] (Add (diff (nth operands 0) diffVariable) (diff (nth operands 1) diffVariable)))))

(def Multiply (operation-constructor
                _Expression
                *
                "*"
                (fn [diffVariable operands] (Add
                                                (Multiply (diff (nth operands 0) diffVariable) (nth operands 1))
                                                (Multiply (diff (nth operands 1) diffVariable) (nth operands 0))))))

(def Divide (operation-constructor
              _Expression
              (fn [a b] (/ (double a) (double b)))
              "/"
              (fn [diffVariable operands] (Divide
                                       (Subtract
                                         (Multiply (diff (nth operands 0) diffVariable) (nth operands 1))
                                         (Multiply (nth operands 0) (diff (nth operands 1) diffVariable)))
                                       (Multiply (nth operands 1) (nth operands 1))))))

(def Negate (operation-constructor
              _Expression
              -
              "negate"
              (fn [diffVariable operands] (Negate (diff (nth operands 0) diffVariable)))))

(def Cos)
(def Sin (operation-constructor
           _Expression
           (fn [a] (Math/sin a))
           "sin"
           (fn [diffVariable operands] (Multiply (diff (nth operands 0) diffVariable) (Cos (nth operands 0))))))

(def Cos (operation-constructor
           _Expression
           (fn [a] (Math/cos a))
           "cos"
           (fn [diffVariable operands] (Multiply (diff (nth operands 0) diffVariable) (Negate (Sin (nth operands 0)))))))


(def signToOpObject {
               '+ Add
               '- Subtract
               '/ Divide
               '* Multiply
               'negate Negate
               'sin Sin
               'cos Cos
               })


(defn fromListToObjectExpr [expressionList] (cond
                                        (list? expressionList) (
                                                                 apply (signToOpObject (first expressionList)) (map fromListToObjectExpr (rest expressionList)))
                                        (number? expressionList) (Constant expressionList)
                                        (symbol? expressionList) (Variable (str expressionList))
                                        ))

(defn parseObject [expression] (fromListToObjectExpr (read-string expression)))
