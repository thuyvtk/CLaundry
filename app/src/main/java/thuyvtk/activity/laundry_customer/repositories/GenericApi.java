package thuyvtk.activity.laundry_customer.repositories;

import java.sql.Timestamp;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenericApi {

    @GET(ConfigApi.Api.GET_ALL_STORE)
    Call<ResponseBody> getAllStore();

    @GET(ConfigApi.Api.GET_CUSTOMER_BY_ID)
    Call<ResponseBody> getCustomerById();

    @PUT(ConfigApi.Api.UPDATE_CUSTOMER)
    Call<ResponseBody> updateCustomer(@Query("id") String customerId, @Query("name") String customerName,
                                      @Query("email") String email, @Query("phone") String phone, @Query("rate") int rate,
                                      @Query("dateCreate")Timestamp createDate);
}
