(ns circles.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

;;; Circles Game Basic Setup
;;; CSCI 2041 Homework #7
;;; Spring 2016

;;; Constants
(def speed 5)                          ;maximm speed circles move
(def lives 3)

;---------------------------------------------------------------------
; Setup
;---------------------------------------------------------------------

(defn make-circle 
  "Creates a circle with a random color and set speed and heading."
   [x y]
  (let [angle (rand q/TWO-PI)          ;random angle
        cur-speed (+ (rand speed) 1)]  ;random speed up to our constant
       {:x x                           ;set this circle's x
      :y y                           ;set this circle's y
        :size (+ 10 (rand 15))         ;set random diameter 
      :color (rand 255)              ;make this colorful      
      :speed cur-speed               ;set this circle's speed
      :heading angle}                ;set this circle's heading
    ))                                 ;returns circle

(defn make-square [x y]
  {:x x
   :y y
   :w 20
   :h 20
   :color (rand 255)})


(defn setup 
  "Set up a sketch and return initial state."
  []
  (q/frame-rate 30)                    ;frequency update and draw functions
  (q/color-mode :hsb)  ;how we represent colors
  
  (let [size (q/width)
        n 20
        bg 250]
       (q/background bg)               ;nice light grey color for the bg
       ;; need to make n circles of random sizes
       ;; here we make only one circle in a list
       {:circles  (makenlist n)
        :square (list (make-square 250 250))
        :squaredirection \w
        :running? true                 ;so we can pause and unpause in update
        :n n                           ;how many circles
        :size size                     ;how big is the sketch
        :bg bg                         ;we might want to change this later
        }))

(defn makenlist [n]
(def x (atom (list)))
(loop [i 0] (when (not= i n) 
  (swap! x conj (make-circle (rand 400) (rand 400)))
  (recur (inc i))
))
@x
)
    
;---------------------------------------------------------------------
; Update functions
;---------------------------------------------------------------------

(defn move-circle 
  "Moves a circle according to its speed and heading"
  [c state]
  (let [n (bounce-back c (:size c))]
  (merge n {:x (+ (:x c) (* (Math/cos (:heading n)) (:speed n))) 
            :y (+ (:y c) (* (Math/sin (:heading n)) (:speed n)))
            }))
  )

(defn move-square [s state ]
  (cond (= \w (:squaredirection state)) (merge s {:y (+ (:y s) 10)})
        (= \s (:squaredirection state)) (merge s {:y (- (:y s) 10)})
        (= \d (:squaredirection state)) (merge s {:x (+ (:x s) 10)})
        (= \a (:squaredirection state)) (merge s {:x (- (:x s) 10)}))
  )

(defn bounce-back [c size]
  (cond (< 500 (+ (:x c) (* (Math/cos (:heading c)) (:speed c))))
          (cond (< 500 (+ (:x c) (* (Math/cos (+ (:heading c) 
                                         (/ Math/PI) 2)))))
              (merge c {:heading (+ (:heading c) (/ (* 3 Math/PI) 2))})
                  :else (merge c {:heading (+ (:heading c) (/ Math/PI 2))}))
        (< 500 (+ (:y c) (* (Math/sin (:heading c)) (:speed c))))
          (cond (< 500 (+ (:y c) (* (Math/sin (+ (:heading c) 
                                         (/ Math/PI) 2)))))
              (merge c {:heading (+ (:heading c) (/ (* 3 Math/PI) 2))})
                  :else (merge c {:heading (+ (:heading c) (/ Math/PI 2))}))
        (> 0 (+ (:x c) (* (Math/cos (:heading c)) (:speed c))))
          (cond (> 0 (+ (:x c) (* (Math/cos (+ (:heading c) 
                                         (/ Math/PI) 2)))))
              (merge c {:heading (+ (:heading c) (/ (* 3 Math/PI) 2))})
                  :else (merge c {:heading (+ (:heading c) (/ Math/PI 2))}))
        (> 0 (+ (:y c) (* (Math/sin (:heading c)) (:speed c))))
          (cond (> 0 (+ (:y c) (* (Math/sin (+ (:heading c) 
                                         (/ Math/PI) 2)))))
              (merge c {:heading (+ (:heading c) (/ (* 3 Math/PI) 2))})
                  :else (merge c {:heading (+ (:heading c) (/ Math/PI 2))}))
  :else c))

(defn update-circles 
  "Moves each circle and returns updated vector of circles."
  [circles state]
  (map (fn [c] (move-circle c state)) circles))

(defn update-square [square state]
  (map (fn [s] (move-square s state)))
  )

(defn update-state 
  "Updates sketch state. If it is paused, then the state is returned unmodified."
  [state]
  (if (:running? state)
      ;add some movement and update functions so the next line moves circles
    (do
    (assoc state :square (update-square (:square state) state))
    (assoc state :circles (update-circles (:circles state) state)))
      state))

;---------------------------------------------------------------------
; Draw functions
;---------------------------------------------------------------------

(defn draw-circle 
  "Draws an individual circle with correct color, location, and size."
  [c] 
  (q/fill (:color c) 255 255)
  (q/ellipse (:x c) (:y c) (:size c) (:size c)))

(defn draw-square [s] 
  (q/fill (:color s) 255 255)
  (q/rect (:x s) (:y s) (:w s) (:h s)))

(defn draw-state 
  "Draws the sketch state."
  [state]
  (q/background (:bg state))                    ;update the background
  (q/stroke 1)  
  (q/text "Lives: " 50 50);how wide should the lines be
  (q/text (str lives) 85 50)
  (dorun (map draw-circle (:circles state)) 
         (map draw-square (:square state)))    ;map is lazy
  (q/fill 0)
  )

;---------------------------------------------------------------------
; User interaction functions
;---------------------------------------------------------------------

(defn mouse-clicked 
  "Changes background color to different shades of grey."
  [state event]
  (update-in state [:bg] (fn [n] (rand-int 255))))

(defn key-pressed 
  "Process key event.  p will pause/unpause everything."
  [state event]
  (condp = (:key event)
    :p (update-in state [:running?] not)
    :w (update-in state [:squaredirection] \w)
    :s (update-in state [:squaredirection] \s)
    :a (update-in state [:squaredirection] \a)
    :d (update-in state [:squaredirection] \d)
    state))

(q/defsketch circles
    :host "host"
    :size [500 500]                ;we need a square canvas
    :setup setup                   ;getting things started, setting initial state
    :update update-state           ;the function to update the state
    :draw draw-state               ;the necessary draw function
    :mouse-clicked mouse-clicked   ;this is our mouse click event
    :key-pressed key-pressed       ;this is our keyboard input event
    :middleware [m/fun-mode])      ;this gives us the ability to have state