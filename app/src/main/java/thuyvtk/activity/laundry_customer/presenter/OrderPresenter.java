package thuyvtk.activity.laundry_customer.presenter;

import android.content.Context;
import android.widget.Toast;

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
    Context context;

    public OrderPresenter(OrderView view) {
        this.view = view;
        orderService = new OderServiceImpl();
    }

    public OrderPresenter(Context context) {
        orderService = new OderServiceImpl();
        this.context = context;
    }
    public void getOrderHistory(String userId, String dateStart, String dateEnd) {
        orderService.loadHistory(userId, dateStart, dateEnd, new CallbackData<List<OrderDetailDTO>>() {
            @Override
            public void onSuccess(List<OrderDetailDTO> orderDTOS) {
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
                view.onFail(message);
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

    public void ratingStore(String storeId, float rateNumber){
        orderService.rateStore(storeId, rateNumber, new CallbackData<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(context, "Rating success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
