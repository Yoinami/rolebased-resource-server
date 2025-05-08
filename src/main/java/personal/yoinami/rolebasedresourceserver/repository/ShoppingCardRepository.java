package personal.yoinami.rolebasedresourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;

import java.util.List;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Integer> {
    List<ShoppingCard> findShoppingCardsByUserId(String userId);

//    @Modifying
//    @Query("update ShoppingCard sc set sc.quantity = ?1 where sc.cardItemId = ?2")
//    void setShoppingCardByCardItemId(int quantity, int cardItemId);
//    void updateShoppingCardByCardItemId(Integer cardItemId);
}
