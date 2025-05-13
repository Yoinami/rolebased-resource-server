package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;

import java.util.List;
import java.util.Optional;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Integer> {
    Optional<List<ShoppingCard>> findShoppingCardsByUserId(String userId);

    void deleteAllByUserId(String userId);
}
