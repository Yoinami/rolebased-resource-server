package personal.yoinami.rolebasedresourceserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "attributes")
@Getter
@Setter
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeId;

    private String name;

    private Boolean nullable;

    private String type;

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", name='" + name + '\'' +
                ", nullable=" + nullable +
                ", type='" + type + '\'' +
                '}';
    }
}
