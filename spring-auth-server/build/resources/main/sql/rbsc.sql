DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                              `user_id` integer NOT NULL AUTO_INCREMENT,
                              `username` varchar(255) DEFAULT NULL,
                              `password` varchar(255) DEFAULT NULL,
                              `status` int(11) DEFAULT 0,
                              PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO sys_user (user_id, username, password, status) VALUES (1, 'messi', '$2a$10$cS8cZH450cIZ6r2oTrudC.yLC.c0uJZ3ua3fe7Hwi4iRorAnzABCe', 1);
INSERT INTO sys_user (user_id, username, password, status) VALUES (2, 'kobe', '$2a$10$IzVg9/0.qR7qlkg1ZHxUG.G0nGVhEQSZgwLWwtkoPQljTVY43gEH6', 1);
INSERT INTO sys_user (user_id, username, password) VALUES (3, 'jordan', '$2a$10$cS8cZH450cIZ6r2oTrudC.yLC.c0uJZ3ua3fe7Hwi4iRorAnzABCe');

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                           `role_id` integer NOT NULL AUTO_INCREMENT,
                           `role_name` varchar(255) DEFAULT NULL,
                           `role_desc` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO sys_role (role_id, role_name, role_desc) VALUES (1, 'ROLE_USER', 'User permission');
INSERT INTO sys_role (role_id, role_name, role_desc) VALUES (2, 'ROLE_ADMIN', 'Admin permission');

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                `sys_user_id` integer not null,
                                `sys_role_id` integer not null
) engine=InnoDB  DEFAULT CHARSET=utf8;
INSERT INTO sys_user_role(sys_user_id, sys_role_id) VALUES (1, 1);
INSERT INTO sys_user_role(sys_user_id, sys_role_id) VALUES (1, 2);
INSERT INTO sys_user_role(sys_user_id, sys_role_id) VALUES (2, 1);