package thuyvtk.activity.laundry_customer.services;

import java.util.List;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;

public interface ServiceTypeService {
    void getAllService(CallbackData<List<ServiceTypeDTO>> callbackData);
}
