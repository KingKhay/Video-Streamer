create table comment (
                         id binary(16) not null,
                         comment_date date,
                         message varchar(255),
                         username varchar(255),
                         video_id binary(16) not null,
                         primary key (id)
);

create table user (
                      id binary(16) not null,
                      dob date,
                      email varchar(255) not null,
                      first_name varchar(255),
                      image_url varchar(255),
                      last_name varchar(255),
                      password varchar(255) not null,
                      username varchar(255) not null,
                      primary key (id)
);

create table video_details (
                               video_id binary(16) not null,
                               date_updated date,
                               date_uploaded date,
                               resource_url varchar(255),
                               video_name varchar(255),
                               user_id binary(16) not null,
                               primary key (video_id)
);

CREATE TABLE `role` (
                         `id` binary(16) NOT NULL,
                         `name` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `users_roles` (
                               `user_id` binary(16) NOT NULL,
                               `role_id` binary(16) NOT NULL,
                               KEY `user_fk_idx` (`user_id`),
                               KEY `role_fk_idx` (`role_id`),
                               CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

alter table user
    add constraint UKf9dvvibvpfsldnu8wh3enop4i unique (username, email);

alter table role
    add constraint uk_name unique (name);

alter table comment
    add constraint FK2frraphlrgpo8ex9s7ax6w62c
        foreign key (video_id)
            references video_details (video_id);

alter table video_details
    add constraint FKp0poy6glsosp1o674md5mjrxe
        foreign key (user_id)
            references user (id);