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
