(def myhistory (atom (list)))
(def theirhistory (atom (list)))

(def gamematrix '[[[\c \c] [3 3]]
					[[\c \d] [0 5]]
					[[\d \c] [5 0]]
					[[\d \d] [1 1]]])


(defn compete-n-times [strategy1 strategy2 n]
	(reset! myhistory (list))
	(reset! theirhistory (list))
(def p1score (atom 0))
(def p2score (atom 0))
(loop [i 1] (when (<= i n) 
	(def a @myhistory)
	(def b @theirhistory)
	(let [x (strategy1 a b)]
	(let [y (strategy2 a b)]
	(swap! myhistory conj x)
	(swap! theirhistory conj y)
	(reset! p1score (+ @p1score (first (getscores (vector x y)))))
	(reset! p2score (+ @p2score (second (getscores (vector x y)))))))
	(recur (inc i))))
	(list @p1score @p2score)
	)


(defn all-defect [history theirhistory]
	\d)

(defn all-cooperate [history theirhistory]
	\c)

(defn random [history ophistory]
(let [x (rand-int 100)]
(cond (even? x) \d 
	:else \c))
	)

(defn tit-for-tat [history ophistory]
	(cond (empty? ophistory) \c
	:else (first ophistory)
	))

(defn tit-for-two-tats [history ophistory]
	(cond (and (= (first ophistory) \d) 
		(= (second ophistory) \d)) \d
		:else \c 
		)
	)


(defn getscores [result] 
	(cond (= '[\c \c] result) (second (first gamematrix))
		(= '[\c \d] result) (second (second gamematrix))
		(= '[\d \c] result) (second (nth gamematrix 2))
		(= '[\d \d] result) (second (nth gamematrix 3))
		))