package thuyvtk.activity.laundry_customer.services.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuyvtk.activity.laundry_customer.callbacks.CallbackData;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.repositories.ClientApi;
import thuyvtk.activity.laundry_customer.services.StoreService;

public class StoreServiceImpl implements StoreService {
    ClientApi clientApi = new ClientApi();
    final double DISTANCE  = 10;

    @Override
    public void getAllStore(final CallbackData<ArrayList<StoreDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getAllStore();
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null ) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ArrayList<StoreDTO>>() {
                                }.getType();
                                ArrayList<StoreDTO> responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void getRecentStore(String serviceId, String customerId, final CallbackData<ArrayList<StoreDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getRecentStore(serviceId,customerId);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null ) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ArrayList<StoreDTO>>() {
                                }.getType();
                                ArrayList<StoreDTO> responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void getTopStore(String serviceId, final CallbackData<ArrayList<StoreDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getTopStore(serviceId);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null ) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ArrayList<StoreDTO>>() {
                                }.getType();
                                ArrayList<StoreDTO> responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void getNearbyStore(String serviceId, double latitude, double longitude, final CallbackData<ArrayList<StoreDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getNearbyStore(serviceId,latitude,longitude,DISTANCE);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ArrayList<StoreDTO>>() {
                                }.getType();
                                ArrayList<StoreDTO> responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void searchStoreByName(String storeName, final CallbackData<ArrayList<StoreDTO>> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().searchStoreByName(storeName);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<ArrayList<StoreDTO>>() {
                                }.getType();
                                ArrayList<StoreDTO> responseResult = new Gson().fromJson(result, type);
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

    @Override
    public void getStoreById(String id, final CallbackData<StoreDTO> callbackData) {
        Call<ResponseBody> serviceCall = clientApi.getGenericApi().getStoreById(id);
        try {
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
                        if (response.code() == 200) {
                            try {
                                String result = response.body().string();
                                Type type = new TypeToken<StoreDTO>() {
                                }.getType();
                                StoreDTO responseResult = new Gson().fromJson(result, type);
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
