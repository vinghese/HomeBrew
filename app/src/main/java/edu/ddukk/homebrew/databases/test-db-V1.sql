PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata VALUES('en_US');
CREATE TABLE room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT INTO room_master_table VALUES(42,'bb9d7054057f5970a9a6a5ab76c2cab9');
CREATE TABLE `user` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT, `last_name` TEXT, `email` TEXT, `password` TEXT NOT NULL);
CREATE TABLE `timetable` (`table_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `day_of_week` TEXT NOT NULL, `start_time` TEXT NOT NULL, `end_time` TEXT NOT NULL, `period` TEXT NOT NULL, `programs_id` INTEGER NOT NULL, `semester_id` INTEGER NOT NULL, `subject_id` INTEGER NOT NULL, `instructor_id` INTEGER, `break_id` INTEGER, FOREIGN KEY(`subject_id`) REFERENCES `subjects`(`subject_id`) ON UPDATE NO ACTION ON DELETE SET NULL , FOREIGN KEY(`instructor_id`) REFERENCES `instructors`(`instructor_id`) ON UPDATE NO ACTION ON DELETE SET NULL , FOREIGN KEY(`break_id`) REFERENCES `breaks`(`break_id`) ON UPDATE NO ACTION ON DELETE SET NULL , FOREIGN KEY(`programs_id`) REFERENCES `programs`(`programs_id`) ON UPDATE NO ACTION ON DELETE SET NULL , FOREIGN KEY(`semester_id`) REFERENCES `semesters`(`semester_id`) ON UPDATE NO ACTION ON DELETE SET NULL );
CREATE TABLE `subjects` (`subject_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject_name` TEXT NOT NULL);
CREATE TABLE `instructors` (`instructor_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `instructor_name` TEXT NOT NULL);
INSERT INTO instructors VALUES(1,'Mr Vinu Varghese V.V');
INSERT INTO instructors VALUES(2,'Ms Suji Jose');
INSERT INTO instructors VALUES(3,'Mr Rishin Heb');
INSERT INTO instructors VALUES(4,'Mr Pradeep M');
CREATE TABLE `semesters` (`semester_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `semester_name` TEXT NOT NULL);
INSERT INTO semesters VALUES(1,'Sem 1');
INSERT INTO semesters VALUES(2,'Sem 2');
INSERT INTO semesters VALUES(3,'Sem 3');
INSERT INTO semesters VALUES(4,'Sem 4');
INSERT INTO semesters VALUES(5,'Sem 5');
INSERT INTO semesters VALUES(6,'Sem 6');
INSERT INTO semesters VALUES(7,'Sem 7');
INSERT INTO semesters VALUES(8,'Sem 8');
CREATE TABLE `breaks` (`break_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `break_type` TEXT NOT NULL, `break_start_time` TEXT NOT NULL, `break_end_time` TEXT NOT NULL);
INSERT INTO breaks VALUES(1,'TEA','10:50 AM','11:00 AM');
INSERT INTO breaks VALUES(2,'TEA','02:50 PM','03:00 PM');
INSERT INTO breaks VALUES(3,'LUNCH','01:00 PM','02:00 PM');
CREATE TABLE `programs` (`programs_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `programs_code` TEXT NOT NULL, `programs_name` TEXT NOT NULL, `number_of_semesters` INTEGER NOT NULL);
INSERT INTO programs VALUES(1,'23-493','M.Voc in Software Application Development',4);
INSERT INTO programs VALUES(2,'20-493','M.Voc in Consultancy Management',4);
INSERT INTO programs VALUES(3,'20-491','B.Voc in Business Process and Data Analytics',6);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('programs',3);
INSERT INTO sqlite_sequence VALUES('breaks',3);
INSERT INTO sqlite_sequence VALUES('instructors',4);
INSERT INTO sqlite_sequence VALUES('semesters',8);
CREATE INDEX `index_timetable_subject_id_instructor_id_programs_id_break_id_semester_id` ON `timetable` (`subject_id`, `instructor_id`, `programs_id`, `break_id`, `semester_id`);
CREATE INDEX `index_timetable_instructor_id` ON `timetable` (`instructor_id`);
CREATE INDEX `index_programs_programs_code` ON `programs` (`programs_code`);
COMMIT;