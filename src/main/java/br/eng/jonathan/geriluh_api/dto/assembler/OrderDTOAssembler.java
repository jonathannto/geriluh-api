package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.OrderController;
import br.eng.jonathan.geriluh_api.dto.OrderDTO;
import br.eng.jonathan.geriluh_api.model.Order;

import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderService orderService;

    public OrderDTO mapToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public Order mapToEntity(OrderDTO orderDTO) throws NotFoundException {
        Order order = modelMapper.map(orderDTO, Order.class);

        if (orderDTO.getOrderId() != null) {
            Order previousOrder = orderService.findOrderById(orderDTO.getOrderId());
            order.setOrderId(previousOrder.getOrderId());
            order.setStatus(orderDTO.getStatus() != null ? orderDTO.getStatus() : previousOrder.getStatus());
            order.setCreationDate(orderDTO.getCreationDate() != null ? orderDTO.getCreationDate() : previousOrder.getCreationDate());
            order.setEndDate(orderDTO.getEndDate() != null ? orderDTO.getEndDate() : previousOrder.getEndDate());
            order.setTotalPrice(orderDTO.getTotalPrice() != null ? orderDTO.getTotalPrice() : previousOrder.getTotalPrice());
            order.setPaymentStatus(orderDTO.getPaymentStatus() != null ? orderDTO.getPaymentStatus() : previousOrder.getPaymentStatus());
            order.setNotes(orderDTO.getNotes() != null ? orderDTO.getNotes() : previousOrder.getNotes());
            order.setTableNumber(orderDTO.getTableNumber() != null ? orderDTO.getTableNumber() : previousOrder.getTableNumber());
            order.setCashRegisterId(previousOrder.getCashRegisterId());
            order.setUserId(previousOrder.getUserId());
        }
        return order;
    }

    /**
     * Uses {@link ModelMapper} to convert an entity into a DTO
     * while also transforming the class into an {@link EntityModel} and adding links for HATEOAS
     * @param order {@link br.eng.jonathan.geriluh_api.model.Order}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.OrderDTO}>
     */
    public EntityModel<OrderDTO> mapToEntityModelDTO(Order order) {
        return EntityModel.of(mapToDTO(order),
                linkTo(methodOn(OrderController.class)
                        .getOrderById(order.getOrderId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(OrderController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(OrderController.class)
                        .createNewOrder(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(OrderController.class)
                        .updateOrder(order.getOrderId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(OrderController.class)
                        .deleteOrder(order.getOrderId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }
}