package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    @SerializedName("Id")
    private String customerId;
    @SerializedName("Name")
    private String customerName;
    @SerializedName("Email")
    private String email;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Rate")
    private int rate;
    @SerializedName ("ImgURL")
    private String ImgUrl;
    @SerializedName("DateCreate")
    private String createDate;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String customerName, String email, String phone, int rate, String imgUrl, String createDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.rate = rate;
        ImgUrl = imgUrl;
        this.createDate = createDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
