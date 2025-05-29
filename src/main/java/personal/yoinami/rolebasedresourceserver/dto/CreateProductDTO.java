package personal.yoinami.rolebasedresourceserver.dto;

import lombok.Data;
import personal.yoinami.rolebasedresourceserver.model.ProductAttribute;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductDTO {
    private String name;
    private String category;
    private List<ProductAttribute> specificationList;
    private String detail;
    private BigDecimal price;
    private int stock;
}
