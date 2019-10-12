package thuyvtk.activity.laundry_customer.model;

import java.io.Serializable;

public class ServiceDTO implements Serializable {
    private String id;
    private String image;
    private String name;
    private String description;
    private double price;
    private int quantity = 1;



    public ServiceDTO(String id, String image, String name, String description, double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ServiceDTO(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
