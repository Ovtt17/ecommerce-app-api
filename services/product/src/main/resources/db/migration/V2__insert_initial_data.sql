-- Insert sample categories
insert into category (id, name, description)
values
    (nextval('category_seq'), 'Electronics', 'Electronic items like phones, laptops, etc.'),
    (nextval('category_seq'), 'Books', 'Printed and digital books'),
    (nextval('category_seq'), 'Clothing', 'Men and Women clothing'),
    (nextval('category_seq'), 'Toys', 'Toys for kids of all ages'),
    (nextval('category_seq'), 'Furniture', 'Home and office furniture'),
    (nextval('category_seq'), 'Groceries', 'Everyday grocery items'),
    (nextval('category_seq'), 'Sports', 'Sporting goods and apparel'),
    (nextval('category_seq'), 'Health', 'Health and wellness products'),
    (nextval('category_seq'), 'Beauty', 'Cosmetics and skincare'),
    (nextval('category_seq'), 'Automotive', 'Car accessories and parts'),
    (nextval('category_seq'), 'Garden', 'Outdoor and gardening tools');

-- Insert sample products
insert into product (id, name, description, price, available_quantity, category_id)
values
    (nextval('product_seq'), 'Smartphone', 'Latest model smartphone', 699.99, 100, (select id from category where name = 'Electronics')),
    (nextval('product_seq'), 'Laptop', 'Powerful and lightweight laptop', 1299.00, 50, (select id from category where name = 'Electronics')),
    (nextval('product_seq'), 'Fiction Book', 'Bestselling fiction novel', 14.99, 200, (select id from category where name = 'Books')),
    (nextval('product_seq'), 'Notebook', 'Lined paper notebook', 2.50, 500, (select id from category where name = 'Books')),
    (nextval('product_seq'), 'T-Shirt', 'Cotton T-shirt in various sizes', 9.99, 300, (select id from category where name = 'Clothing')),
    (nextval('product_seq'), 'Jeans', 'Slim fit jeans', 29.99, 150, (select id from category where name = 'Clothing')),
    (nextval('product_seq'), 'Action Figure', 'Superhero toy figure', 19.99, 120, (select id from category where name = 'Toys')),
    (nextval('product_seq'), 'Toy Car', 'Remote controlled car', 34.99, 80, (select id from category where name = 'Toys')),
    (nextval('product_seq'), 'Office Chair', 'Ergonomic chair for office use', 149.99, 30, (select id from category where name = 'Furniture')),
    (nextval('product_seq'), 'Dining Table', 'Wooden dining table set', 499.99, 15, (select id from category where name = 'Furniture')),
    (nextval('product_seq'), 'Protein Powder', 'Vanilla flavored protein supplement', 39.99, 100, (select id from category where name = 'Health')),
    (nextval('product_seq'), 'Face Cream', 'Anti-aging face cream', 24.99, 75, (select id from category where name = 'Beauty')),
    (nextval('product_seq'), 'Car Vacuum', 'Portable vacuum cleaner for cars', 45.00, 60, (select id from category where name = 'Automotive'));
