package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.dto.OrderDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.Order;
import br.eng.jonathan.geriluh_api.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final String ORDER_SEARCH_ERROR = "ORDER.SEARCH_ERROR";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OrderRepository repository;

    public Page<Order> listAllOrders(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Order findOrderById(Long orderId) throws NotFoundException {
        return repository.findById(orderId).orElseThrow(() -> new NotFoundException(getErrorMessage()));
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(getErrorMessage()));

        BeanUtils.copyProperties(orderDTO, order, "orderId", "cashRegisterId", "userId");

        return repository.save(order);
    }

    public void deleteOrder(Long orderId) {
        if (!repository.existsById(orderId)) {
            throw new NotFoundException(getErrorMessage());
        }
        repository.deleteById(orderId);
    }

    private String getErrorMessage() {
        return messageSource.getMessage(ORDER_SEARCH_ERROR, null, LocaleContextHolder.getLocale());
    }
}
