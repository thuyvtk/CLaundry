package thuyvtk.activity.laundry_customer.presenter;

import java.sql.Date;
import java.sql.Timestamp;

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

    public void getCustomerById (){
        customerService.getCustomerById(new CallbackData<CustomerDTO>() {
            @Override
            public void onSuccess(CustomerDTO customerDTO) {
                customerView.returnCustomer(customerDTO);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void updateCustomer(String customerId, String customerName) {
        customerService.updateCustomer(customerId, customerName,"thien@fpt.edu","0965448599",1,new Timestamp(System.currentTimeMillis()), new CallbackData<CustomerDTO>() {
            @Override
            public void onSuccess(CustomerDTO customerDTO) {
                System.out.println("Update success");
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
