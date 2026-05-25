insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq') ,'giovanile', 2025, 'torneo di calcio giovanile');
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'),'amatoriale', 2026, 'torneo di calcio amatoriale');
insert into torneo(id, nome, anno, descrizione) values(nextval('torneo_seq'), 'infantile', 2024, 'torneo di calcio infantile');
insert into squadra(id, nome, città, anno_fondazione) values(nextval('squadra_seq'), 'Roma', 'Roma', 1927);
insert into squadra(id, nome, città, anno_fondazione) values(nextval('squadra_seq'), 'Lazio', 'Roma', 1900);
insert into squadra(id, nome, città, anno_fondazione) values(nextval('squadra_seq'), 'Inter', 'Milano', 1908);