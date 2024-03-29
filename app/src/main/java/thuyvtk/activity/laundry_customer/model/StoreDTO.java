package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StoreDTO implements Serializable {
    @SerializedName("Id")
    private String store_id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("AccountId")
    private String accountId;
    @SerializedName("rate")
    private int rate;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Imgurl")
    private String Image;

    @SerializedName("ServiceTypes")
    private List<ServiceTypeDTO> listServiceType;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public List<ServiceTypeDTO> getListServiceType() {
        return listServiceType;
    }

    public void setListServiceType(List<ServiceTypeDTO> listServiceType) {
        this.listServiceType = listServiceType;
    }

    public StoreDTO(String store_id, String name, String email, String accountId, int rate, String address, String image, List<ServiceTypeDTO> listServiceType) {
        this.store_id = store_id;
        this.name = name;
        this.email = email;
        this.accountId = accountId;
        this.rate = rate;
        Address = address;
        Image = image;
        this.listServiceType = listServiceType;
    }

    public StoreDTO(String store_id, String name, String email, String accountId, int rate, String address, String image) {
        this.store_id = store_id;
        this.name = name;
        this.email = email;
        this.accountId = accountId;
        this.rate = rate;
        Address = address;
        Image = image;
    }

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
