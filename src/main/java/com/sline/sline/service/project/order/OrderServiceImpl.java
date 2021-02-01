package com.sline.sline.service.project.order;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.order.Status;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.system.User;
import com.sline.sline.repository.project.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order findByUuid(String uuid) {
        return orderRepository.findByUuid(uuid);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByStatus(Status status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public List<Order> findAllByDeliveryTime(Date deliveryTime) {
        return orderRepository.findAllByDeliveryTimeBefore(deliveryTime);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public List<Order> findAllByUserUuid(String userUuid) {
        return orderRepository.findAllByUser_Uuid(userUuid);
    }

    @Override
    public List<Order> findAllByPerson(Person person) {
        return orderRepository.findAllByPerson(person);
    }

    @Override
    public List<Order> findAllByPersonUuid(String personUuid) {
        return orderRepository.findAllByPerson_Uuid(personUuid);
    }

    @Override
    public List<Order> findAllByProduct(Product product) {
        return orderRepository.findAllByProduct(product);
    }

    @Override
    public List<Order> findAllByProductUuid(String productUuid) {
        return orderRepository.findAllByProduct_Uuid(productUuid);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void delete(String uuid) {
        orderRepository.deleteByUuid(uuid);
    }
}
