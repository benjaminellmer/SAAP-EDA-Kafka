create table equipment_status
(
    id                   bigint generated always as identity
        constraint equipment_status_pk
            primary key,
    producing            bool,
    equipment_id         text,
    job_percent_finished integer,
    timestamp timestamp
);

