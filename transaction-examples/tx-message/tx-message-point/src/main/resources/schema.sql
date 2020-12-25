

create table `point`
(
    `id`       bigint(16)   not null,
    `user_id`  bigint(16)   not null,
    `order_no` bigint(16)   not null,
    `points`   int(4)       not null,
    `remarks`  varchar(128) not null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_bin;