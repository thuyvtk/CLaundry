package thuyvtk.activity.laundry_customer.services;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;

public interface StoreService {
    void getAllStore(CallbackData<ArrayList<StoreDTO>> callBack);
    void getRecentStore(String serviceId, String customerId,CallbackData<ArrayList<StoreDTO>> callBackData);
    void getTopStore(String serviceId,CallbackData<ArrayList<StoreDTO>> callBackData);
    void getNearbyStore(String serviceId, double latitude, double longitude,CallbackData<ArrayList<StoreDTO>> callBackData);
}
