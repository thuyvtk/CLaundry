package thuyvtk.activity.laundry_customer.repositories;

public class ConfigApi {

    public static final String BASE_URL = "https://giatdo20191009105153.azurewebsites.net/api/";


    public interface Api {
        //store
        String GET_ALL_STORE = "Store/GetAll";
        String GET_RECENT_STORE = "Store/StoreUserHasUse";
        String GET_TOP_STORE =  "Store/GetStoreByRate";
        String GET_NEARBY_STORE = "Store/GetNearbyStores";
        String SEARCH_STORE_BY_NAME =  "Store/SearchStoreByName";
        //customer
        String GET_CUSTOMER_BY_FIREBASE_ID = "Customer/GetByUserID";
        String UPDATE_CUSTOMER = "Customer/UpdateCustomer";
        //order
        String ORDER_HISTORY = "Order/History";
        //service
        String GET_ALL_SERVICE = "ServiceType/GetAll";
    }

}