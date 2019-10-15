package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDetailDTO implements Serializable {
    @SerializedName("Date")
    private String dateCreate;
    @SerializedName("ListOrder")
    private List<OrderDTO> listOrder;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String dateCreate, List<OrderDTO> listOrder) {
        this.dateCreate = dateCreate;
        this.listOrder = listOrder;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<OrderDTO> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderDTO> listOrder) {
        this.listOrder = listOrder;
    }
}
