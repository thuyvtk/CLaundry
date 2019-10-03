package thuyvtk.activity.laundry_customer.presenter;

import java.util.List;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.services.OrderService;
import thuyvtk.activity.laundry_customer.services.serviceImpl.OderServiceImpl;
import thuyvtk.activity.laundry_customer.view.OrderHistoryView;

public class OrderPresenter {
    OrderService orderService;
    OrderHistoryView view;

    public OrderPresenter(OrderHistoryView view) {
        this.view = view;
        orderService =  new OderServiceImpl();
    }

    public void getOrderHistory(String userId){
        orderService.loadHistory(userId, new CallbackData<List<OrderDTO>>() {
            @Override
            public void onSuccess(List<OrderDTO> orderDTOS) {
                view.loadOrderHistory(orderDTOS);
            }

            @Override
            public void onFail(String message) {
                view.onFail(message);
            }
        });
    }
}
