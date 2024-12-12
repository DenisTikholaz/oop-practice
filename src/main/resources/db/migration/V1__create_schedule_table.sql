CREATE TABLE swimming_lesson (
    id BIGSERIAL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL,
    lesson_number INTEGER NOT NULL,
    date VARCHAR(255) NOT NULL,
    coach VARCHAR(255) NOT NULL,
    pool_number INTEGER NOT NULL
);

