package personal.yoinami.rolebasedresourceserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;
import personal.yoinami.rolebasedresourceserver.repository.ShoppingCardRepository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ShoppingCardService {

    @Autowired
    ShoppingCardRepository shoppingCardRepository;

    public List<ShoppingCard> getShoppingCard(String user_id) {
        return shoppingCardRepository.findShoppingCardsByUserId(user_id);
    }

    public ShoppingCard addProductToShoppingCard(int product_id, int quantity) throws SQLIntegrityConstraintViolationException {

        ShoppingCard sc = new ShoppingCard();
        sc.setProductId(product_id);
        sc.setQuantity(quantity);
        OAuth2User oAuth2User= (OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sc.setUserId(oAuth2User.getName());
        try {
            return shoppingCardRepository.save(sc);
        } catch (Exception exception) {
            throw new SQLIntegrityConstraintViolationException("Already In the shopping card");
        }
    }

    public ShoppingCard addProductToShoppingCard(int product_id)  throws SQLIntegrityConstraintViolationException {
        return this.addProductToShoppingCard(product_id, 1);
    }
}
