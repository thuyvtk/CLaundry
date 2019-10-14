package thuyvtk.activity.laundry_customer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceTypeDTO implements Serializable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("listService")
    private ArrayList<ServiceDTO> listService;

    public ArrayList<ServiceDTO> getListService() {
        return listService;
    }

    public void setListService(ArrayList<ServiceDTO> listService) {
        this.listService = listService;
    }

    public ServiceTypeDTO(String id, String name, ArrayList<ServiceDTO> listService) {
        this.id = id;
        this.name = name;
        this.listService = listService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceTypeDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
