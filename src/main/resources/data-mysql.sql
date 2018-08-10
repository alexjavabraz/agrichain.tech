
INSERT INTO `fch`.`contact_type` (`nm_contact_type`) 
SELECT * FROM (SELECT 'Phone') AS tmp
WHERE NOT EXISTS (
    SELECT nm_contact_type FROM `fch`.`contact_type` WHERE nm_contact_type = 'Phone'
) LIMIT 1;


INSERT INTO `fch`.`contact_type` (`nm_contact_type`) 
SELECT * FROM (SELECT 'Mobile Phone') AS tmp
WHERE NOT EXISTS (
    SELECT nm_contact_type FROM `fch`.`contact_type` WHERE nm_contact_type = 'Mobile Phone'
) LIMIT 1;


INSERT INTO `fch`.`contact_type` (`nm_contact_type`) 
SELECT * FROM (SELECT 'E-mail') AS tmp
WHERE NOT EXISTS (
    SELECT nm_contact_type FROM `fch`.`contact_type` WHERE nm_contact_type = 'E-mail'
) LIMIT 1;


INSERT INTO `fch`.`contact_type` (`nm_contact_type`) 
SELECT * FROM (SELECT 'Address') AS tmp
WHERE NOT EXISTS (
    SELECT nm_contact_type FROM `fch`.`contact_type` WHERE nm_contact_type = 'Address'
) LIMIT 1;

INSERT INTO `fch`.`contact_type` (`nm_contact_type`) 
SELECT * FROM (SELECT 'Telegram') AS tmp
WHERE NOT EXISTS (
    SELECT nm_contact_type FROM `fch`.`contact_type` WHERE nm_contact_type = 'Telegram'
) LIMIT 1;



insert into fch.contact_info(contact_value, id_contact_type) values
('Contato', 1);

insert into fch.user(email_user, nm_user, password, id_contact_info)
SELECT * FROM (SELECT 'admin1@admin.com', 'admin', '$2a$11$Ul0Amx0jhOS10vIZDJIB3uCyiPDsJ.diIYX9a0ceS7mk18rFH5RTm', 1) AS tmp
WHERE NOT EXISTS (
    SELECT email_user FROM `fch`.`user` WHERE email_user = 'admin1@admin.com'
) LIMIT 1;


commit;
