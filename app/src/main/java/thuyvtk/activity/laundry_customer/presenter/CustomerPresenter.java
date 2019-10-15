package thuyvtk.activity.laundry_customer.presenter;

import android.content.Context;
import android.widget.Toast;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.services.CustomerService;
import thuyvtk.activity.laundry_customer.services.serviceImpl.CustomerServiceImpl;
import thuyvtk.activity.laundry_customer.view.CustomerView;

public class CustomerPresenter {
    CustomerService customerService;
    CustomerView customerView;

    public CustomerPresenter(CustomerView customerView) {
        customerService = new CustomerServiceImpl();
        this.customerView = customerView;
    }

    public void getCustomerByFirebaseId (String userId){
        customerService.getCustomerByFirebaseId(userId, new CallbackData<CustomerDTO>() {
            @Override
            public void onSuccess(CustomerDTO customerDTO) {
                customerView.returnCustomer(customerDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void updateCustomer(CustomerDTO customerDTO, final Context context) {
        customerService.updateCustomer(customerDTO, new CallbackData<CustomerDTO>() {
            @Override
            public void onSuccess(CustomerDTO customerDTO) {
                customerView.returnCustomer(customerDTO);
                Toast.makeText(context, "Update your profile successful!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(context, "Update your profile failed!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
