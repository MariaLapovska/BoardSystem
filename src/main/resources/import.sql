INSERT INTO subject (id, name) VALUES (1, 'Math');
INSERT INTO subject (id, name) VALUES (2, 'English');
INSERT INTO subject (id, name) VALUES (3, 'Ukrainian Language and Literature');
INSERT INTO subject (id, name) VALUES (4, 'Physics');
INSERT INTO subject (id, name) VALUES (5, 'Chemistry');
INSERT INTO subject (id, name) VALUES (6, 'Biology');
INSERT INTO subject (id, name) VALUES (7, 'Geography');
INSERT INTO subject (id, name) VALUES (8, 'Ukrainian History');
INSERT INTO subject (id, name) VALUES (9, 'Foreign History');
INSERT INTO subject (id, name) VALUES (10, 'Foreign Literature');

INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (1, 'Software Engineering', 10, TRUE);
INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (2, 'Psychology', 10, TRUE);
INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (3, 'Economics', 15, TRUE);
INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (4, 'Medicine', 20, TRUE);
INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (5, 'International Law', 30, TRUE);

INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (1, 1);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (1, 2);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (1, 4);

INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (2, 3);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (2, 2);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (2, 6);

INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (3, 3);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (3, 1);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (3, 7);

INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (4, 6);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (4, 5);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (4, 3);

INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (5, 10);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (5, 8);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (5, 9);

INSERT INTO uuser(id, login, password, name, surname, role) VALUES (1, 'adminka', '$2a$06$SwVUZcg9GFgAtXddyxYl6uJVszIJdV0e4/RGXHu5wZO4Rz2lUTpe.', 'Ivan', 'Ivanov', 'ADMIN');

INSERT INTO uuser(id, login, password, name, surname, role) VALUES (2, 'maria', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (3, 'katya', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Peter', 'Petrov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (4, 'vasya', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'vasya', 'vasya', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (5, 'jack', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'jack', 'jack', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (6, 'sasha', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'sasha', 'sasha', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (7, 'dmitriy', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'dmitriy', 'dmitriy', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (8, 'petya', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'petya', 'petya', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (9, 'nastia', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'nastia', 'nastia', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (10, 'natasha', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'natasha', 'natasha', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (11, '111111', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (12, '222222', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (13, '333333', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (14, '444444', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (15, '555555', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');

INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (1, 180, '0123456789', 1, 2, 780, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (2, 186, '1111111111', 1, 3, 717, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (3, 200, '2222222222', 1, 4, 791, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (4, 172, '3333333333', 1, 5, 730, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (5, 195, '4444444444', 1, 6, 795, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (6, 135, '5555555555', 1, 7, 624, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (7, 178, '6666666666', 1, 8, 742, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (8, 199, '7777777777', 1, 9, 718, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (9, 183, '8888888888', 1, 10, 741, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (10, 174, '9999999999', 1, 11, 645, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (11, 168, '0000000000', 1, 12, 759, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (12, 171, '9876543210', 1, 13, 642, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (13, 188, '6985471230', 1, 14, 788, 'NEW');
INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade, status) VALUES (14, 145, '1278546930', 1, 15, 655, 'NEW');

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (2, 177, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (2, 177, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (2, 177, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (3, 197, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (3, 197, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (3, 197, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (4, 186, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (4, 186, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (4, 186, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (5, 200, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (5, 200, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (5, 200, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (6, 163, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (6, 163, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (6, 163, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (7, 188, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (7, 188, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (7, 188, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (8, 173, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (8, 173, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (8, 173, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (9, 186, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (9, 186, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (9, 186, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (10, 157, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (10, 157, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (10, 157, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (11, 197, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (11, 197, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (11, 197, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (12, 157, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (12, 157, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (12, 157, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (13, 200, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (13, 200, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (13, 200, 4);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (14, 170, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (14, 170, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (14, 170, 4);

DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence start 20 increment 1;