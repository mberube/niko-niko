CREATE TABLE mood (
	id serial PRIMARY KEY,
	user_id bigserial NOT NULL REFERENCES users (id),
	calendar_day date NOT NULL,
	"value" integer NOT NULL
);