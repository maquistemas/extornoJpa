/*Insertamos mqregiones*/
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Sudamérica');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Centroamérica');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Norteamérica');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Europa');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Asia');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Africa');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Oceanía');
INSERT INTO mqregiones (id, nombre) VALUES (seq_mqregiones.nextval, 'Antártida');

/*Insertar personas*/
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', to_date('2018-01-01', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 2, 'Mr. John', 'Doe', 'john.doe@gmail.com', to_date('2018-01-02', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', to_date('2018-01-03', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', to_date('2018-01-04', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', to_date('2018-02-01', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 3, 'Richard', 'Helm', 'richard.helm@gmail.com', to_date('2018-02-10', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', to_date('2018-02-18', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 3, 'John', 'Vlissides', 'john.vlissides@gmail.com', to_date('2018-02-28', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', to_date('2018-03-03', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 5, 'Magma', 'Lee', 'magma.lee@gmail.com', to_date('2018-03-04', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', to_date('2018-03-05', 'yyyy-mm-dd'));
INSERT INTO mqpersonas (id, mqregiones_id, nombre, apellido, email, create_at) VALUES(seq_mqpersonas.nextval, 7, 'Jade', 'Doe', 'jane.doe@gmail.com', to_date('2018-03-06', 'yyyy-mm-dd'));

/*insertamos roles*/
INSERT INTO mqroles (id, nombre) VALUES (seq_mqroles.nextval, 'ROLE_USER');
INSERT INTO mqroles (id, nombre) VALUES (seq_mqroles.nextval, 'ROLE_ADMIN');
INSERT INTO mqroles (id, nombre) VALUES (seq_mqroles.nextval, 'ROLE_CLIENTE');
INSERT INTO mqroles (id, nombre) VALUES (seq_mqroles.nextval, 'ROLE_VENTANILLA');

/* Creamos algunos usuarios con sus roles */
INSERT INTO mqusuarios (id, username, password, enabled) VALUES (seq_mqusuarios.nextval, 'andres','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1);
INSERT INTO mqusuarios (id, username, password, enabled) VALUES (seq_mqusuarios.nextval, 'admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1);

INSERT INTO mqusuarios_mqroles (mqusuarios_id, mqroles_id) VALUES (1, 1);
INSERT INTO mqusuarios_mqroles (mqusuarios_id, mqroles_id) VALUES (2, 2);
INSERT INTO mqusuarios_mqroles (mqusuarios_id, mqroles_id) VALUES (2, 1);