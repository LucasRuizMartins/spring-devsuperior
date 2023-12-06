package com.ruizlucas.desafioinjecaodependencia.services;

import com.ruizlucas.desafioinjecaodependencia.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
        public Double shipment(Order order) {
            return (order.getBasic() < 100.0) ? 20.0 : (order.getBasic() > 200) ? 0.0 : 12.0;
        };
}
