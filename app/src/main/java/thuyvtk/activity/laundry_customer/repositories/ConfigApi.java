package thuyvtk.activity.laundry_customer.repositories;

public class ConfigApi {

  public static final String BASE_URL = "https://giatdo20190924115104.azurewebsites.net/api/";

    // for localhost
   //    public static final String BASE_URL = "http://192.168.1.23:44366/api/";


    public interface Api {
     String GET_ALL_STORE = "Store/GetAll";
     String GET_CUSTOMER_BY_FIREBASE_ID = "Customer/GetByUserID";
     String UPDATE_CUSTOMER = "Customer/UpdateCustomer";
     String ORDER_HISTORY = "Order/History";
    }

}