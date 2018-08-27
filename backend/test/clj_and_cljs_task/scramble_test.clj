(ns clj-and-cljs-task.scramble-test
  (:require [clojure.test :refer :all]
            [clj-and-cljs-task.scramble :refer :all]))

(deftest scramble
  (testing "With an empty string"
    (is (scramble? "abcd" "")))
  (testing "With 2 empty strings"
    (is (scramble? "" "")))
  (testing "With one character"
    (is (scramble? "abcd" "c")))
  (testing "With different char number"
    (is (not (scramble? "abcd" "cc"))))
  (testing "With disjunct input strings"
    (is (not (scramble? "abcd" "wxyz"))))
  (testing "With wrong parameter type"
    (is (thrown? AssertionError (scramble? 10 0))))
  (testing "With numbers in parameter values"
    (is (thrown? ArrayIndexOutOfBoundsException (scramble? "abcd1234" "4a"))))
  (testing "With capital letters in parameter values"
    (is (thrown? ArrayIndexOutOfBoundsException (scramble? "aBCd" "CB")))))
  
