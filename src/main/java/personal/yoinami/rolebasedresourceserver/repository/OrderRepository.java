package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.yoinami.rolebasedresourceserver.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
