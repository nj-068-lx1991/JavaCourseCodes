-- 创建数据库 --
CREATE DATABASE IF NOT EXISTS geek_db;

-- 选择数据库 --
use geek_db;

-- 展示数据库中的表 --
show tables;

-- 查看表结构 -
desc geek_db.customer;

-- 用户表 --
CREATE TABLE IF NOT EXISTS `customer`
(
    `customer_id`        INT AUTO_INCREMENT NOT NULL COMMENT '用户ID',
    `customer_name`      VARCHAR(20)        NOT NULL COMMENT '用户姓名',
    `identity_card_type` TINYINT            NOT NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照',
    `identity_card_no`   VARCHAR(20) COMMENT '证件号码',
    `mobile_phone`       INT UNSIGNED COMMENT '手机号',
    `customer_email`     VARCHAR(50) COMMENT '邮箱',
    `gender`             CHAR(1) COMMENT '性别',
    `birthday`           DATETIME COMMENT '生日',
    `zip`                SMALLINT NOT NULL COMMENT '邮编',
    `province_code`      VARCHAR(100) NOT NULL COMMENT '行政区域省编码',
    `city_code`          VARCHAR(100) NOT NULL COMMENT '行政区域市编码',
    `county_code`        VARCHAR(100) NOT NULL COMMENT '行政区域县编码',
    `street_code`        VARCHAR(200) NOT NULL COMMENT '行政区划街道编码',
    `is_default`         TINYINT NOT NULL COMMENT '是否默认地址',
    `register_time`      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '注册时间',
    `modified_time`      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';



-- 行政区域表
CREATE TABLE IF NOT EXISTS `region`
(
    `id`                 INT AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
    `province_code`      VARCHAR(100) NOT NULL COMMENT '行政区域省编码',
    `province_name`      VARCHAR(255) NOT NULL COMMENT '行政区域省名称',
    `city_code`          VARCHAR(100) NOT NULL COMMENT '行政区域市编码',
    `city_name`          VARCHAR(100) NOT NULL COMMENT '行政区域市名称',
    `county_code`        VARCHAR(100) NOT NULL COMMENT '行政区域县编码',
    `county_name`        VARCHAR(100) NOT NULL COMMENT '行政区域县名称',
    `street_code`        VARCHAR(200) NOT NULL COMMENT '行政区划街道编码',
    `street_name`        VARCHAR(200) NOT NULL COMMENT '行政区划街道名称',
    `create_time`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
    `update_time`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '行政区域表';



-- 商品表 --
CREATE TABLE IF NOT EXISTS `product`
(
    `product_id`        INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品ID',
    `product_code`      CHAR(16)                    NOT NULL COMMENT '商品编码',
    `product_name`      VARCHAR(100)                NOT NULL COMMENT '商品名称',
    `bar_code`          VARCHAR(50)                 NOT NULL COMMENT '国条码',
    `brand_id`          INT                 NOT NULL COMMENT '品牌ID',
    `category1_id`      INT                 NOT NULL COMMENT '一级分类ID',
    `category2_id`      INT                 NOT NULL COMMENT '二级分类ID',
    `category3_id`      INT                 NOT NULL COMMENT '三级分类ID',
    `supplier_id`       INT UNSIGNED                NOT NULL COMMENT '商品的供应商ID',
    `price`             DECIMAL(8, 2)               NOT NULL COMMENT '商品销售价格',
    `average_cost`      DECIMAL(18, 2)              NOT NULL COMMENT '商品加权平均成本',
    `publish_status`    TINYINT                     NOT NULL DEFAULT 0 COMMENT '上下架状态：0下架1上架',
    `audit_status`      TINYINT                     NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核，1已审核',
    `weight`            FLOAT COMMENT '商品重量',
    `length`            FLOAT COMMENT '商品长度',
    `height`            FLOAT COMMENT '商品高度',
    `width`             FLOAT COMMENT '商品宽度',
    `color_type`        ENUM ('红','黄','蓝','黑'),
    `production_date`   DATETIME                    NOT NULL COMMENT '生产日期',
    `shelf_life`        INT                         NOT NULL COMMENT '商品有效期',
    `describe`          TEXT                        NOT NULL COMMENT '商品描述',
    `create_time`       TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品录入时间',
    `update_time`       TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (product_id)
) ENGINE=InnoDB DEFAULT CHARSET = utf8 COMMENT '商品表';


-- 品牌表 --
CREATE TABLE IF NOT EXISTS `brand`
(
    `brand_id`     INT AUTO_INCREMENT NOT NULL COMMENT '品牌ID',
    `brand_code`   VARCHAR(50)        NOT NULL COMMENT '品牌编码',
    `brand_name`   VARCHAR(50)        NOT NULL COMMENT '品牌名称',
    `telephone`    VARCHAR(50)        NOT NULL COMMENT '联系电话',
    `brand_desc`   VARCHAR(150) COMMENT '品牌描述',
    `brand_status` TINYINT            NOT NULL DEFAULT 0 COMMENT '品牌状态,0禁用,1启用',
    `brand_order`  TINYINT            NOT NULL DEFAULT 0 COMMENT '排序',
    `update_time`  TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`brand_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '品牌表';



-- 商品分类表 --
CREATE TABLE IF NOT EXISTS `product_category`
(
    `category_id`     INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '分类ID',
    `category_code`   VARCHAR(10)                 NOT NULL COMMENT '分类编码',
    `category_name`   VARCHAR(10)                 NOT NULL COMMENT '分类名称',
    `parent_id`       SMALLINT                    NOT NULL DEFAULT 0 COMMENT '父分类ID',
    `category_level`  TINYINT                     NOT NULL DEFAULT 1 COMMENT '分类层级',
    `category_status` TINYINT                     NOT NULL DEFAULT 1 COMMENT '分类状态',
    `update_time`     TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`category_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '商品分类表';



-- 供应商 --
CREATE TABLE IF NOT EXISTS `supplier`
(
    `supplier_id`     INT AUTO_INCREMENT NOT NULL COMMENT '供应商ID',
    `supplier_code`   CHAR(8)            NOT NULL COMMENT '供应商编码',
    `supplier_name`   CHAR(50)           NOT NULL COMMENT '供应商名称',
    `supplier_type`   TINYINT            NOT NULL COMMENT '供应商类型：1.自营，2.平台',
    `link_man`        VARCHAR(10)        NOT NULL COMMENT '供应商联系人',
    `telephone`       VARCHAR(50)        NOT NULL COMMENT '联系电话',
    `bank_name`       VARCHAR(50)        NOT NULL COMMENT '供应商开户银行名称',
    `bank_account`    VARCHAR(50)        NOT NULL COMMENT '银行账号',
    `address`         VARCHAR(200)       NOT NULL COMMENT '供应商地址',
    `supplier_status` TINYINT            NOT NULL DEFAULT 0 COMMENT '状态：0禁止，1启用',
    `update_time`     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`supplier_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '供应商表';



-- 订单表 --
CREATE TABLE IF NOT EXISTS `order`
(
    `order_id`           INT            NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `order_code`         BIGINT         NOT NULL COMMENT '订单编号',
    `customer_id`        INT            NOT NULL COMMENT '下单人ID',
    `shipping_user`      VARCHAR(10)    NOT NULL COMMENT '收货人姓名',
    `province`           SMALLINT       NOT NULL COMMENT '省',
    `city`               SMALLINT       NOT NULL COMMENT '市',
    `district`           SMALLINT       NOT NULL COMMENT '区',
    `address`            VARCHAR(100)   NOT NULL COMMENT '地址',
    `payment_method`     TINYINT        NOT NULL COMMENT '支付方式：1现金，2余额，3网银，4支付宝，5微信',
    `order_amount`       DECIMAL(19, 2) NOT NULL COMMENT '订单金额',
    `district_amount`    DECIMAL(19, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',
    `shipping_amount`    DECIMAL(19, 2) NOT NULL DEFAULT 0.00 COMMENT '运费金额',
    `payment_amount`     DECIMAL(19, 2) NOT NULL DEFAULT 0.00 COMMENT '支付金额',
    `shipping_comp_name` VARCHAR(10) COMMENT '快递公司名称',
    `shipping_sn`        VARCHAR(50) COMMENT '快递单号',
    `shipping_time`      DATETIME COMMENT '发货时间',
    `pay_time`           DATETIME COMMENT '支付时间',
    `receive_time`       DATETIME COMMENT '收货时间',
    `order_status`       TINYINT        NOT NULL DEFAULT 0 COMMENT '订单状态',
    `order_point`        INT            NOT NULL DEFAULT 0 COMMENT '订单积分',
    `invoice_time`       VARCHAR(100) COMMENT '发票抬头',
    `create_time`        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `update_time`        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '订单表';



-- 订单详情表 --
CREATE TABLE IF NOT EXISTS `order_details`
(
    `order_detail_id`  INT            NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
    `order_id`         INT            NOT NULL COMMENT '订单表ID',
    `product_id`       INT            NOT NULL COMMENT '订单商品ID',
    `product_name`     VARCHAR(50)    NOT NULL COMMENT '商品名称',
    `product_quantity` INT            NOT NULL DEFAULT 1 COMMENT '购买商品数量',
    `product_price`    DECIMAL(19, 2) NOT NULL COMMENT '购买商品单价',
    `average_cost`     DECIMAL(19, 2) NOT NULL COMMENT '平均成本价格',
    `weight`           FLOAT COMMENT '商品重量',
    `fee_money`        DECIMAL(19, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠分摊金额',
    `update_time`      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`order_detail_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '订单详情表';


-- 查看对应建表语句 --
# show create table geek_db.customer;
# show create table geek_db.region;
# show create table geek_db.product;
# show create table geek_db.brand;
# show create table geek_db.supplier;
# show create table geek_db.product_category;
# show create table geek_db.order;
# show create table geek_db.order_details;

-- 删除对应表表 --
# drop table if exists `geek_db.customer`;
# drop table if exists `geek_db.region`;
# drop table if exists `geek_db.product`;
# drop table if exists `geek_db.brand`;
# drop table if exists `geek_db.supplier`;
# drop table if exists `geek_db.product_category`;
# drop table if exists `geek_db.order`;
# drop table if exists `geek_db.order_details`;