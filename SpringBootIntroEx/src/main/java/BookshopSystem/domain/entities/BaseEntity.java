package BookshopSystem.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long id;

    public BaseEntity() {
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        BaseEntity.id = id;
    }
}
