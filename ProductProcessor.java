import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Product {
    String name, category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return name + " - " + category + " - $" + price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 800),
            new Product("Phone", "Electronics", 500),
            new Product("TV", "Electronics", 1200),
            new Product("Sofa", "Furniture", 700),
            new Product("Chair", "Furniture", 150)
        );

        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(groupingBy(p -> p.category));

        Map<String, Optional<Product>> mostExpensive = products.stream()
            .collect(groupingBy(p -> p.category, maxBy(Comparator.comparingDouble(p -> p.price))));

        double avgPrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0);

        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        mostExpensive.forEach((category, product) -> {
            System.out.println("Most Expensive in " + category + ": " + product.orElse(null));
        });

        System.out.println("Average Price: $" + avgPrice);
    }
}
