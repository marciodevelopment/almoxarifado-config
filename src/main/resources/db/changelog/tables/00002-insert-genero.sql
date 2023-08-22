-- liquibase formatted sql
-- changeset author:Marcio Taveira
-- Inserir um registro
-- Insert a record using the sequence
INSERT INTO Genero (cdGenero, dsGenero, idSituacao, sgGenero)
VALUES (nextval('Genero_sq'), 'MASCULINO', 1, 'M');

-- Insert another record using the sequence
INSERT INTO Genero (cdGenero, dsGenero, idSituacao, sgGenero)
VALUES (nextval('Genero_sq'), 'FEMININO', 1, 'F');

-- Insert one more record using the sequence
INSERT INTO Genero (cdGenero, dsGenero, idSituacao, sgGenero)
VALUES (nextval('Genero_sq'), 'TRANS', 1, 'TR');
