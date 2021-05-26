insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('Artem', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com'),
('Daria', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'manager@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 2),
(2, 1);

insert into products (title, price)
values
('Boots', 50.00),
('Pants', 40.00),
('Book', 15.00),
('Pizza', 10.00),
('Hat', 20.00),
('Shirt', 25.00),
('Towel', 8.00),
('Coffee', 3.00),
('Car', 20000.00),
('Tea', 4.00),
('Bread', 5.00),
('Socks', 5.00),
('Dress', 85.00),
('Knife', 6.00),
('Fork', 6.00),
('Spoon', 6.00),
('Jacket', 100.00),
('Coat', 150.00),
('Mat', 15.00),
('IceCream', 3.00);