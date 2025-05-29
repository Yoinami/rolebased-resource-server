package personal.yoinami.rolebasedresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.model.Attribute;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.model.ProductAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAttributeService {

    @Autowired AttributeService attributeService;

    public List<ProductAttribute> regulatedProductAttribute(List<ProductAttribute> productAttributeList, Product product) {
        List<ProductAttribute> populatedProductAttribute = new ArrayList<>();
        for(ProductAttribute p: productAttributeList) {
            try {
                Attribute attribute = attributeService.saveOrElse(p.getAttribute());
                p.setAttribute(attribute);
                p.setProduct(product);
                populatedProductAttribute.add(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return populatedProductAttribute;
    }
}
