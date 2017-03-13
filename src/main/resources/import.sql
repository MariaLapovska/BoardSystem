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

INSERT INTO faculty (id, name, passing_score, recruitment_plan, is_available) VALUES (1, 'Software Engineering', 750, 40, 1);
INSERT INTO faculty (id, name, passing_score, recruitment_plan, is_available) VALUES (2, 'Psychology', 730, 10, 1);
INSERT INTO faculty (id, name, passing_score, recruitment_plan, is_available) VALUES (3, 'Economics', 745, 15, 1);
INSERT INTO faculty (id, name, passing_score, recruitment_plan, is_available) VALUES (4, 'Medicine', 780, 20, 1);
INSERT INTO faculty (id, name, passing_score, recruitment_plan, is_available) VALUES (5, 'International Law', 775, 30, 1);

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