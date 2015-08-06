--user
drop table if exists user;
create table user (username, password);
insert into user values('admin', '123456');

--app_system
drop table if exists app_system;
CREATE TABLE app_system (id TEXT, system_name TEXT, system_desc TEXT);
insert into app_system values('1','CDMS','公共数据管理系统');
insert into app_system values('2','CAPSTONE','Capstone系统');
insert into app_system values('3','HIPO','Hipo系统');
insert into app_system values('4','HUNDSUN','恒生');
insert into app_system values('5','IDW','IDW');