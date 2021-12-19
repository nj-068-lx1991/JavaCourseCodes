作业一
读写分离 - 动态 切换数据源版本 1.0
    完成情况说明：

配置了三个数据库：1主2从
MySQL docker配置记录
service中的insert注入主库，query注入从库
使用注解实现不同数据库源的注入
简单的实现了从库访问的负载均衡
    作业详情：

作业详细代码地址：https://github.com/nj-068-lx1991/JavaCourseCodes/tree/master/week_07/read_write_separation_1.0/src/main/java/com/example/demo/database

作业二
读写分离 - 数据库框架版本 2.0
    简单的直接使用配置文件配置，在测试代码中注入后直接使用SQL进行执行，没有结合ORM

代码地址：https://github.com/nj-068-lx1991/JavaCourseCodes/blob/master/week_07/read_write_separation_2.0/src/main/java/com/example/demo/shardingsphere/raw/jdbc/ShardingMasterSlaveDataSource.java
参考链接
USE SPRING BOOT STARTER
READ-WRITE SPLITTING
ShardingSphere入门实战(1)-Sharding-JDBC使用
作业三
按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
    测试了两种，一个是关闭自动提交，一百万的数据弄好后一起提交；一种是一条一条的提交插入

    通过测试，批量的插入是非常快的；下面的两个测试都写了存储过程，脚本在下方链接：

order_initial.sql

关闭自动提交，一百万的数据弄好后一起提交：completed in 12 s 195 ms

一条一条的提交插入：之前测试过插入10万的，要差一个多小时左右，这里只知道会很久吧，就不测具体数据了