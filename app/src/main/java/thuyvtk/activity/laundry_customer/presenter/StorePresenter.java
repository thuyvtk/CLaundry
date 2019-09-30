package thuyvtk.activity.laundry_customer.presenter;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.services.StoreService;
import thuyvtk.activity.laundry_customer.services.serviceImpl.StoreServiceImpl;
import thuyvtk.activity.laundry_customer.view.StoreView;

public class StorePresenter {
    StoreService storeService;
    StoreView storeView;
    public StorePresenter(StoreView storeView) {
        storeService = new StoreServiceImpl();
        this.storeView = storeView;
    }

    public void getAllStore(){
        storeService.getAllStore(new CallbackData<ArrayList<StoreDTO>>() {
            @Override
            public void onSuccess(ArrayList<StoreDTO> storeDTOS) {
                storeView.returnAllStore(storeDTOS);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
