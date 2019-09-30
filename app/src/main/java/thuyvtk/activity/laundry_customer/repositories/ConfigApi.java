package thuyvtk.activity.laundry_customer.repositories;

public class ConfigApi {

  public static final String BASE_URL = "https://giatdo20190924115104.azurewebsites.net/api/";

    // for localhost
   //    public static final String BASE_URL = "http://192.168.1.23:44366/api/";


    public interface Api {
     String GET_ALL_STORE = "Store/GetAll";
     String GET_CUSTOMER_BY_ID = "Customer/GetById?Id=d8961124-f5dd-4124-8453-08d74555b235";
     String UPDATE_CUSTOMER = "Customer/UpdateCustomer";
    }

}