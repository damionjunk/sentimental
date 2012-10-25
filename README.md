# Sentimental

A simple Clojure library designed to perform sentiment analysis using
the ANEW: Affective norms for English words [anew][]. This data has
been augmented to include (and make use of) Spanish and Portuguese 
translations of the ANEW English terms.

## Reference

Bradley, M. M., & Lang, P. J. (1999). 
Affective norms for English words (ANEW): 
Instruction manual and affective ratings. 
University of Florida: the Center for Research in Psychophysiology.

[anew]: http://csea.phhp.ufl.edu/media/anewmessage.html  "ANEW"

## Usage

Add the following to your Leiningen configuration

```clojure
;; yet to be released to clojars ...
[sentimental "0.1.0-SNAPSHOT"]
```

Once you've obtained the ANEW data file containing the words to scores
map, usage is straightforward. Due to license issues of the ANEW
lexicon, the data file is not distributed with this code. 

```clojure
(ns sentimental.example
  (:require [sentimental.anew :as anew]))
```

Load the lexicon:

```clojure
(count @(:english anew/scores))
;; => 0
;; Remember to load the lexicon before trying to score any data.
(anew/load-lexicon "/path/to/sentiment/lexicon/original-anew-la.csv")
```

Counts of sentiment data:

```clojure
;; The words are stored in a map. Word -> score
(count @(:english anew/scores))
;; => 1030

(count @(:spanish anew/scores))
(count @(:portuguese anew/scores))
```

Generate the ANEW score for a phrase:

```clojure
;; Compute the EN ANEW for the provided phrase
(anew/score-phrase "I really love my dog." :english)
;; => {:words ["love" "dog"],
;;     :score {:v 8.145000219345093, :d 6.680000066757202, :a 6.1000001430511475}}
```

Find a specific word in the initialized map.

```clojure
;; Grab a value 
(get @(:english anew/scores) "love")
;; => {:a 6.44, :d 7.11, :v 8.72}
```

Generate the ANEW score for a Spanish phrase:

```clojure
;; Compute the ES ANEW for the provided phrase
(anew/score-phrase "Odio mi pájaro, amo a mi perro. amor." :spanish)
;; ==> {:words ["odio" "pájaro" "perro" "amor"],
;;      :score {:v 6.385000109672546, :d 5.520000100135803, :a 5.507500052452087}}
```

Compute the "Best of Three" match for a phrase where we're only
interested in scoring for one language.

```clojure
(anew/score-phrase-langs
 "Odio mi pájaro, amo a mi perro. amor. I really love my dog.")
;; => {:v 6.385000109672546, :d 5.520000100135803, :a 5.507500052452087,
;;     :anew-es {:words ["odio" "pájaro" "perro" "amor"],
;;              :score {:v 6.385000109672546,
;;                      :d 5.520000100135803,
;;                      :a 5.507500052452087}}}
```

## License

Copyright © 2012 Damion Junk

Distributed under the Eclipse Public License, the same as Clojure.
