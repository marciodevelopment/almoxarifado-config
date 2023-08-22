-- liquibase formatted sql
-- changeset author:Marcio Taveira
    create table DOCUMENTOCPF (
        cdDocumentoCpf integer not null,
        idSituacao integer not null,
        nrDocumento varchar(50) not null,
        cdUsuario integer not null,
        primary key (cdDocumentoCpf)
    );
 
    create table Genero (
        cdGenero integer not null,
        dsGenero varchar(255),
        idSituacao integer not null,
        sgGenero varchar(255),
        primary key (cdGenero)
    );
 
    create table USUARIO (
        cdUsuario integer not null,
        idSexo integer not null,
        idSituacao integer not null,
        nmMae varchar(255) not null,
        nmPai varchar(255),
        nmUsuario varchar(255) not null,
        cdGenero integer not null,
        dsEmail varchar(255) NOT NULL unique,
        primary key (cdUsuario)
    );
 
    alter table if exists DOCUMENTOCPF 
       drop constraint if exists UK_9gmdkuqmu44rlll60c43uq80o;
      
    alter table if exists DOCUMENTO_CPF 
       add constraint UK_9gmdkuqmu44rlll60c43uq80o unique (cdUsuario);
 
    alter table if exists USUARIO 
       drop constraint if exists UK_1eosk27wbl6ey2kkbiid2836g;
      
 
    create sequence DocumentoCpf_sq start with 1 increment by 1;
 
    create sequence Genero_sq start with 1 increment by 1;
 
    create sequence Usuario_sq start with 1 increment by 1;
 
    alter table if exists DOCUMENTOCPF 
       add constraint FKo1eic4sccq3i5ik65ac1fng8 
       foreign key (cdUsuario) 
       references USUARIO;
 
    alter table if exists USUARIO 
       add constraint FKp6aot7dally9c0v26w6wls1cf 
       foreign key (cdGenero) 
       references Genero;