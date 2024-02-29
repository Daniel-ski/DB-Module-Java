package SalesDatabase.entities;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class Sale extends BaseEntity{

    // bi-directional relation
    @ManyToOne
    private Product product;

    @OneToMany
    private List<Product> test;

    // bi-directional relation
    @ManyToOne
    private Customer customer;

    // bi-directional relation
    @ManyToOne
    private StoreLocation storeLocation;

    @Column
    private Date date;

    public Sale() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getTest() {
        return test;
    }

    public void setTest(List<Product> test) {
        this.test = test;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
