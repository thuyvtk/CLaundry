package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderServiceDTO implements Serializable {
    @SerializedName("Quantity")
    public int quantity;
    @SerializedName("Price")
    public float price;
    @SerializedName("ServiceId")
    public String serviceId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public OrderServiceDTO(int quantity, float price, String serviceId) {
        this.quantity = quantity;
        this.price = price;
        this.serviceId = serviceId;
    }
}
