package com.sline.sline.service.facade.project.order;

import com.sline.sline.dto.order.OrderDto;
import com.sline.sline.dto.person.PersonDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.user.UserDto;
import com.sline.sline.entity.project.order.Status;

import java.util.Date;
import java.util.List;

public interface OrderFacade {
    OrderDto findByUuid(String uuid);
    List<OrderDto> findAll();
    List<OrderDto> findAllByStatus(Status status);
    List<OrderDto> findAllByDeliveryTime(Date deliveryTime);
    List<OrderDto> findAllByUser(UserDto userDto);
    List<OrderDto> findAllByUserUuid(String userUuid);
    List<OrderDto> findAllByPerson(PersonDto personDto);
    List<OrderDto> findAllByPersonUuid(String personUuid);
    List<OrderDto> findAllByProduct(ProductDto productDto);
    List<OrderDto> findAllByProductUuid(String productUuid);
    OrderDto save(OrderDto orderDto);
    OrderDto edit(OrderDto orderDto);
    void delete(OrderDto orderDto);
    void delete(String uuid);
}
