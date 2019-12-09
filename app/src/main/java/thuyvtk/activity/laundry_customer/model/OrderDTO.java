package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

public class OrderDTO implements Serializable {
    @SerializedName("Id")
    private String orderId;
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
    @SerializedName("Address")
    private String address;
    @SerializedName("OrderServices")
    private List<OrderServiceDTO> listOrderServices;

    public OrderDTO() {
    }

    public OrderDTO(float totalPrice, String status, String customerId, Date takeTime, Date deliveryTime, String address, List<OrderServiceDTO> listOrderServices) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerId = customerId;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.listOrderServices = listOrderServices;
    }

    public OrderDTO(String orderId, float totalPrice, String status, String customerId, Date takeTime, Date deliveryTime, String address, List<OrderServiceDTO> listOrderServices) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customerId = customerId;
        this.takeTime = takeTime;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.listOrderServices = listOrderServices;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderServiceDTO> getListOrderServices() {
        return listOrderServices;
    }

    public void setListOrderServices(List<OrderServiceDTO> listOrderServices) {
        this.listOrderServices = listOrderServices;
    }
}
