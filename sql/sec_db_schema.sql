CREATE TABLE shopping_carts (
   cart_item_id INT PRIMARY KEY AUTO_INCREMENT,
   user_id VARCHAR(255) NOT NULL,
   product_id INT UNSIGNED NOT NULL,
   quantity INT NOT NULL DEFAULT 1,
   added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
   UNIQUE KEY user_product_unique (user_id, product_id)
);
