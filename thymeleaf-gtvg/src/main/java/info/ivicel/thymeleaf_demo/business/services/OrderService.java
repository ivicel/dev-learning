package info.ivicel.thymeleaf_demo.business.services;

import info.ivicel.thymeleaf_demo.business.enities.Order;
import info.ivicel.thymeleaf_demo.business.enities.repositories.OrderRepository;
import java.util.List;

public class OrderService {



    public List<Order> findAll() {
        return OrderRepository.getInstance().findAll();
    }

    public Order findById(Integer orderId) {
        return OrderRepository.getInstance().findById(orderId);
    }
}
