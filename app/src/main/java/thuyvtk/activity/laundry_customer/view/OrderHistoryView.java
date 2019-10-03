package thuyvtk.activity.laundry_customer.view;

import java.util.List;

import thuyvtk.activity.laundry_customer.model.OrderDTO;

public interface OrderHistoryView {
    void loadOrderHistory(List<OrderDTO> orderList);
    void onFail(String msg);
}
