
INSERT INTO `course` (`id`, `created_on`, `modified_on`, `rrn`, `course_code`, `course_name`) VALUES
	(1, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a434e0656bc6490e9e26ea1b348b3870', 'CSC302', 'Object Oriented Programming'),
	(2, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a234e0656bc6490e9e26ea1b348b3876', 'CSC303', 'Introduction to Computer'),
	(3, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a234e0656bc6490e9e26ea1b348b3877', 'CSC305', 'Algorithm and Probability'),
	(4, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc6490e9e26ea1b348b3873', 'CSC304', 'Introduction to Data Structure'),
	(5, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc6490e9e26ea1b308b3873', 'ECO404', 'Introduction to Economics'),
	(6, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc6490e9e26ea1b318b3873', 'BUS102', 'Business Analysis'),
	(7, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc6490e0e26ea1b318b3873', 'CHM102', 'Applied Chemistry'),
	(8, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc6490e1e26ea1b318b3873', 'CSC107', 'Database Management System'),
	(9, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a264e0656bc9490e1e26ea1b318b3873', 'CSC108', 'Computer Networking'),
	(10, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a222e0656bc6490e9e26ea1b318b3873', 'BUS202', 'Operations Research'),
	(11, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a222e0656bc2290e9e26ea1b318b3873', 'MTH206', 'Linear Algebra'),
	(12, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a222e0656bc2290e9e26ea1b338b3873', 'MTH103', 'Advanced Calculus'),
	(13, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a222e0656bb2290e9e26ea1b338b3873', 'CHM301', 'Industrial Chemistry'),
	(14, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a222e0656bb2290e9e26ea1b118b3873', 'CHM701', 'Chemistry II');

INSERT INTO `student` (`id`, `created_on`, `modified_on`, `rrn`, `dept`, `email`, `firstname`, `lastname`, `phone`, `student_registration_no`) VALUES
	(1, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a434e0656bc6490e9e26ea1b348b3877', 'Computer', 'joe@gmail.com', 'Marine', 'Jacob', '2339425094250', 'CSC200001'),
	(2, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'c434e0656bc6490e9e26ea1b348b3877', 'BioChemistry', 'adrin@gmail.com', 'Andy', 'Kush', '2339425094251', 'BCH199901'),
	(3, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a134e0656bc6490e9e26ea1b348b3877', 'Mathematics', 'maxwell@yahoo.com', 'Cole', 'Selah', '2339425094252', 'MTH202201'),
	(4, '2018-01-01 00:00:00', '2018-01-01 00:00:00', 'a134e0656bc6490e9e76ea1b348b3877', 'Mathematics', 'agit@yahoo.com', 'Agit', 'Cole', '2339425094252', 'MTH202208'),
	(6, '2022-03-10 11:29:38', '2022-03-10 11:40:55', 'd52994a1-a05c-11ec-8edb-43d2fe7ee76d', 'Bio-Chemistry', 'jayjay@yahoo.com', 'Jacob', 'Jakes', '23390000000000', 'BCH2022001');

INSERT INTO `student_offered_courses` (`students_id`, `offered_courses_id`) VALUES
	(6, 13);