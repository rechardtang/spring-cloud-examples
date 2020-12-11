CREATE TABLE `hc_question_reply_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `reply_text` varchar(500) NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_wheel_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',

  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_reply_wheel_time` (`reply_wheel_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='回复明细记录';


SELECT count(*), date_format(ctime, '%Y-%m') AS `日期`
FROM hc_question_reply_record
GROUP BY date_format(ctime, '%Y-%m');