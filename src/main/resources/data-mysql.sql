
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


commit;
