package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "attributes")
@Data
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeId;

    private String name;

    private Boolean nullable;

    private String type;
}
