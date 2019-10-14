package thuyvtk.activity.laundry_customer.services;

import java.util.List;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.OrderDTO;

public interface OrderService {
    void loadHistory(String userId, CallbackData<List<OrderDTO>> callbackData);
    void createOrder(OrderDTO orderDTO, CallbackData<String> callbackData);
}
