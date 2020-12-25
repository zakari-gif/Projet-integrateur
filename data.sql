insert into CLIENT values(1,'Abdou','Zakari',photozak,'mahabzakariyaou@gmail.com','0763345584','36','rue calmette','38000','Grenoble');
insert into CLIENT values(1,'Abdou','Zakari',photozak,'mahabzakariyaou@gmail.com','0763345584','36','rue calmette','38000','Grenoble');
insert into CLIENT values(2,'Bruno','Disson',photobruno,'br.disson@gmail.com','0638419287','20','rue docteur Edouard','38000','Grenoble');
insert into CLIENT values(3,'Robin','Chaoouis',photorobin,'chappuis73@gmail.com','0651771665','25','rue VictorHugo','38000','Grenoble');
insert into CLIENT values(4,'Julien','Dufourt',photojul,'jdufourt20@gmail.com','0632148927','15','rue Pierre Semard','38000','Grenoble');
insert into CLIENT values(5,'Mathis','Vigne',photomat,'mathis.vigne73@gmail.com','0632148927','10','rue vallier','38400','Grenoble');
insert into CLIENT values(6,'Elaoudi','Rayhana',photoray,'elaouadi1998@gmail.com','0632148927','34','rue saint martin','38000','Grenoble');
---Insertions d'images-----------------------------------------------
Insert into photozak(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go

Insert into photobruno(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go

Insert into photorobin(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go
Insert into photojul(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go

Insert into photomat(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go

Insert into photoray(ImageName) values ('photo1.JPG')
go
update dbo.Image
set Photo = 
(SELECT * FROM OPENROWSET(BULK 'e:\image\photo\photo1.JPG', SINGLE_BLOB)AS x ) 
WHERE ImageName='photo1.JPG'
go
-- Export image
declare @SQLcommand nvarchar(4000)
set @SQLcommand = 'bcp "SELECT Photo FROM mabase..t_ImageWarehouse" queryout "e:\image\photo\expphoto1.jpg" -T -n -SMonInstance'
 
exec xp_cmdshell @SQLcommand
go
--------------------------------------------------------------------------------------------------------

insert into COMMANDE values(10,1,to_date('20042020','yyyymmdd'),'20 euros','36','rue docteur calmette','38000','Grenoble');
insert into COMMANDE values(11,2,to_date('20052020','yyyymmdd'),'19 euros','20','rue docteur Edouard','38000','Grenoble');
insert into COMMANDE values(12,3,to_date('20062020','yyyymmdd'),'15 euros','25','rue VictorHugo','38000','Grenoble');
insert into COMMANDE values(13,4,to_date('20042020','yyyymmdd'),'21 euros','15','rue Pierre Semard','38000','Grenoble');
insert into COMMANDE values(14,5,to_date('20032020','yyyymmdd'),'20 euros','10','rue vallier','38400','Grenoble');
insert into COMMANDE values(15,6,to_date('20072020','yyyymmdd'),'30 euros','34','rue saint martin','38000','Grenoble');


insert into FILM values(100,'Ligne Verte','4 euros','2h30');
insert into FILM values(101,'Rambo I','5 euros','2h');
insert into FILM values(102,'Rambo II','3 euros','2h');
insert into FILM values(103,'Banlieusard','5 euros','2h');
insert into FILM values(104,'La Cite Rose','4 euros','1h45');
insert into FILM values(105,'Black Lightening','4 euros','2h');
insert into FILM values(106,'Niamey','3 euros','1h30');

insert into CARTE values(C1,305);
insert into CARTE values(C2,306);
insert into CARTE values(C3,307);
insert into CARTE values(C4,308);
insert into CARTE values(C5,309);
insert into CARTE values(C6,310);
insert into CARTE values(C7,311);

insert into INGREDIENT values(ING1,INGREDIENTS ingredient='tomate');
insert into INGREDIENT values(ING2,INGREDIENTS ingredient='pomme de terre');
insert into INGREDIENT values(ING3,INGREDIENTS ingredient='steak');
insert into INGREDIENT values(ING4,INGREDIENTS ingredient='salade');
insert into INGREDIENT values(ING5,INGREDIENTS ingredient='semoule');
insert into INGREDIENT values(ING6,INGREDIENTS ingredient='poulet');
insert into INGREDIENT values(ING7,INGREDIENTS ingredient='tomate');


insert into PLAT values(P1,c1,TYPE_PLAT typePlat='entrée');
insert into PLAT values(P2,c2,TYPE_PLAT typePlat='principal');
insert into PLAT values(P3,c3,TYPE_PLAT typePlat='dessert');
insert into PLAT values(P4,c4,TYPE_PLAT typePlat='boisson');
insert into PLAT values(P5,c5,TYPE_PLAT typePlat='entrée');
insert into PLAT values(P6,c6,TYPE_PLAT typePlat='principal');
insert into PLAT values(P1,c1,TYPE_PLAT typePlat='entrée');

insert into CARTE_COMPOSITION(c1,P1);
nsert into CARTE_COMPOSITION(c2,P2);
nsert into CARTE_COMPOSITION(c3,P3);
nsert into CARTE_COMPOSITION(c4,P4);
nsert into CARTE_COMPOSITION(c5,P5);
nsert into CARTE_COMPOSITION(c6,P6);




