package com.sline.sline.service.project.order;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.order.Status;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.system.User;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order findById(Long id);
    Order findByUuid(String uuid);
    List<Order> findAll();
    List<Order> findAllByStatus(Status status);
    List<Order> findAllByDeliveryTime(Date deliveryTime);
    List<Order> findAllByUser(User user);
    List<Order> findAllByUserUuid(String userUuid);
    List<Order> findAllByPerson(Person person);
    List<Order> findAllByPersonUuid(String personUuid);
    List<Order> findAllByProduct(Product product);
    List<Order> findAllByProductUuid(String productUuid);
    void save(Order order);
    void delete(Order order);
    void delete(String uuid);
}
