-- # 插入数据到订单表中，关闭自动提交，批量插入
DROP PROCEDURE IF EXISTS order_initData;
DELIMITER $
CREATE PROCEDURE order_initData()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i<=1000000 DO
            insert into geek_db.order (order_code, customer_id, shipping_user, province, city, district, address, payment_method, order_amount, district_amount, payment_amount, pay_time, order_status)
            VALUES (CEILING(rand()*100), 10001001, '李四', '32', '3201', '101', '沿江街道威尼斯水城十六街区15栋一单元333室', 1, 90.234, 5, 85.234, current_timestamp(), 2);
            SET i = i+1;
        END WHILE;
    commit;
END $
CALL order_initData();

-- # 插入数据到订单表中，自动提交，一条一条的插入
DROP PROCEDURE IF EXISTS order_initData_one;
DELIMITER $
CREATE PROCEDURE order_initData_one()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=1000000 DO
            insert into geek_db.order (order_code, customer_id, shipping_user, province, city, district, address, payment_method, order_amount, district_amount, payment_amount, pay_time, order_status)
            VALUES (CEILING(rand()*100), 10001001, '李四', '32', '3201', '101', '沿江街道威尼斯水城十六街区15栋一单元333室', 1, 90.234, 5, 85.234, current_timestamp(), 2);
            SET i = i+1;
        END WHILE;
END $
CALL order_initData_one();