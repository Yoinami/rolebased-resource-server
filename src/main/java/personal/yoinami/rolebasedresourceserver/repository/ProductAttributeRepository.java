package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.yoinami.rolebasedresourceserver.model.Attribute;
import personal.yoinami.rolebasedresourceserver.model.ProductAttribute;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
