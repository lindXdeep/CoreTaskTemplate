DROP TABLE If EXISTS Users.Users;CREATE TABLE IF NOT EXISTS Users.Users(
                            id       LONG NOT NULL,
                            name     CHAR(64) NOT NULL,
                            lastName CHAR(64) NOT NULL,
                            age      TINYINT);