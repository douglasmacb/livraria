create database livraria;
create user 'guiabolso'@'%' identified by 'livraria';
grant select, insert, delete, update on livraria.* to 'guiabolso'@'localhost';