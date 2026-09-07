// User-defined exception for invalid product ID
class InvalidProductException extends Exception {

    InvalidProductException(String message) {
        super(message);
    }
}

// User-defined exception for insufficient stock
class InsufficientStockException extends Exception {

    InsufficientStockException(String message) {
        super(message);
    }
}

// User-defined exception for invalid quantity
class InvalidQuantityException extends Exception {

    InvalidQuantityException(String message) {
        super(message);
    }
}

// User-defined exception for invalid payment
class InvalidPaymentException extends Exception {

    InvalidPaymentException(String message) {
        super(message);
    }
}

// Product class
class Product {

    private int productId;
    private String productName;
    private double price;
    private int stock;

    // Constructor
    Product(int productId, String productName,
            double price, int stock) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        stock = stock - quantity;
    }
}

// Order class
class Order {

    // Process order
    public void processOrder(Product product,
                             int quantity,
                             double payment)
            throws InvalidProductException,
                   InvalidQuantityException,
                   InsufficientStockException,
                   InvalidPaymentException {

        // Validate product
        if (product == null) {
            throw new InvalidProductException(
                    "Invalid Product ID.");
        }

        // Validate quantity
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero.");
        }

        // Check stock
        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                    "Insufficient stock available.");
        }

        // Calculate total
        double total = product.getPrice() * quantity;

        // Validate payment
        if (payment < total) {
            throw new InvalidPaymentException(
                    "Insufficient payment amount.");
        }

        // Reduce stock
        product.reduceStock(quantity);

        System.out.println("Order processed successfully.");
        System.out.println("Product: " +
                product.getProductName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Amount: Rs." + total);
        System.out.println("Payment: Rs." + payment);
        System.out.println("Balance: Rs." +
                (payment - total));
    }
}

// Main class
public class EcommerceDemo {

    public static void main(String[] args) {

        Product product = new Product(
                101,
                "Wireless Mouse",
                500,
                10
        );

        Order order = new Order();

        // Valid order
        try {

            order.processOrder(product, 2, 1200);

        } catch (Exception e) {

            System.out.println("Error: " +
                    e.getMessage());
        }

        // Invalid quantity
        try {

            order.processOrder(product, 0, 500);

        } catch (Exception e) {

            System.out.println("\nError: " +
                    e.getMessage());
        }

        // Insufficient stock
        try {

            order.processOrder(product, 20, 10000);

        } catch (Exception e) {

            System.out.println("Error: " +
                    e.getMessage());
        }

        // Invalid payment
        try {

            order.processOrder(product, 2, 500);

        } catch (Exception e) {

            System.out.println("Error: " +
                    e.getMessage());
        }

        // Invalid product
        try {

            order.processOrder(null, 1, 500);

        } catch (Exception e) {

            System.out.println("Error: " +
                    e.getMessage());
        }
    }
}