package personal.yoinami.rolebasedresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.yoinami.rolebasedresourceserver.model.Order;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;
import personal.yoinami.rolebasedresourceserver.repository.OrderRepository;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;
import personal.yoinami.rolebasedresourceserver.repository.ShoppingCardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    ShoppingCardRepository shoppingCardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public boolean orderNow(String user_id) {

        Optional<List<ShoppingCard>> optionalShoppingCardList = shoppingCardRepository.findShoppingCardsByUserId(user_id);
        if(optionalShoppingCardList.isPresent()) {
            List<ShoppingCard> shoppingCardList = optionalShoppingCardList.get();

            for(ShoppingCard sc: shoppingCardList) {
                Product product = productRepository.findById(sc.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found: " + sc.getProductId()));

                Order order = new Order();

                order.setProduct(product);
                order.setUser_id(sc.getUserId());
                order.setStatus("Pending");
                order.setDatetime(LocalDateTime.now());

                orderRepository.save(order);

                shoppingCardRepository.deleteAllByUserId(sc.getUserId());
            }
        }  else {
            return false;
        }

        return true;
    }

}
