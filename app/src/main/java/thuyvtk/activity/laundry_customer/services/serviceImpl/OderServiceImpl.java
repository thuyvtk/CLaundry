package thuyvtk.activity.laundry_customer.services.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.repositories.ClientApi;
import thuyvtk.activity.laundry_customer.services.OrderService;

public class OderServiceImpl implements OrderService {
    ClientApi clientApi = new ClientApi();

    @Override
    public void loadHistory(String userId, final CallbackData<List<OrderDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getOrderHistory(userId);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<List<OrderDTO>>() {
                                }.getType();
                                List<OrderDTO> responseResult = new Gson().fromJson(result, type);
                                if (responseResult != null) {
                                    callbackData.onSuccess(responseResult);
                                } else {
                                    callbackData.onFail("empty");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            callbackData.onFail("timeout");
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrder(OrderDTO orderDTO, final CallbackData<String> callbackData) {
        Gson gson = new Gson();
        String json = gson.toJson(orderDTO);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().createOrder(body);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null) {
                        if (response.code() == 200 || response.code() == 201) {
                            callbackData.onSuccess("success");
                        } else {
                            callbackData.onFail("timeout");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
