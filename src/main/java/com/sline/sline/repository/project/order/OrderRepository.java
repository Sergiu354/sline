package com.sline.sline.repository.project.order;

import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.order.Status;
import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUuid(String uuid);
    List<Order> findAllByStatus(Status status);
    @Query(value = "select * from Orders o where o.deliveryTime >= :deliveryTime", nativeQuery = true)
    List<Order> findAllByDeliveryTimeBefore(Date deliveryTime);
    List<Order> findAllByUser(User user);
    List<Order> findAllByUser_Uuid(String user_uuid);
    List<Order> findAllByPerson(Person person);
    List<Order> findAllByPerson_Uuid(String person_uuid);
    List<Order> findAllByProduct(Product product);
    List<Order> findAllByProduct_Uuid(String product_uuid);
    void deleteByUuid(String uuid);
}
