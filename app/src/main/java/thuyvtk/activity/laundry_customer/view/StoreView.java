package thuyvtk.activity.laundry_customer.view;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.model.StoreDTO;

public interface StoreView {
    void returnAllStore(ArrayList<StoreDTO> listStore);
    void loadStoreFail(String message);
    void searchStoreByName(ArrayList<StoreDTO> listStore);
    void searchStoreByNameFail(String message);
    void returnStoreById(StoreDTO storeDTO);
}
