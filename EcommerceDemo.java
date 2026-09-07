class InvalidProductException extends Exception {
    InvalidProductException(String message) {
        super(message);
    }
}
class InsufficientStockException extends Exception {

    InsufficientStockException(String message) {
        super(message);
    }
}
class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}
class InvalidPaymentException extends Exception {

    InvalidPaymentException(String message) {
        super(message);
    }
}
class Product {
    private int productId;
    private String productName;
    private double price;
    private int stock;
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
class Order {
    public void processOrder(Product product,
                             int quantity,
                             double payment)
            throws InvalidProductException,
                   InvalidQuantityException,
                   InsufficientStockException,
                   InvalidPaymentException {
        if (product == null) {
            throw new InvalidProductException(
                    "Invalid Product ID.");
        }
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero.");
        }
        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                    "Insufficient stock available.");
        }
        double total = product.getPrice() * quantity;
        if (payment < total) {
            throw new InvalidPaymentException(
                    "Insufficient payment amount.");
        }
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
public class EcommerceDemo {

    public static void main(String[] args) {
        Product product = new Product(
                101,
                "Wireless Mouse",
                500,
                10  );
        Order order = new Order();
        try {
            order.processOrder(product, 2, 1200);
        } catch (Exception e) {
            System.out.println("Error: " +
                    e.getMessage());
        }
        try {
            order.processOrder(product, 0, 500);
        } catch (Exception e) {
            System.out.println("\nError: " +
                    e.getMessage());
        }
        try {
            order.processOrder(product, 20, 10000);
        } catch (Exception e) {

            System.out.println("Error: " +
                    e.getMessage());
        }
        try {
            order.processOrder(product, 2, 500);
        } catch (Exception e) {
            System.out.println("Error: " +
                    e.getMessage());
        }
        try {
            order.processOrder(null, 1, 500);
        } catch (Exception e) {
            System.out.println("Error: " +
                    e.getMessage());
        }
    }
}
