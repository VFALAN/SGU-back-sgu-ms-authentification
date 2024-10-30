create table cmodule
(
    id_module   int auto_increment
        primary key,
    active      bit          null,
    date_create datetime(6)  null,
    date_delete datetime(6)  null,
    date_update datetime(6)  null,
    path        varchar(255) null,
    name        varchar(255) null
);

create table ctype_file
(
    id_type_file int auto_increment
        primary key,
    active       bit          null,
    date_create  datetime(6)  null,
    date_delete  datetime(6)  null,
    date_update  datetime(6)  null,
    name         varchar(255) null
);

create table state
(
    id_state int auto_increment
        primary key,
    name     varchar(255) null
);

create table municipality
(
    id_municipality int auto_increment
        primary key,
    name            varchar(255) null,
    state           int          null,
    constraint FKguw77kwn6kno7xnaajjps4ix6
        foreign key (state) references state (id_state)
);

create table locality
(
    id_locality     int auto_increment
        primary key,
    cp              varchar(255) null,
    name            varchar(255) null,
    id_municipality int          null,
    constraint FK8wc197tffd0ewaufqliekifdk
        foreign key (id_municipality) references municipality (id_municipality)
);

create table t_user
(
    id_user  bigint       not null
        primary key,
    email    varchar(255) null,
    password varchar(255) null
);

create table t_users
(
    id_user  bigint       not null
        primary key,
    email    varchar(255) null,
    password varchar(255) null
);

create table taddress
(
    id_address  int auto_increment
        primary key,
    active      bit          null,
    date_create datetime(6)  null,
    date_delete datetime(6)  null,
    date_update datetime(6)  null,
    description varchar(255) null,
    id_location int          null,
    street      varchar(255) null
);

create table tfile
(
    id_file      int auto_increment
        primary key,
    active       bit          null,
    date_create  datetime(6)  null,
    date_delete  datetime(6)  null,
    date_update  datetime(6)  null,
    file_name    varchar(255) null,
    path         varchar(255) null,
    user_owner   int          null,
    id_type_file int          null,
    bucket       varchar(255) null,
    constraint FK1bf9axl0g0d25pypd1yur0dd6
        foreign key (id_type_file) references ctype_file (id_type_file)
);

create table tidentity
(
    id_identity int          not null
        primary key,
    active      bit          null,
    date_create datetime(6)  null,
    date_delete datetime(6)  null,
    date_update datetime(6)  null,
    app_key     varchar(255) null,
    app_name    varchar(255) null,
    constraint UK_ia5le6mmox8vl3qjydv2okwn7
        unique (app_name)
);

create table tprofile
(
    id_profile  int auto_increment
        primary key,
    active      bit          null,
    date_create datetime(6)  null,
    date_delete datetime(6)  null,
    date_update datetime(6)  null,
    key_profile varchar(255) null,
    name        varchar(255) null
);

create table tmenu_section
(
    id_menu_section int          not null
        primary key,
    has_path        bit          null,
    name            varchar(255) null,
    path            varchar(255) null,
    id_profile      int          null,
    parent          int          null,
    constraint FKfxbkiuqudsckynq6mn8jmakm4
        foreign key (id_profile) references tprofile (id_profile)
);

create table tmodule_profile
(
    id_module_profile int auto_increment
        primary key,
    active            bit         null,
    date_create       datetime(6) null,
    date_delete       datetime(6) null,
    date_update       datetime(6) null,
    id_module         int         null,
    id_profile        int         null,
    constraint FK5lvwyblqw88st8barxanevkl7
        foreign key (id_profile) references tprofile (id_profile),
    constraint FKlfbsxpannjvlsv0go2owphl06
        foreign key (id_module) references cmodule (id_module)
);

create table tsub_menu_section
(
    id_sub_menu     int          not null
        primary key,
    icon            varchar(255) null,
    path            varchar(255) null,
    id_menu_section int          null,
    constraint FK61bajq0plrkm7s9mxpgskxo28
        foreign key (id_menu_section) references tmenu_section (id_menu_section)
);

create table tmenu_item
(
    id_menu_item    int auto_increment
        primary key,
    icon            varchar(255) null,
    name            varchar(255) null,
    path            varchar(255) null,
    id_menu_section int          null,
    id_sub_menu     int          null,
    constraint FK21dnq157s7fum5ptjqqkakuvf
        foreign key (id_menu_section) references tmenu_section (id_menu_section),
    constraint FKej7fa39tlsr561hvlc5vct7e1
        foreign key (id_sub_menu) references tsub_menu_section (id_sub_menu)
);

create table tuser
(
    id_user      int auto_increment
        primary key,
    active       bit          null,
    date_create  datetime(6)  null,
    date_delete  datetime(6)  null,
    date_update  datetime(6)  null,
    age          int          null,
    birth_date   datetime(6)  null,
    email        varchar(255) null,
    last_name    varchar(255) null,
    middle_name  varchar(255) null,
    name         varchar(255) not null,
    phone_number varchar(255) null,
    user_name    varchar(255) null,
    profile      int          null,
    address      int          null,
    constraint UK_jo607r2k02ado3uoo3fhjcmv
        unique (address),
    constraint FKrplbbvbc8axbg3tav252cgudp
        foreign key (address) references taddress (id_address),
    constraint FKwwmlq2hi9h580vv2q8p3b2t
        foreign key (profile) references tprofile (id_profile)
);

create table tlog_pass
(
    id_log_pass int auto_increment
        primary key,
    active      bit          null,
    date_create datetime(6)  null,
    date_delete datetime(6)  null,
    date_update datetime(6)  null,
    expired     bit          null,
    password    varchar(255) null,
    id_user     int          null,
    constraint FKh6un03x6y30i2oa2jifta9jkl
        foreign key (id_user) references tuser (id_user)
);

create
    definer = root@localhost procedure search_username(IN username varchar(100), OUT count_of_users int)
begin

    declare exit handler for sqlexception

        begin

            rollback;

            resignal;

        end;

    declare exit handler for sqlwarning

        begin

            rollback;

            resignal;

        end;

    start transaction;

    select count(user_name) into count_of_users from tuser where user_name = username;

    commit;

end;





