CREATE TABLE `project_config` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `system_id` varchar(100) NOT NULL,
                                  `name` varchar(255) NOT NULL,
                                  `value` text,
                                  `description` varchar(255) DEFAULT NULL,
                                  `need_check` tinyint(1) NOT NULL DEFAULT '0',
                                  `create_time` datetime DEFAULT NULL,
                                  `update_time` datetime DEFAULT NULL,
                                  `deleted` tinyint(1) NOT NULL DEFAULT '0',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_name` (`name`) USING BTREE COMMENT '参数名索引'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;