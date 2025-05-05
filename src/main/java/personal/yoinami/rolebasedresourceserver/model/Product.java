package personal.yoinami.rolebasedresourceserver.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    @Column(columnDefinition = "json")
    private String detail; // Or use Map<String, Object> with converter

    @Lob
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Getters, setters, etc.
}
