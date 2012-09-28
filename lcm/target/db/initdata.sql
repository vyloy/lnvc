INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_xxxxx', 1, 'Dial', 'SIP/${EXTEN},,');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_xxxx', 1, 'Dial', 'SIP/${EXTEN},,');

INSERT INTO sys_media (media_name, media_path, status, file_name, customer_id, media_desc, original_name) VALUES ('movie', '/opt/lcp/media/movie.mp4', 1, 'movie.mp4', 2, '', 'movie.mp4');


INSERT INTO mcu_server(server_name, server_ip, server_status, server_url, cs_ip, cs_port, cs_username, cs_user_passwd)
    VALUES ('mcu', '127.0.0.1', 1, 'http://127.0.0.1:6080/lcmRPC', '127.0.0.1', 5038, 'manager', '123');

INSERT INTO sys_rates (rates_name, start_time, end_time, rates_type, rates_tarriff, status, rates_status) VALUES ('bill', NULL, NULL, 2, 1, 1, 1);

INSERT INTO authority (authority_name, mark, del) VALUES ('上传文件', 'fileupload', 1);
INSERT INTO authority (authority_name, mark, del) VALUES ('共享文档', 'sharedocument', 1);
INSERT INTO authority (authority_name, mark, del) VALUES ('共享桌面', 'screenshare', 1);
INSERT INTO authority (authority_name, mark, del) VALUES ('电子白板', 'whiteboard', 1);
INSERT INTO authority (authority_name, mark, del) VALUES ('邀请加入会议', 'invitejoinconference', 1);
INSERT INTO authority (authority_name, mark, del) VALUES ('踢出会议', 'kickfromconference', 1);

INSERT INTO conference_type (type_name, del) VALUES ('讲座会议类型', 1);
--INSERT INTO conference_type (type_name, del) VALUES ('自由会议类型', 1);

INSERT INTO conference_role (role_name, del) VALUES ('主持人', 1);
INSERT INTO conference_role (role_name, del) VALUES ('主讲人', 1);
INSERT INTO conference_role (role_name, del) VALUES ('普通会议者', 1);
--INSERT INTO conference_role (role_name, del) VALUES ('自由人', 1);

INSERT INTO conf_role_authority (authority_id, role_id) VALUES (1, 2);
INSERT INTO conf_role_authority (authority_id, role_id) VALUES (2, 2);
INSERT INTO conf_role_authority (authority_id, role_id) VALUES (3, 2);
INSERT INTO conf_role_authority (authority_id, role_id) VALUES (4, 2);
INSERT INTO conf_role_authority (authority_id, role_id) VALUES (5, 1);
INSERT INTO conf_role_authority (authority_id, role_id) VALUES (6, 1);

INSERT INTO conference_type_role (conference_type_id, conference_role_id) VALUES (1, 1);
INSERT INTO conference_type_role (conference_type_id, conference_role_id) VALUES (1, 2);
INSERT INTO conference_type_role (conference_type_id, conference_role_id) VALUES (1, 3);

INSERT INTO sys_params(module, "key", "value") VALUES ('lcm', 'version', 1);

alter table conference_type_role add column maxnum integer default -1;
alter table conference_type_role add column minnum integer default -1;
COMMENT ON COLUMN conference_type_role.maxnum IS '最大数目';
COMMENT ON COLUMN conference_type_role.minnum IS '最小数目';

update conference_type_role set maxnum = 1, minnum = 1 where id = (select tr.id from conference_type_role tr join conference_type t on tr.conference_type_id = t.id join conference_role r on tr.conference_role_id = r.id where t.type_name = '讲座会议类型' and r.role_name = '主持人');


--INSERT INTO conference_type_role (conference_type_id, conference_role_id) VALUES (2, 4);