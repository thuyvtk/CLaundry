package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    @SerializedName("TotalPrice")
    private float totalPrice;
    @SerializedName("Status")
    private String status;
    @SerializedName("CustomerId")
    private String customerId;
    @SerializedName("TakeTime")
    private Date takeTime;
    @SerializedName("DeliveryTime")
    private Date deliveryTime;
    @SerializedName("ListOrderServices")
    private List<OrderServiceDTO> listOrderServices;

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public List<OrderServiceDTO> getListOrderServices() {
        return listOrderServices;
    }

    public void setListOrderServices(List<OrderServiceDTO> listOrderServices) {
        this.listOrderServices = listOrderServices;
    }

    public OrderDTO(float totalPrice, String status, String customerId, Date takeTime, Date deliveryTime, List<OrderServiceDTO> listOrderServices) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerId = customerId;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.listOrderServices = listOrderServices;
    }

    public OrderDTO(float totalPrice, String status, String customerId, Date takeTime, Date deliveryTime) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerId = customerId;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
    }
}
