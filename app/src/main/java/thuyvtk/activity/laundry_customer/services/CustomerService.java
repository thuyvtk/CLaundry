package thuyvtk.activity.laundry_customer.services;

import java.sql.Timestamp;
import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;

public interface CustomerService {

    void getCustomerById(CallbackData<CustomerDTO> callbackData);
    void updateCustomer(String customerId, String customerName, String email, String phone, int rate, Timestamp createDate, CallbackData<CustomerDTO> callbackData);

}
