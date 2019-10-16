package thuyvtk.activity.laundry_customer.services;

import java.util.List;
import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;

public interface OrderService {
    void loadHistory(String userId, String dateStart, String dateEnd, CallbackData<List<OrderDetailDTO>> callbackData);
    void createOrder(OrderDTO orderDTO, CallbackData<String> callbackData);
    void getOrderByDateAndStatus(String customerId,String dateStart,String dateEnd, String status, CallbackData<List<OrderDetailDTO>> callbackData);
    void rateStore(String storeId, float rateNumber, CallbackData<String> callbackData);
}
