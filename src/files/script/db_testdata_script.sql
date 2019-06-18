INSERT INTO service (service_name, url, port, description) VALUES ('fleetTracker', 'ifleet.hu', '2990', 'fleet tracker service');
INSERT INTO service (service_name, url, port, description) VALUES ('feeCalculator', 'itoll.hu', '2980', 'toll fee calulator service');
INSERT INTO service (service_name, url, port, description) VALUES ('slothRescuer', 'bradypus.com', '2970', 'sloth rescuer service');
INSERT INTO service (service_name, url, port, description) VALUES ('giraffeCounter', 'camelopardalis.com', '2000', 'giraffe counter service');
INSERT INTO service (service_name, url, port, description) VALUES ('sheepShearer', 'ovis.com', '1980', 'sheep shearer service');
INSERT INTO service (service_name, url, port, description) VALUES ('tamanduaFeeder', 'tamandua.com', '1990', 'tamandua feeder service');
INSERT INTO service (service_name, url, port, description) VALUES ('pilosaWatcher', 'pilosa.org', '1960', 'pilosa watcher service');
INSERT INTO service (service_name, url, port, description) VALUES ('cingulataBreeder', 'cingulata.org', '1920', 'cingulata breeder service');
INSERT INTO service (service_name, url, port, description) VALUES ('xenarthraObserver', 'xenarthra.org', '1910', 'xenarthra observer service');

INSERT INTO server (server_name, op_sys, cpu, ram, ip, url, external_ip) VALUES ('ASA', 'Windows Server 2016', 'Xenon E5-2650', 4, '192.168.1.1', '10.1.1.250',  true);
INSERT INTO server (server_name, op_sys, cpu, ram, ip, url, external_ip) VALUES ('AxialImg', 'Windows Server 2008', 'FX-8320', 16, '192.168.1.2', '10.1.1.61', false);
INSERT INTO server (server_name, op_sys, cpu, ram, ip, url, external_ip) VALUES ('CCHBC2', 'Windows Server 2018', 'Xenon E5-2650', 8, '192.168.1.3', '10.1.1.43', false);
INSERT INTO server (server_name, op_sys, cpu, ram, ip, url, external_ip) VALUES ('Corp01', 'Windows Server 2018', 'FX-8350', 16, '192.168.1.4', '10.1.1.50', true);

INSERT INTO server_service (server_id, service_id) VALUES (1,1);
INSERT INTO server_service (server_id, service_id) VALUES (1,2);
INSERT INTO server_service (server_id, service_id) VALUES (2,3);
INSERT INTO server_service (server_id, service_id) VALUES (2,4);
INSERT INTO server_service (server_id, service_id) VALUES (3,5);
INSERT INTO server_service (server_id, service_id) VALUES (3,6);
INSERT INTO server_service (server_id, service_id) VALUES (4,7);
INSERT INTO server_service (server_id, service_id) VALUES (4,8);

INSERT INTO reserved (port_number) VALUES ('8080');
INSERT INTO reserved (port_number) VALUES ('4554');
INSERT INTO reserved (port_number) VALUES ('23365');
INSERT INTO reserved (port_number) VALUES ('48891');
