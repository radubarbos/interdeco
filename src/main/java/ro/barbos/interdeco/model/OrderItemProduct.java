package ro.barbos.interdeco.model;

import java.io.Serializable;

/**
 * Created by radu on 5/6/2015.
 */
public class OrderItemProduct implements Serializable {

    private Product product;
    private Double quantity;
    private Double volume;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
