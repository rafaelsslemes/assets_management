

-- To use sequence: 
-- insert into public.user values (nextval('user_seq'), 'api_user');

-- BCrypt hash for 123456 = $2a$12$/UtaNaJsHlq6fM2KKzegyu3hwMrn/5fSTMQufm9XS0MxOSsN/l62K

insert into api_user (id, login, name, password, email) values (1, 'api_user', 'API User', '$2a$12$/UtaNaJsHlq6fM2KKzegyu3hwMrn/5fSTMQufm9XS0MxOSsN/l62K', 'api_user@mail.com');
insert into api_user (id, login, name, password, email) values (2, 'api_admin', 'API Admin', '$2a$12$/UtaNaJsHlq6fM2KKzegyu3hwMrn/5fSTMQufm9XS0MxOSsN/l62K', 'api_admin@mail.com');

insert into role values (1, 'ROLE_USER');
insert into role values (2, 'ROLE_ADMIN');

insert into user_roles values (1, 1);
insert into user_roles values (2, 2);


insert into asset values (11, 'sample', 'type1');
insert into asset values (12, 'sample', 'type1');
insert into asset values (13, 'sample', 'type1');
insert into asset values (14, 'sample', 'type1');
insert into asset values (15, 'sample', 'type1');
insert into asset values (16, 'sample', 'type2');
insert into asset values (17, 'sample', 'type2');
insert into asset values (18, 'sample', 'type2');
insert into asset values (19, 'sample', 'type2');
insert into asset values (20, 'sample', 'type2');
