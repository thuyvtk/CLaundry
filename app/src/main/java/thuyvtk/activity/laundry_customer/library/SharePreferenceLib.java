package thuyvtk.activity.laundry_customer.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import thuyvtk.activity.laundry_customer.model.CustomerDTO;

import static android.content.Context.MODE_PRIVATE;

public class SharePreferenceLib {
    final String SHARE_NAME = "SHARED";
    final String JSON_NAME = "USER";
    final String CART_NAME = "CART";
    SharedPreferences sharedPreferences;
    Context context;

    public SharePreferenceLib(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        this.context = context;
    }

    public void saveUser(CustomerDTO user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(JSON_NAME, json);
        editor.commit();
    }

    public CustomerDTO getUser() {
        String json = sharedPreferences.getString(JSON_NAME, "");
        Type type = new TypeToken<CustomerDTO>() {
        }.getType();
        CustomerDTO dto = new Gson().fromJson(json, type);
        return dto;
    }

    public void logouṭ() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(JSON_NAME);
        editor.commit();
    }

    public CartDTO getShoppingCart() {
        String json = sharedPreferences.getString(CART_NAME, "");
        if(json == null || json.equals("")){
            // if there is no shopping cart create one.
            CartDTO dto = new CartDTO(getUser());
            saveShoppingCart(dto);
            return dto;
        }
        else{
            Type type = new TypeToken<CartDTO>() {
            }.getType();
            CartDTO dto = new Gson().fromJson(json, type);
            return dto;
        }
    }

    public void saveShoppingCart(CartDTO dto) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        editor.putString(CART_NAME, json);
        editor.commit();
    }
}
