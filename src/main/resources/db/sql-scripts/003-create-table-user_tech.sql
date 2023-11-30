create table if not exists user_tech (
    user_id int not null,
    tech_id int not null,
    foreign key (user_id) references users(id),
    foreign key (tech_id) references tech(id)
)