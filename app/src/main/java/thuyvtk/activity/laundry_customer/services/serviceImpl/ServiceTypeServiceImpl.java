package thuyvtk.activity.laundry_customer.services.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.repositories.ClientApi;
import thuyvtk.activity.laundry_customer.services.ServiceTypeService;

public class ServiceTypeServiceImpl implements ServiceTypeService {

    ClientApi clientApi = new ClientApi();

    @Override
    public void getAllService(final CallbackData<List<ServiceTypeDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getAllService();
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<List<ServiceTypeDTO>>() {
                                }.getType();
                                ArrayList<ServiceTypeDTO> responseResult = new Gson().fromJson(result, type);
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

        }
    }
}
