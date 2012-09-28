--build 0907

INSERT INTO sys_params(module, "key", "value") VALUES ('lcm', 'version', 1);

alter table conference_type_role add column maxnum integer default -1;
alter table conference_type_role add column minnum integer default -1;
COMMENT ON COLUMN conference_type_role.maxnum IS '最大数目';
COMMENT ON COLUMN conference_type_role.minnum IS '最小数目';

update conference_type_role set maxnum = 1, minnum = 1 where id = (select tr.id from conference_type_role tr join conference_type t on tr.conference_type_id = t.id join conference_role r on tr.conference_role_id = r.id where t.type_name = '讲座会议类型' and r.role_name = '主持人');

