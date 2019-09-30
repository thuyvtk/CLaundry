package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreDTO implements Serializable {
    @SerializedName("Id")
    private String store_id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("AccountId")
    private String accountId;
//    @SerializedName("DateCreate")
    @SerializedName("rate")
    private int rate;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
