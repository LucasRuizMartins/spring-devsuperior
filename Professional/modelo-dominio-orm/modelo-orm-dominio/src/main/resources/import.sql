INSERT INTO tb_category(description) VALUES ('Curso');
INSERT INTO tb_category(description) VALUES ('Oficina');

INSERT INTO tb_participant(name,email) VALUES ('Jose Silva', 'jose@gmail.com');
INSERT INTO tb_participant(name,email) VALUES ('Tiago Faria', 'tiago@gmail.com');
INSERT INTO tb_participant(name,email) VALUES ('Maria do Rosario', 'maria@gmail.com');
INSERT INTO tb_participant(name,email) VALUES ('Teresa Silva', 'teresa@gmail.com');

INSERT INTO tb_activity(price,category_id,description,name) VALUES (80.0, 1, 'Aprenda HTML de forma pratica', 'Curso HTML');
INSERT INTO tb_activity(price,category_id,description,name) VALUES (50.0, 2, 'Controle Versoes de seus projetos', 'Oficina de Github');

INSERT INTO tb_activity_group (activity_id, end_activity, start) VALUES (1, TIMESTAMP WITH TIME ZONE '2017-09-25T11:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T08:00:00Z');
INSERT INTO tb_activity_group (activity_id, end_activity, start) VALUES (2, TIMESTAMP WITH TIME ZONE '2017-09-25T18:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T14:00:00Z');
INSERT INTO tb_activity_group (activity_id, end_activity, start) VALUES (2, TIMESTAMP WITH TIME ZONE '2017-09-26T11:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-26T08:00:00Z');


INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (1,1);
INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (2,1);
INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (1,2);
INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (1,3);
INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (2,3);
INSERT INTO tb_participant_activity(activity_id, participant_id) VALUES (2,4);


 INSERT INTO  TB_ACTIVITY_ACTIVITY_GROUP (activity_group_id, activity_id) VALUES(1,1)
 INSERT INTO  TB_ACTIVITY_ACTIVITY_GROUP (activity_group_id, activity_id) VALUES(2,2)
 INSERT INTO  TB_ACTIVITY_ACTIVITY_GROUP (activity_group_id, activity_id) VALUES(3,2)