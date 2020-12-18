create table `order`
(
    `id`         bigint auto_increment not null,
    `order_code` varchar(32)           not null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_bin;

create table `transaction_log`
(
    `id`          varchar(32) not null,
    `business`    varchar(32) not null,
    `foreign_key` varchar(32) not null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_bin;