package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "shopping_carts")
@Getter
@Setter
public class ShoppingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    String userId;

    Integer productId;

    Integer quantity;

    @CreationTimestamp
    Timestamp addedAt;

    @UpdateTimestamp
    Timestamp updatedAt;

}
