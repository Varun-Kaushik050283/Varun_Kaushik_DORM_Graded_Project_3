-- ######### Start ###################################################################################
-- run the below scripts in (SQL Shell) only using (postgres) user
create user testuser with superuser createdb createrole login encrypted password 'pgadmin';
create database testdb with owner testuser;
-- ########### END ################################################################################### 


--\c testdb testuser; 

-- ######### Start ###################################################################################
-- run the below scripts either in (SQL Shell) or pgadmin4 tool by connecting testdb database
create schema ticket_tracker authorization testuser;
grant all on schema ticket_tracker to testuser;
grant all on schema ticket_tracker to public;
comment on schema ticket_tracker is 'testdb schema';

create table ticket_tracker.ticket(
  ticket_id int primary key,
  title character varying(50) not null,
  descr character varying(100) not null,
  created_on date
);

-- Sample test data insert to further test the application
INSERT INTO ticket_tracker.ticket(
	ticket_id, title, descr, created_on)
	VALUES (1,'Ticket_1','Ticket regarding application not responding X ','2024-08-26');
INSERT INTO ticket_tracker.ticket(
	ticket_id, title, descr, created_on)
	VALUES (2,'Ticket_2','Ticket regarding application not responding Y 2','2024-08-27');
INSERT INTO ticket_tracker.ticket(
	ticket_id, title, descr, created_on)
	VALUES (3,'Ticket_3','Ticket regarding application not responding X 3','2024-08-28');


delete from ticket_tracker.ticket; 
truncate table ticket_tracker.ticket; 
drop database if exists testdb;
drop user if exists testuser;
-- ############################# END ##################################################################