drop table if exists User;
drop table if exists Product;

create table User(
                  ID int not null auto_increment,
                  UserName varchar(30),
                  Password varchar(30),
                  FullName varchar(200),
                  RightsLevel int default 0,
                  constraint pk_user primary key(ID));

insert into User(UserName,Password,FullName,RightsLevel) value ('test','test','Operator stock', 1);
insert into User(UserName,Password,FullName,RightsLevel) value ('admin','admin','Administrator', 0);
insert into User(UserName,Password,FullName,RightsLevel) value ('operator','operator','Operator Gater', 2);

create table Product(
                    ID int not null auto_increment,
                    Label varchar(50) not null,
                    Length numeric(10, 2) not null,
                    Width numeric(10, 2) not null,
                    Thick numeric(10, 2) not null,
                    constraint pk_product primary key(ID));

insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ NERINDELUITĂ 24X15X2000MM', 2000, 24, 15);
insert into Product(Label, Length, Width,  Thick) values ('PACHET 10BUC RIGLĂ NERIND 24X15X2000MM', 2000, 24, 15);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ NERINDELUITĂ 24X20X2000MM', 2000, 24, 20);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ NERINDELUITĂ 24X24X2000MM', 2000, 24, 24);
insert into Product(Label, Length, Width,  Thick) values ('PACHET 10BUC RIGLĂ NERIND 24X24X2000MM', 2000, 24, 24);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ NERINDELUITĂ 24X30X2000MM', 2000, 24, 30);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ NERINDELUITĂ 24X40X2000MM', 2000, 24, 40);
insert into Product(Label, Length, Width,  Thick) values ('ŞIPCĂ RINDELUITĂ 16X35X3000MM', 3000, 16, 35);
insert into Product(Label, Length, Width,  Thick) values ('ŞIPCĂ RINDELUITĂ 24X48X3000MM', 3000, 24, 48);
insert into Product(Label, Length, Width,  Thick) values ('ŞIPCĂ RINDELUITĂ 46X46X3000MM', 3000, 46, 46);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 10X10X1000 MM', 1000, 10, 10);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 10X20X1000 MM', 1000, 10, 20);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 10X40X1000 MM', 1000, 10, 40);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 15X20X1000 MM', 1000, 15, 20);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 30X40X1000 MM', 1000, 30, 40);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ 40X40X1000 MM', 1000, 40, 40);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ RINDELUITĂ 15X18X2000 MM', 2000, 15, 18);
insert into Product(Label, Length, Width,  Thick) values ('PACHET 10BUC RIGLĂ RINDELUITĂ 15X18X2000 MM', 2000, 15, 18);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ RINDELUITĂ 15X28X2000 MM', 2000, 15, 28);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ RINDELUITĂ 18X18X2000 MM', 2000, 18, 18);
insert into Product(Label, Length, Width,  Thick) values ('PACHET 10BUC RIGLĂ RINDELUITĂ 18X18X2000 MM', 2000, 18, 18);
insert into Product(Label, Length, Width,  Thick) values ('RIGLĂ RINDELUITĂ 18X30X2000 MM', 2000, 18, 30);
insert into Product(Label, Length, Width,  Thick) values ('PACHET 10 BUC RIGLĂ RINDELUITĂ 18X30X2000 MM', 2000, 18, 30);