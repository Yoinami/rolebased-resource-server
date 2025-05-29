package personal.yoinami.rolebasedresourceserver.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String merchantId;

    private String name;

    private String category;

    @Lob
    private String detail; // Or use Map<String, Object> with converter

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    @JsonManagedReference
    private List<ProductAttribute> productSpecification;

    @Lob
    private String description;

    private long stockQuantity;

    private long price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", productId=" + productId +
                ", merchantId='" + merchantId + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", productSpecification=" + productSpecification +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
