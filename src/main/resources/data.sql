CREATE TABLE `project_config` (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `system_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微服务名',
                                  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数名（system_id + name  要唯一）',
                                  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '参数值',
                                  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '配置描述',
                                  `need_check` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要审核',
                                  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_name` (`name`) USING BTREE COMMENT '参数名索引'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;