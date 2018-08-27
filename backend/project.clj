(defproject clj-and-cljs-task "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :jvm-opts ["--add-modules" "java.xml.bind"]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [http-kit "2.2.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring-cors "0.1.12"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot clj-and-cljs-task.server
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
