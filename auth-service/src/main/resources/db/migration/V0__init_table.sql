create table if not exists member_authentication_info (
    id UUID primary key not null,
    nickname varchar(255) not null,
    password varchar(255) not null,
    created_at timestamp not null default now(),
    last_login_at timestamp default null
);

insert into member_authentication_info(
    id, nickname, password, created_at, last_login_at
) values (
    '01917f7a-3d9f-75aa-be6b-ef76d583653b', 'admin', '$2b$12$W73CKCBO927PQ/LOzVlX2uKwxJQsbHRbZwt7Y1f6miKfuAOThQrzG', now(), null
);