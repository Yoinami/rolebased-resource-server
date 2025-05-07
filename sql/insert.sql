INSERT INTO attributes (name, nullable, type) VALUES
('Color', 0, 'string'),
('Size', 0, 'string'),
('Material', 1, 'string'),
('Weight', 1, 'float'),
('Brand', 0, 'string'),
('Origin', 0, 'string'),
('Warranty', 1, 'string'),
('Capacity (ml)', 1, 'int'),
('Microwave Safe', 1, 'boolean');



INSERT INTO products (merchant_id, name, category, detail, description, created_at, updated_at) VALUES
(1, 'T-Shirt', 'Clothing', JSON_OBJECT('brand', 'CottonCo', 'origin', 'USA'), 'A comfortable cotton t-shirt.', NOW(), NOW()),
(1, 'Laptop', 'Electronics', JSON_OBJECT('brand', 'TechX', 'warranty', '2 years'), 'High-performance laptop for professionals.', NOW(), NOW()),
(2, 'Coffee Mug', 'Kitchenware', JSON_OBJECT('capacity_ml', 350, 'microwave_safe', true), 'Ceramic mug with custom print.', NOW(), NOW());


INSERT INTO product_attributes (attribute_id, product_id, value) VALUES
(1, 1, 'Red'),
(2, 1, 'M'),
(3, 1, 'Cotton'),
(1, 2, 'Silver'),
(4, 2, '2.5'),
(1, 3, 'White'),
(2, 3, 'Standard'),
(3, 3, 'Ceramic'),
(5, 1, 'CottonCo'),
(6, 1, 'USA'),
(5, 2, 'TechX'),
(7, 2, '2 years'),
(8, 3, '350'),
(9, 3, 'true');
