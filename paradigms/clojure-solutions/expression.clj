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


; HW 11: Combinatorial parsers
(defn -return [value tail] {:value value :tail tail})
(def -valid? boolean)
(def -value :value)
(def -tail :tail)
(defn -show [result] (if (-valid? result) (str "-> " (pr-str (-value result)) " | " (pr-str (apply str (-tail result)))) "!"))
(defn tabulate [parser inputs] (run! (fn [input] (printf "    %-10s %s\n" (pr-str input) (-show (parser input)))) inputs))

(defn _empty [value] (partial -return value))
(defn _char [p] (fn [[c & cs]] (if (and c (p c)) (-return c cs))))
(defn _map [f result] (if (-valid? result) (-return (f (-value result)) (-tail result))))
(defn _combine [f a b]
  (fn [input]
    (let [ar ((force a) input)]
      (if (-valid? ar)
        (_map (partial f (-value ar))
              ((force b) (-tail ar)))))))
(defn _either [a b]
  (fn [input]
    (let [ar ((force a) input)]
      (if (-valid? ar)
        ar
        ((force b) input)))))
(defn _parser [p] (let [pp (_combine (fn [v _] v) p (_char #{\u0000}))] (fn [input] (-value (pp (str input \u0000))))))

(defn +char [chars] (_char (set chars)))
(defn +char-not [chars] (_char (comp not (set chars))))
(defn +map [f parser] (comp (partial _map f) parser))
(def +ignore (partial +map (constantly 'ignore)))
(defn iconj [coll value] (if (= value 'ignore) coll (conj coll value)))
(defn +seq [& ps] (reduce (partial _combine iconj) (_empty []) ps))
(defn +seqf [f & ps] (+map (partial apply f) (apply +seq ps)))
(defn +seqn [n & ps] (apply +seqf #(nth %& n) ps))
(defn +or [p & ps] (reduce _either p ps))
(defn +opt [p] (+or p (_empty nil)))
(defn +star [p] (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] (rec)))
(defn +plus [p] (+seqf cons p (+star p)))
(defn +str [p] (+map (partial apply str) p))
(def +parser _parser)

(def *digit (+char "0123456789"))
(def *number (+map read-string (+str (+seq
                                       (+opt (+char "-"))
                                       (+str (+plus *digit))
                                       (+opt (+char "."))
                                       (+str (+star *digit))))))
(def *space (+char " \t\n\r"))
(def *ws (+ignore (+star *space)))

(def *negate (+str (+seq (+char "n") (+char "e") (+char "g") (+char "a") (+char "t") (+char "e"))))
(def *sign (+map #(signToOpObject %) (+map (comp symbol str) (+or (+char "+-*/") *negate))))

(defn *seq [begin p end]
  (+seqn 1
         (+char begin)
         (+opt (+seqf cons *ws p (+star (+seqn 0 *ws (+or p *sign)))))
         *ws
         (+char end)))

(def *constant (+map Constant *number))
(def *variable (+map Variable (+str (+plus (+char "XxYyZz")))))

(defn *operation [parser] (+map #(apply (last %) (butlast %)) (*seq "(" parser ")")))

(def expression-parser
  (letfn [(*value []
            (delay (+or
                     *variable
                     *constant
                     (*operation (*value)))))]
    (+parser (+seqn 0 *ws (*value) *ws))))

(def parseObjectSuffix expression-parser)









