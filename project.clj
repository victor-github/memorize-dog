(defproject bakery "0.1.0-SNAPSHOT"
  :description "bakery"
  :dependencies [[org.clojure/clojure "1.8.0"] [cheshire "5.8.1"]]
  :main ^:skip-aot bakery.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
