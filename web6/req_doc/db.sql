--drop table product;
--drop sequence product_seq;
--drop table product_cat;

--master table
create table product_cat(
id number(10)primary key,
name  varchar2(30) not null unique
);
insert into product_cat(id,name)values(101,'cloth');
insert into product_cat(id,name)values(102,'food');
insert into product_cat(id,name)values(103,'book');
insert into product_cat(id,name)values(104,'device');
insert into product_cat(id,name)values(105,'footware');
commit;
select * from product_cat;
select * from product_cat where id=101;




create table product(
id number(10)primary key,
name  varchar2(30) not null,
price  number(10,2) not null,
cat_id number(10),
constraint pfk_1 foreign key(cat_id) references product_cat(id)  
);

create sequence product_seq start with 1001;

insert into product(id,name,price,cat_id)values
(product_seq.nextval,'p1',100.50,101);

insert into product(id,name,price,cat_id)values
(product_seq.nextval,'p2',200.50,102);

insert into product(id,name,price,cat_id)values
(product_seq.nextval,'p3',300.50,103);

insert into product(id,name,price,cat_id)values
(product_seq.nextval,'p4',400.50,104);

commit;
select * from product;
select * from product p ,product_cat c where p.cat_id=c.id;

select p.id,c.id,p.name,c.name from product p ,product_cat c where p.cat_id=c.id;



delete from product  where id =10001;
update product  set name='p444' ,price=4444.4,cat_id=102  where id =10001;