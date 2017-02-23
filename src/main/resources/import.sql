INSERT INTO subject (id, name) VALUES (1, 'Math');
INSERT INTO subject (id, name) VALUES (2, 'English');
INSERT INTO subject (id, name) VALUES (3, 'Ukrainian');
INSERT INTO faculty (id, name, info, passing_score, recruitment_plan, is_available) VALUES (1, 'Software engineering', 'This is faculty', 750, 100, 1);
INSERT INTO faculty_subject(faculty_id, subject_id) VALUES (1, 1);