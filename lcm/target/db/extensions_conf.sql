delete from extensions_conf;

INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_xxxxx', 1, 'Dial', 'SIP/${EXTEN}@lntmcu,,');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_xxxx', 1, 'Dial', 'SIP/${EXTEN}');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_00xxxx', 1, 'Dial', 'SIP/${EXTEN:2}@72-peer');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_0088888xxxx', 1, 'Dial', 'SIP/${EXTEN:2}@72-peer');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_00888888xxxx', 1, 'Dial', 'SIP/${EXTEN:2}@72-peer');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_88888xxxx', 1, 'ExtenSpy', '${EXTEN:5},q');
INSERT INTO extensions_conf (context, exten, priority, app, appdata) VALUES ('default', '_888888xxxx', 1, 'ExtenSpy', '${EXTEN:6},Bq');
