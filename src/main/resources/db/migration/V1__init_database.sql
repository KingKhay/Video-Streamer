CREATE TABLE comment (
                         id uuid NOT NULL,
                         comment_date date,
                         message varchar(255),
                         username varchar(255),
                         video_id uuid NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE "user" (
                        id uuid NOT NULL,
                        dob date,
                        email varchar(255) NOT NULL,
                        first_name varchar(255),
                        image_url varchar(255),
                        last_name varchar(255),
                        password varchar(255) NOT NULL,
                        username varchar(255) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE video_details (
                               video_id uuid NOT NULL,
                               date_updated date,
                               date_uploaded date,
                               resource_url varchar(255),
                               video_name varchar(255),
                               user_id uuid NOT NULL,
                               PRIMARY KEY (video_id)
);

CREATE TABLE "role" (
                        id uuid NOT NULL,
                        name varchar(45) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE users_roles (
                             user_id uuid NOT NULL,
                             role_id uuid NOT NULL,
                             CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES "user" (id),
                             CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES "role" (id),
                             PRIMARY KEY (user_id, role_id)
);

CREATE TABLE password_reset_token (
                                      id SERIAL PRIMARY KEY,
                                      token VARCHAR(255),
                                      user_id uuid NOT NULL,
                                      expiry_date TIMESTAMP,
                                      FOREIGN KEY (user_id) REFERENCES "user" (id)
);

ALTER TABLE "user" ADD CONSTRAINT unique_username_email UNIQUE (username, email);
ALTER TABLE "role" ADD CONSTRAINT unique_name UNIQUE (name);
ALTER TABLE comment ADD CONSTRAINT fk_comment_video FOREIGN KEY (video_id) REFERENCES video_details (video_id);
ALTER TABLE video_details ADD CONSTRAINT fk_video_user FOREIGN KEY (user_id) REFERENCES "user" (id);