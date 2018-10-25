# bakery

This is the code for bakery test exercise.

## Usage

The best way to run this is by using Leiningen. Run "lein repl" in the root project directory.

Compute shopping list prices by calling the compute-price function and passing a vector consisting of tuples ["Name" Quantity] and the products map. Each tuple consists of the product name and the product quantity, e.g. ["Brownie", 2]. The product name must be one of the product names listed in the input products-data.json file, e.g.
(compute-price [["Cookie" 1] ["Brownie" 4] ["Key Lime Cheesecake" 1]] (get-products))

## Implementation Description

I'm using the well-known cheshire library to easily parse the json into a map, through the products function.

The main functionality is in the "compute-price" function. This one takes two arguments, shopping_list and a products map. The shopping-list has the format described above.

We first map through each ["Product Name", Qty] tuple and calculate the price. The "reduce" outer function then aggreggates the sum of all of these.

Price calculation is done by looking at the product data and determining if there is bulk pricing or not. If there is no bulk pricing, we simply multiply the given quantity with the product price.
The more complicated case occurs if there is bulk pricing. In that case, we determine the bulk quantity first, in bulk_quant: this is the largest number of multiples of the bulk amount. We determine this by taking the floor of dividing the quantity requested to the bulk pricing amount. bulk_cost is then determined by multiplying this bulk quantity to the price of one "bulk". The remainder of the items will be billed at unit (non-bulk) price: to determine that, we find the reminder number of items in unit_quant, and for those we compute unit cost by multiplying that by the regular price. Finally we add the bulk_cost and units_cost to determine total price for the item.

We repeat that for each item and reduce over all to sum them up to determine the price for all items.

## Tests

Please find all tests in bakery/test/bakery/core_test.clj. They can be run with "lein test".

## Extensions

This could be easily extended to ask the user input for the products filename, and to explicitly prompt the user for each shopping item. It could also validate product names to make sure they match those in the products file.


