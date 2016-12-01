;; QUESTION #1 requests w/o a hosthame 
(defn count-IP []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
 (def x (atom 0))
 (def y (atom 0)) 
 (doseq [line (line-seq rdr)] 
 	(reset! x (+ @x (count (re-seq #"\d{1,3}[.]\d{1,3}[.]\d{1,3}[.]\d{1,3}" line)))))
 @x))

;;QUESTION 2 PRIVATE IP ADDRESSES 
(defn find-private-IP []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom 0))
(doseq [line (line-seq rdr)]
	(cond (not= nil (re-seq #"127.\d{1}[.][0-1][0-2][0-7]" line)) (swap! x inc)
		(not= nil (re-seq #"10.\d{1}[.]\d{1}[.][0-1]0" line)) (swap! x inc)
		(not= nil (re-seq #"192.168.\d{1}[.][0-1][0-9][0-2]" line)) (swap! x inc)
		(not= nil (re-seq #"192.\d{3}[.]\d{3}" line)) (swap! x inc))
	)
	@x))


;;QUESTION 3 
(defn count-request-on-dates []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom 0))
(doseq [line (line-seq rdr)]
	(cond (not= nil (re-seq #"05/Jul/\d{4}" line))
	(swap! x inc)
	(not= nil (re-seq #"04/Jul/\d{4}" line)) (swap! x inc)))
@x))


;;QUESTION 4 distinct hosts between 10:00 -10:29 july 5th
(defn count-hosts []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom (vector)))
(doseq [line (line-seq rdr)]
	(if (not= nil (re-seq #"05/Jul/\d{4}" line))
		(if (not= nil (re-seq #"22:[0-2][0-9][:]" line))
 			(swap! x conj line)
 			))) @x))

;;QUESTION 5 SERVER ERROR 5XXX
(defn find-server-error []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom (vector)))
(doseq [line (line-seq rdr)]
	(if (not= nil (re-seq #"\s5\d{2}\s" line)) 
	(swap! x conj line)))
	@x))

;;QUESTION 6 count of REDIRECTED RESPONSE CODE 3XX
(defn count-redirect []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom 0))
(doseq [line (line-seq rdr)]
	(if (not= nil (re-seq #"\s3\d{2}\s" line)) 
	(swap! x inc)))
	@x))


;;QUESTION 7 COUNT OF REQUESTS >50,000 BYTES
(defn count-morethan50000 []
(with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
(def x (atom 0))
(doseq [line (line-seq rdr)]
	(cond (not= nil (re-seq #"[5-9]\d{4}" line)) 
		(swap! x inc) 
		(not= nil (re-seq #"\s\d{6,8}" line)) (swap! x inc)))
	@x))


