(require 'clojure.walk)
(use 'cheshire.core)

(ns bakery.core
  (:gen-class))

"retrieve the data in products-data.json into a map"
(def products
  (:treats (clojure.walk/keywordize-keys (cheshire.core/parse-string (slurp "products-data.json")))))

"@shopping_list: a vector consisting in tuples [ProductName Quantity]
 @products: the products map"
(defn compute-price [shopping_list products]
  (reduce + (map (fn[i] 
    (let [product_data (first (filter #(= (:name %) (first i)) products))
        quant (last i)]
      (if (nil? (product_data :bulkPricing))
        (* (:price product_data) quant)
        (let [bulk_quant (int (/ quant (:amount (:bulkPricing product_data))))
                bulk_cost (* bulk_quant (:totalPrice (:bulkPricing product_data)))
                unit_quant (- quant (* bulk_quant (:amount (:bulkPricing product_data))))
                units_cost (* unit_quant (:price product_data))]
                (+ bulk_cost units_cost))
      ))) shopping_list)))

(defn -main
  [& args]
  (println "Usage: (compute price shopping_list products)"))
