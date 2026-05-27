create table if not exists users (
    id bigserial primary key,
    name varchar(120) not null,
    email varchar(160) not null unique,
    password varchar(255) not null,
    created_at timestamp not null default now()
);

create table if not exists transactions (
    id bigserial primary key,
    user_id bigint not null references users(id) on delete cascade,
    amount numeric(12,2) not null,
    category varchar(40) not null,
    payment_method varchar(40) not null,
    txn_date date not null,
    note varchar(255),
    created_at timestamp not null default now()
);

create table if not exists budgets (
    id bigserial primary key,
    user_id bigint not null references users(id) on delete cascade,
    category varchar(40) not null,
    budget_month varchar(7) not null,
    limit_amount numeric(12,2) not null,
    created_at timestamp not null default now(),
    unique (user_id, category, budget_month)
);

create table if not exists insights (
    id bigserial primary key,
    user_id bigint not null references users(id) on delete cascade,
    type varchar(40) not null,
    message varchar(255) not null,
    created_at timestamp not null default now()
);
