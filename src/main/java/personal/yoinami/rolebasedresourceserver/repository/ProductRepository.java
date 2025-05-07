package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.yoinami.rolebasedresourceserver.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { }
