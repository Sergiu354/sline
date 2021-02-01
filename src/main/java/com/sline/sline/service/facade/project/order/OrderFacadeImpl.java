package com.sline.sline.service.facade.project.order;

import com.sline.sline.dto.order.OrderDto;
import com.sline.sline.dto.person.PersonDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.user.UserDto;
import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.order.Status;
import com.sline.sline.service.project.order.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderFacadeImpl implements OrderFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final OrderService orderService;

    @Override
    public OrderDto findByUuid(String uuid) {
        Order order = orderService.findByUuid(uuid);
        if (order==null)
            LOGGER.info("Not found Order by UUID: " + uuid);
        return convertEntityToDto(order);
    }

    @Override
    public List<OrderDto> findAll() {
        return orderService.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByStatus(Status status) {
        return orderService.findAllByStatus(status).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByDeliveryTime(Date deliveryTime) {
        return orderService.findAllByDeliveryTime(deliveryTime).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByUser(UserDto userDto) {
        return findAllByUserUuid(userDto.getUuid());
    }

    @Override
    public List<OrderDto> findAllByUserUuid(String userUuid) {
        return orderService.findAllByUserUuid(userUuid).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByPerson(PersonDto personDto) {
        return findAllByPersonUuid(personDto.getUuid());
    }

    @Override
    public List<OrderDto> findAllByPersonUuid(String personUuid) {
        return orderService.findAllByPersonUuid(personUuid).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByProduct(ProductDto productDto) {
        return findAllByProductUuid(productDto.getUuid());
    }

    @Override
    public List<OrderDto> findAllByProductUuid(String productUuid) {
        return null;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = new Order();
        convertDtoToEntity(orderDto, order);
        orderService.save(order);
        return convertEntityToDto(order);
    }

    @Override
    public OrderDto edit(OrderDto orderDto) {
        Order order = orderService.findByUuid(orderDto.getUuid());
        if (order!=null) {
            convertDtoToEntity(orderDto, order);
            orderService.save(order);
            return convertEntityToDto(order);
        } else {
            LOGGER.info("Not found Order UUID: " + orderDto.getUuid());
        }
        return null;
    }

    @Override
    public void delete(OrderDto orderDto) {
        delete(orderDto.getUuid());
    }

    @Override
    public void delete(String uuid) {
        orderService.delete(uuid);
    }

    private OrderDto convertEntityToDto(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(order, OrderDto.class);
    }

    private void convertDtoToEntity(OrderDto orderDto, Order order) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<OrderDto, Order>() {

            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUuid());
                if (orderDto.getUuid()!=null) {
                    skip(destination.getPerson());
                    skip(destination.getProduct());
                    skip(destination.getUser());
                }
            }
        });
        modelMapper.map(orderDto, order);
    }
}
