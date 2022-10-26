(defn vectorOperation [f] (fn [vectorX vectorY] (mapv f vectorX vectorY)))

(def v+ (vectorOperation +))
(def v- (vectorOperation -))
(def v* (vectorOperation *))
(def vd (vectorOperation /))

(defn v*s [vector scalar] (mapv (fn [xi] (* xi scalar)) vector))

(defn scalar [x y] (apply + (v* x y)))

(defn coordinateMulSub [xi yi x y] (-
                                     (* (nth x xi) (nth y yi))
                                     (* (nth x yi) (nth y xi))
                                     ))

(defn vect [x y] (vector
                   (coordinateMulSub 1 2 x y)
                   (coordinateMulSub 2 0 x y)
                   (coordinateMulSub 0 1 x y)
                   ))

(defn matrixOperation [f] (fn [matrixA matrixB] (mapv f matrixA matrixB)))

(def m+ (matrixOperation v+))
(def m* (matrixOperation v*))
(def m- (matrixOperation v-))
(def md (matrixOperation vd))

(defn m*s [matrix scalar] (mapv (fn [vector] (v*s vector scalar)) matrix))
(defn m*v [matrix vector] (mapv (fn [xi] (scalar xi vector)) matrix))

(defn transpose [matrix] (apply mapv vector matrix))
(defn m*m [matrixA matrixB] (mapv (fn [x] (mapv (fn [y] (scalar x y)) (transpose matrixB))) matrixA))


(defn cuboidOperation [f] (fn [cuboidA cuboidB] (mapv f cuboidA cuboidB)))

(def c+ (cuboidOperation m+))
(def c- (cuboidOperation m-))
(def c* (cuboidOperation m*))
(def cd (cuboidOperation md))













