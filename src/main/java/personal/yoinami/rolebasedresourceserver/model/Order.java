package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String user_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime datetime;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Getters, setters, etc.
}
