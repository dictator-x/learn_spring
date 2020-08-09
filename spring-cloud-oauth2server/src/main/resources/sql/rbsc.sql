SET SQL_MODE='ALLOW_INVALID_DATES';

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

DROP TABLE IF EXISTS `oauth_client_details`;
create table `oauth_client_details` (
                                      `client_id` VARCHAR(256) PRIMARY KEY,
                                      `resource_ids` VARCHAR(256),
                                      `client_secret` VARCHAR(256),
                                      `scope` VARCHAR(256),
                                      `authorized_grant_types` VARCHAR(256),
                                      `web_server_redirect_uri` VARCHAR(256),
                                      `authorities` VARCHAR(256),
                                      `access_token_validity` INTEGER,
                                      `refresh_token_validity` INTEGER,
                                      `additional_information` VARCHAR(4096),
                                      `autoapprove` VARCHAR(256)
);

INSERT INTO
    oauth_client_details (
    client_id,
    client_secret,
    resource_ids,
    scope,
    authorized_grant_types,
    access_token_validity,
    refresh_token_validity,
    autoapprove,
    web_server_redirect_uri
)
VALUES
(
    'client1',
    '$2a$10$cS8cZH450cIZ6r2oTrudC.yLC.c0uJZ3ua3fe7Hwi4iRorAnzABCe',
    'oauth2_resource_test_api',
    'read,write',
    'client_credentials,implicit,authorization_code,refresh_token,password',
    1000000,
    1000000,
    'false',
    'www.google.ca'
);

DROP TABLE IF EXISTS `oauth_client_token`;
create table `oauth_client_token` (
                                    `token_id` VARCHAR(256),
                                    `token` blob,
                                    `authentication_id` VARCHAR(256) PRIMARY KEY,
                                    `user_name` VARCHAR(256),
                                    `client_id` VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_access_token`;
create table `oauth_access_token` (
                                    `token_id` VARCHAR(256),
                                    `token` blob,
                                    `authentication_id` VARCHAR(256) PRIMARY KEY,
                                    `user_name` VARCHAR(256),
                                    `client_id` VARCHAR(256),
                                    `authentication` blob,
                                    `refresh_token` VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_refresh_token`;
create table `oauth_refresh_token` (
                                     `token_id` VARCHAR(256),
                                     `token` blob,
                                     `authentication` blob
);

DROP TABLE IF EXISTS `oauth_code`;
create table `oauth_code` (
                            `code` VARCHAR(256), `authentication` blob
);

DROP TABLE IF EXISTS `oauth_approvals`;
create table `oauth_approvals` (
                                 `userId` VARCHAR(256),
                                 `clientId` VARCHAR(256),
                                 `scope` VARCHAR(256),
                                 `status` VARCHAR(10),
                                 `expiresAt` TIMESTAMP,
                                 `lastModifiedAt` TIMESTAMP
);

DROP TABLE IF EXISTS `ClientDetails`;
-- customized oauth_client_details table
create table `ClientDetails` (
                               `appId` VARCHAR(256) PRIMARY KEY,
                               `resourceIds` VARCHAR(256),
                               `appSecret` VARCHAR(256),
                               `scope` VARCHAR(256),
                               `grantTypes` VARCHAR(256),
                               `redirectUrl` VARCHAR(256),
                               `authorities` VARCHAR(256),
                               `access_token_validity` INTEGER,
                               `refresh_token_validity` INTEGER,
                               `additionalInformation` VARCHAR(4096),
                               `autoApproveScopes` VARCHAR(256)
);