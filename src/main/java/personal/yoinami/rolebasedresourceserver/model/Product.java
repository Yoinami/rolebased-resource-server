package personal.yoinami.rolebasedresourceserver.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String merchant_id;

    private String name;

    private String category;

    @Lob
    private String detail; // Or use Map<String, Object> with converter

    @Transient
    private List<ProductAttribute> productSpecification;

    @Lob
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductAttribute> getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(List<ProductAttribute> productSpecification) {
        this.productSpecification = productSpecification;
    }

    @Override
    public String toString() {
        return this.name + " " + this.category;
    }
}
