CREATE TABLE `user`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
);
CREATE TABLE `orders`(
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `product_id` INT UNSIGNED NOT NULL,
    `datetime` DATETIME NOT NULL,
    `status` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
);
CREATE TABLE `merchant`(
    `merchant_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `location` VARCHAR(255) NOT NULL
);
CREATE TABLE `products`(
    `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `merchant_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `category` VARCHAR(255) NOT NULL,
    `detail` JSON NOT NULL,
    `description` TEXT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
);
CREATE TABLE `attributes`(
    `attribute_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `nullable` BOOLEAN NOT NULL,
    `type` VARCHAR(255) NOT NULL
);
CREATE TABLE `product_attributes`(
    `product_attribute_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `attribute_id` INT UNSIGNED NOT NULL,
    `product_id` INT UNSIGNED NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`product_attribute_id`)
);
ALTER TABLE
    `orders` ADD CONSTRAINT `orders_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `products`(`product_id`);
ALTER TABLE
    `products` ADD CONSTRAINT `products_merchant_id_foreign` FOREIGN KEY(`merchant_id`) REFERENCES `merchant`(`merchant_id`);
ALTER TABLE
    `product_attributes` ADD CONSTRAINT `product_attributes_product_id_foreign` FOREIGN KEY(`product_id`) REFERENCES `products`(`product_id`);
ALTER TABLE
    `orders` ADD CONSTRAINT `orders_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `product_attributes` ADD CONSTRAINT `product_attributes_attribute_id_foreign` FOREIGN KEY(`attribute_id`) REFERENCES `attributes`(`attribute_id`);