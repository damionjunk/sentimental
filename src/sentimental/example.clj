(ns sentimental.example
  (:require [sentimental.anew :as anew]))


(count @(:english anew/scores))
;; => 0
;; Remember to load the lexicon before trying to score any data.
(anew/load-lexicon "/path/to/sentiment/data/original-anew-la.csv")

;; The words are stored in a map. Word -> score
(count @(:english anew/scores))
;; => 1030

(count @(:spanish anew/scores))
(count @(:portuguese anew/scores))


;; Compute the EN ANEW for the provided phrase
(anew/score-phrase "I really love my dog." :english)
;; => {:words ["love" "dog"],
;;     :score {:v 8.145000219345093, :d 6.680000066757202, :a 6.1000001430511475}}

;; Grab a value 
(get @(:english anew/scores) "love")
;; => {:a 6.44, :d 7.11, :v 8.72}


;; Compute the ES ANEW for the provided phrase
(anew/score-phrase "Odio mi pájaro, amo a mi perro. amor." :spanish)
;; ==> {:words ["odio" "pájaro" "perro" "amor"],
;;      :score {:v 6.385000109672546, :d 5.520000100135803, :a 5.507500052452087}}


;; Compute the ANEW without knowing which language the content may be
;; in. (Takes the 'best of the three', and returns a map with the
;; results)

(anew/score-phrase-langs
 "Odio mi pájaro, amo a mi perro. amor. I really love my dog.")
;; => {:v 6.385000109672546, :d 5.520000100135803, :a 5.507500052452087,
;;     :anew-es {:words ["odio" "pájaro" "perro" "amor"],
;;              :score {:v 6.385000109672546,
;;                      :d 5.520000100135803,
;;                      :a 5.507500052452087}}}

