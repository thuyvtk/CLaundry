package thuyvtk.activity.laundry_customer.view;

import java.util.List;

import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;

public interface OrderView {
    void loadOrderHistory(List<OrderDTO> orderList);
    void onFail(String msg);
    void createOrderSuccess();
    void returnListOrder(List<OrderDetailDTO> listOrderDetail);
}
