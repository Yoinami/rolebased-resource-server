package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;

@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeId;

    private String name;

    private Boolean nullable;

    private String type;

    // Getters, setters, etc.
}
