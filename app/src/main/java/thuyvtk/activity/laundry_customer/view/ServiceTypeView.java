package thuyvtk.activity.laundry_customer.view;

import java.util.List;

import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;

public interface ServiceTypeView {
    void loadAllService(List<ServiceTypeDTO> listService);
    void loadServiceFail(String message);
}
