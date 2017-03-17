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

INSERT INTO faculty (id, name, recruitment_plan, is_available) VALUES (1, 'Software Engineering', 40, TRUE);
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

INSERT INTO uuser(id, login, password, name, surname, role) VALUES (1, 'lolka', '$2a$06$SwVUZcg9GFgAtXddyxYl6uJVszIJdV0e4/RGXHu5wZO4Rz2lUTpe.', 'Ivan', 'Ivanov', 'ADMIN');
INSERT INTO uuser(id, login, password, name, surname, role) VALUES (2, 'maria', '$2a$06$sM/AXOcuTpLmZ0Eo.YjTsuDToJ7rGkhcz3e34juC6NiSFwsJxjEea', 'Ivan', 'Ivanov', 'USER');

INSERT INTO application(id, certificate_grade, certificate_number, faculty_id, user_id, total_grade) VALUES (1, 180, '0123456789', 1, 2, 780);

INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 1);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 2);
INSERT INTO application_exam(application_id, exam_grade, subject_id) VALUES (1, 200, 4);

DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence start 11 increment 1;