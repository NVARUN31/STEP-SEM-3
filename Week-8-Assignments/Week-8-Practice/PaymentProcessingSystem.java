import java.util.*;

class Product {
    String name;
    double price;
    Product(String name, double price) { this.name = name; this.price = price; }
}

class PaymentCustomer {
    String name;
    PaymentCustomer(String name) { this.name = name; }
    String getName() { return name; }
}

interface PaymentMethod {
    boolean pay(double amount);
}

class CreditCard implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.printf("Credit card payment successful: $%.2f%n", amount);
        return true;
    }
}

class PayPal implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("PayPal payment failed.");
        return false;
    }
}

class BankTransfer implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.printf("Bank transfer successful: $%.2f%n", amount);
        return true;
    }
}

class Order {
    PaymentCustomer customer;
    List<Product> products = new ArrayList<>();
    String status = "Pending";

    Order(PaymentCustomer customer) { this.customer = customer; }

    void addProduct(Product product) { products.add(product); }

    double getTotal() {
        double total = 0;
        for (Product p : products) total += p.price;
        return total;
    }

    void checkout(PaymentMethod method) {
        if (products.isEmpty()) {
            System.out.println("Cannot checkout: order is empty.");
            return;
        }
        System.out.println("Customer: " + customer.getName());
        System.out.printf("Order total: $%.2f%n", getTotal());
        status = method.pay(getTotal()) ? "Paid" : "Payment Failed";
        System.out.println("Order status: " + status);
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        PaymentCustomer customer = new PaymentCustomer("Varun");
        Order emptyOrder = new Order(customer);
        emptyOrder.checkout(new CreditCard());

        Order order = new Order(customer);
        order.addProduct(new Product("Keyboard", 40));
        order.addProduct(new Product("Mouse", 20));
        order.checkout(new CreditCard());

        Order failedOrder = new Order(customer);
        failedOrder.addProduct(new Product("Headphones", 50));
        failedOrder.checkout(new PayPal());
    }
}
