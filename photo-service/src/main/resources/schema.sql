CREATE TABLE IF NOT EXISTS photo (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    height  INTEGER NOT NULL CHECK (height > 0),
    width   INTEGER NOT NULL CHECK (width  > 0),
    photo   BYTEA   NOT NULL CHECK (octet_length(photo) > 0)
);