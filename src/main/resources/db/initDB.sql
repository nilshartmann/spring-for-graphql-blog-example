DROP SEQUENCE IF EXISTS comments_seq;
CREATE SEQUENCE comments_seq INCREMENT BY 50;

DROP SEQUENCE IF EXISTS users_seq;
CREATE SEQUENCE users_seq INCREMENT BY 50;

DROP SEQUENCE IF EXISTS posts_seq;
CREATE SEQUENCE posts_seq INCREMENT BY 50;

DROP TABLE comments IF EXISTS CASCADE;
DROP TABLE posts IF EXISTS CASCADE;
DROP TABLE users IF EXISTS CASCADE;

CREATE TABLE users (
    id       BIGINT               NOT NULL,
    username VARCHAR(20)          NOT NULL,
    ROLE     VARCHAR(256)         NOT NULL,
    password VARCHAR(20)          NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE NOT NULL,
    fullname VARCHAR(256)         NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE posts (
    id      BIGINT    NOT NULL,
    title   VARCHAR   NOT NULL,
    body    VARCHAR   NOT NULL,
    date    TIMESTAMP NOT NULL,
    user_id BIGINT    NOT NULL REFERENCES users (id),
    PRIMARY KEY (id)
);

CREATE TABLE comments (
    id      BIGINT  NOT NULL,
    comment VARCHAR NOT NULL,
    post_id BIGINT  NOT NULL REFERENCES posts (id),
    PRIMARY KEY (id)
)


