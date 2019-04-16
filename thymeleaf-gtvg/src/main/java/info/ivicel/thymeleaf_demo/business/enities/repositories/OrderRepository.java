package info.ivicel.thymeleaf_demo.business.enities.repositories;

import info.ivicel.thymeleaf_demo.business.enities.Customer;
import info.ivicel.thymeleaf_demo.business.enities.Order;
import info.ivicel.thymeleaf_demo.business.enities.OrderLine;
import info.ivicel.thymeleaf_demo.business.enities.Product;
import info.ivicel.thymeleaf_demo.business.util.CalendarUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private static final OrderRepository INSTANCE = new OrderRepository();
    private static Map<Integer, List<Order>> ordersByCustomerId;
    private static Map<Integer, Order> ordersById;


    private OrderRepository() {
        ordersByCustomerId = new LinkedHashMap<Integer, List<Order>>();
        ordersById = new LinkedHashMap<Integer, Order>();

        final Customer cust1 = CustomerRepository.getInstance().findById(1);
        ordersByCustomerId.put(cust1.getId(), new ArrayList<Order>());

        final Customer cust4 = CustomerRepository.getInstance().findById(4);
        ordersByCustomerId.put(cust4.getId(), new ArrayList<Order>());

        final Customer cust6 = CustomerRepository.getInstance().findById(6);
        ordersByCustomerId.put(cust6.getId(), new ArrayList<Order>());


        final Product prod1 = ProductRepository.getInstance().findById(1);
        final Product prod2 = ProductRepository.getInstance().findById(2);
        final Product prod3 = ProductRepository.getInstance().findById(3);
        final Product prod4 = ProductRepository.getInstance().findById(4);


        final Order order1 = new Order();
        order1.setId(1);
        order1.setCustomer(cust4);
        order1.setDate(CalendarUtil.calendarFor(2009, 1, 12, 10, 23));
        ordersById.put(order1.getId(), order1);
        ordersByCustomerId.get(cust4.getId()).add(order1);

        final OrderLine orderLine11 = new OrderLine();
        orderLine11.setProduct(prod2);
        orderLine11.setAmount(2);
        orderLine11.setPurchasePrice(new BigDecimal("0.99"));
        order1.getOrderLines().add(orderLine11);

        final OrderLine orderLine12 = new OrderLine();
        orderLine12.setProduct(prod3);
        orderLine12.setAmount(4);
        orderLine12.setPurchasePrice(new BigDecimal("2.50"));
        order1.getOrderLines().add(orderLine12);

        final OrderLine orderLine13 = new OrderLine();
        orderLine13.setProduct(prod4);
        orderLine13.setAmount(1);
        orderLine13.setPurchasePrice(new BigDecimal("15.50"));
        order1.getOrderLines().add(orderLine13);


        final Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(cust6);
        order2.setDate(CalendarUtil.calendarFor(2010, 6, 9, 21, 01));
        ordersById.put(order2.getId(), order2);
        ordersByCustomerId.get(cust6.getId()).add(order2);

        final OrderLine orderLine21 = new OrderLine();
        orderLine21.setProduct(prod1);
        orderLine21.setAmount(5);
        orderLine21.setPurchasePrice(new BigDecimal("3.75"));
        order2.getOrderLines().add(orderLine21);

        final OrderLine orderLine22 = new OrderLine();
        orderLine22.setProduct(prod4);
        orderLine22.setAmount(2);
        orderLine22.setPurchasePrice(new BigDecimal("17.99"));
        order2.getOrderLines().add(orderLine22);



        final Order order3 = new Order();
        order3.setId(3);
        order3.setCustomer(cust1);
        order3.setDate(CalendarUtil.calendarFor(2010, 7, 18, 22, 32));
        ordersById.put(order3.getId(), order3);
        ordersByCustomerId.get(cust4.getId()).add(order3);

        final OrderLine orderLine32 = new OrderLine();
        orderLine32.setProduct(prod1);
        orderLine32.setAmount(8);
        orderLine32.setPurchasePrice(new BigDecimal("5.99"));
        order3.getOrderLines().add(orderLine32);

    }

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    public Order findById(Integer id) {
        return ordersById.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<Order>(ordersById.values());
    }

    public List<Order> findByCustomerId(Integer id) {
        final List<Order> orders = ordersByCustomerId.get(id);
        if (orders == null) {
            return new ArrayList<Order>();
        }
        return orders;
    }
}
