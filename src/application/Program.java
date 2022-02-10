package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter client data: ");

        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        Date birthDate = sdf2.parse(sc.nextLine());

        Client client = new Client(clientName, email, birthDate);

        System.out.println();
        System.out.println("Enter order data: ");

        System.out.print("Status: ");
        String status = sc.nextLine();

        Order order = new Order(cal.getTime(), OrderStatus.valueOf(status), client);

        System.out.println();
        System.out.print("How many items in this order? ");
        int n = sc.nextInt();

        if (n < 1) {
            System.out.println("Order cancelled\n");
        }

        else {

            for (int i = 0; i < n; i++) {
                sc.nextLine();
                System.out.println("Enter #" + (i + 1) + " item data:");
                System.out.print("Product name: ");
                String productName = sc.nextLine();
                System.out.print("Product price: ");
                double productPrice = sc.nextDouble();
                System.out.print("Quantity: ");
                int productQuantity = sc.nextInt();
                System.out.println();

                order.addItem(new OrderItem(productQuantity, productPrice, new Product(productName, productPrice)));
            }

        }

        System.out.println("ORDER SUMMARY: ");
        System.out.println("Order moment: " + sdf.format(cal.getTime()));
        System.out.println("Order status: " + status);
        System.out.println("Client: " + client.getName() + " (" + sdf2.format(client.getBirthDate()) + ") - " + client.getEmail());
        System.out.println("Order Items:");
        for (OrderItem orderItem : order.getOrderItems()) {
            System.out.println(orderItem.getProduct().getName()
                    + ", $ "
                    + String.format("%.2f", orderItem.getPrice())
                    + ", Quantity: "
                    + orderItem.getQuantity()
                    + ", Subtotal: $ "
                    + String.format("%.2f", orderItem.subTotal()));
        }
        System.out.println("Total price: $ " + String.format("%.2f", order.total()));

        sc.close();
    }
}
