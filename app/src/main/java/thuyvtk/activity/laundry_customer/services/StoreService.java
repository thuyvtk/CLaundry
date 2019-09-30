package thuyvtk.activity.laundry_customer.services;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;

public interface StoreService {
    void getAllStore(CallbackData<ArrayList<StoreDTO>> callBack);
}
