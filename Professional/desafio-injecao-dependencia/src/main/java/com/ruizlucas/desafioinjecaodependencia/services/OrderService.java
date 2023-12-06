package com.ruizlucas.desafioinjecaodependencia.services;


import com.ruizlucas.desafioinjecaodependencia.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ShippingService shippingService;

    public Double total(Order order) {
       return  order.getBasic() - calcDiscountValue(order) + shippingService.shipment(order);
    }
    private Double calcDiscountValue(Order order) {
        return order.getBasic() * (order.getDiscount()/100);
    }

}
