package application;

import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Product p1 = new Product("TV", 1000.00);
        OrderItem o1 = new OrderItem(2, p1.getPrice(), p1);

        Order order = new Order(sdf.parse("09/02/2022 03:49:55"), OrderStatus.valueOf("PROCESSING"));
        order.addItem(o1);

        System.out.println(p1);
        System.out.println();
        System.out.println(o1);
        System.out.println();
        System.out.println(o1.subTotal());
        System.out.println();
        System.out.println(order);
        System.out.println();
        System.out.println(order.total());

    }
}
