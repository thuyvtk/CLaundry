package thuyvtk.activity.laundry_customer.presenter;

import java.util.List;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_customer.services.ServiceTypeService;
import thuyvtk.activity.laundry_customer.services.serviceImpl.ServiceTypeServiceImpl;
import thuyvtk.activity.laundry_customer.view.ServiceTypeView;

public class ServiceTypePresenter {
    ServiceTypeService service;
    ServiceTypeView view;

    public ServiceTypePresenter(ServiceTypeView view) {
        this.view = view;
        service = new ServiceTypeServiceImpl();
    }

    public void loadAllService(){
        service.getAllService(new CallbackData<List<ServiceTypeDTO>>() {
            @Override
            public void onSuccess(List<ServiceTypeDTO> serviceTypeDTOS) {
                view.loadAllService(serviceTypeDTOS);
            }

            @Override
            public void onFail(String message) {
                view.loadServiceFail(message);
            }
        });
    }
}
