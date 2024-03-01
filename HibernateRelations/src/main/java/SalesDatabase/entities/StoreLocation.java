package SalesDatabase.entities;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

public class StoreLocation extends BaseEntity{

    @Column
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
