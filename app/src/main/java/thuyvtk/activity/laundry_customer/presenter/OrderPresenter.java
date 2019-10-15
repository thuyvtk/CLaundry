package thuyvtk.activity.laundry_customer.presenter;

import java.util.List;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;
import thuyvtk.activity.laundry_customer.services.OrderService;
import thuyvtk.activity.laundry_customer.services.serviceImpl.OderServiceImpl;
import thuyvtk.activity.laundry_customer.view.OrderView;

public class OrderPresenter {
    OrderService orderService;
    OrderView view;

    public OrderPresenter(OrderView view) {
        this.view = view;
        orderService = new OderServiceImpl();
    }

    public void getOrderHistory(String userId) {
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

    public void createOrder(OrderDTO dto) {
        orderService.createOrder(dto, new CallbackData<String>() {
            @Override
            public void onSuccess(String s) {
                view.createOrderSuccess();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void getOrderByDateAndStatus(String customerId, String dateStart, String dateEnd, String status) {
        orderService.getOrderByDateAndStatus(customerId, dateStart, dateEnd, status, new CallbackData<List<OrderDetailDTO>>() {
            @Override
            public void onSuccess(List<OrderDetailDTO> orderDetailDTOS) {
                view.returnListOrder(orderDetailDTOS);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
