INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
VALUES ('user', 1, 1, 1, 1, '{bcrypt}$2a$10$5e3dB36HeRcozRgp8xQfw.tfD3Qsut8xu/NT9g/DSpVKg9Kzuitrq');

INSERT INTO users (username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
VALUES ('admin', 1, 1, 1, 1, '{bcrypt}$2a$10$5e3dB36HeRcozRgp8xQfw.tfD3Qsut8xu/NT9g/DSpVKg9Kzuitrq');

INSERT INTO users_roles (users_username, roles_name) VALUES ('user', 'ROLE_USER');
INSERT INTO users_roles (users_username, roles_name) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO users_roles (users_username, roles_name) VALUES ('admin', 'ROLE_USER');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000001, 'Madrid', 'IES', 'Manolete', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000002, 'Madrid', 'CEIP', 'Joselete', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000003, 'Móstoles', 'IES', 'Felipete', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000004, 'Alcorcón', 'CEIP', 'Juanete', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000005, 'Leganés', 'CEIP', 'Adolfete', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000006, 'Madrid', 'IES', 'Pedrito', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000007, 'Madrid', 'CEIP', 'Alfonsito', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000008, 'Móstoles', 'IES', 'Pepito', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000009, 'Alcorcón', 'CEIP', 'Juanito', now(), 'system');

INSERT INTO centros (codigo, localidad, generico, nombre, ultima_modificacion, usuario_modificacion)
VALUES (28000010, 'Leganés', 'CEIP', 'Adolfito', now(), 'system');