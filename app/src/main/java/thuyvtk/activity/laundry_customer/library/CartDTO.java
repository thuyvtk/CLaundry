package thuyvtk.activity.laundry_customer.library;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;

import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;

public class CartDTO implements Serializable {
    private CustomerDTO customer;
    private HashMap<String, ServiceDTO> listStore = new HashMap<>();

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public HashMap<String, ServiceDTO> getListStore() {
        return listStore;
    }

    public void setListStore(HashMap<String, ServiceDTO> listStore) {
        this.listStore = listStore;
    }


    public CartDTO(CustomerDTO customer) {
        this.customer = customer;
    }

    public void addStore(ServiceDTO serviceDTO){
        if(listStore.containsKey(serviceDTO.getId())){
            ServiceDTO dto = listStore.get(serviceDTO.getId());
            int quantity = dto.getQuantity();
            dto.setQuantity(quantity++);
        }else{
            listStore.put(serviceDTO.getId(),serviceDTO);
        }
    }
    public void removeStore(ServiceDTO serviceDTO){
        if(listStore.containsKey(serviceDTO.getId())){
           listStore.remove(serviceDTO.getId());
        }else{

        }
    }
    public double getTotalPrice(){
        double total = 0 ;
        for (ServiceDTO dto: listStore.values()) {
            double price = dto.getQuantity() * dto.getPrice();
            total += price;
        }
        return total;
    }

}
