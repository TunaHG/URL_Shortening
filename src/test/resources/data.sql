CREATE TABLE SHORT_URL (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    ORIGIN_URL VARCHAR(255),
    REQUEST_COUNT BIGINT,
    SHORTENED_URL VARCHAR(255),
    PRIMARY KEY (ID)
)

INSERT INTO SHORT_URL VALUES (1, 'https://www.naver.com', 'http://url.test/B', 1);