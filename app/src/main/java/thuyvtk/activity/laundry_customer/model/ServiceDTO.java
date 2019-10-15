package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceDTO implements Serializable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Imgurl")
    private String image="";
    @SerializedName("Description")
    private String description;
    @SerializedName("Price")
    private double price;
    private int quantity = 1;
    @SerializedName("Store")
    private StoreBS store;

    public ServiceDTO() {
    }

    public ServiceDTO(String id, String image, String description, double price, int quantity, StoreBS store) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StoreBS getStore() {
        return store;
    }

    public void setStore(StoreBS store) {
        this.store = store;
    }
}
