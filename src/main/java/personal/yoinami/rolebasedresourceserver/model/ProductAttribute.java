package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productAttributeId;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String value;

    // Getters, setters, etc.
}
