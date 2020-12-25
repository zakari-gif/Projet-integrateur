insert into CLIENT values (34767865, 'Pierre', 'Gerard', '/photo/pierre.png', 'gege.pierre@gmail.com', '06.54.37.21.56', '18bis', 'rue de Grenoble', '38670', 'SeyssinetPariset');
insert into CLIENT values (34767866, 'Pierre', 'Quentin', '/photo/pierre.png', 'quentin.pierre@gmail.com', '06.56.38.24.54', '3', 'rue de Toulouse', '38640', 'Fontaine');

insert into COMMANDE values (1, 34767865, to_date('20200326', 'yyyymmdd'), 10.05, '18bis', 'rue de Grenoble', '38670', 'Seyssinet-Pariset');
insert into COMMANDE values (2, 34767866, to_date('20200401', 'yyyymmdd'), 12.43, '3', 'rue de Toulouse', '38640', 'Fontaine');
insert into COMMANDE values (3, 34767866, to_date('20200413', 'yyyymmdd'), 8.90, '3', 'rue de Toulouse', '38640', 'Fontaine');

insert into FILM_COMMANDES values (12, 1, 3.75);
insert into FILM_COMMANDES values (23, 2, 3.75);
insert into FILM_COMMANDES values (42, 3, 3.75);

insert into PLAT values ('coca cola', 1);
insert into PLAT values ('pizza aux enchoix', 1);
insert into PLAT values ('donut au chocolat', 1);
insert into PLAT values ('ice tea', 2);
insert into PLAT values ('salade à la mozzarella', 2);
insert into PLAT values ('limonade', 3);
insert into PLAT values ('hamburger', 3);
insert into PLAT values ('crème brûlée', 3);
