# 作业详情
### 作业要求
*   1、（挑战☆）基于其他各类场景，设计并在示例代码中实现简单demo：
       
        1）实现分数排名或者排行榜 
        2）实现全局ID生成
        3）基于Bitmap实现id去重
        4）基于HLL实现点击量计数
        5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务 
    
### 1）实现分数排名或者排行榜
    使用Redis的 Sorted Set 来进行实现。简单使用随机数生成0-9的十个用户，
    每次分数加一，打印前三的用户

    public class RankingList {
    
        public static void main(String[] args) throws InterruptedException {
            try (JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
                String key = "rankingList";
                jedis.del(key);
    
                for (int i = 0; i < 1000; i++) {
                    String user = "user:" + new Random(System.currentTimeMillis()).nextInt(10);
                    jedis.zincrby(key, 1, user);
    
                    Set<String> users = jedis.zrevrange(key, 0, 2);
                    System.out.print("Top 3:");
                    for (String item: users) {
                        System.out.printf("%s -- %f  ", item, jedis.zscore(key, item));
                    }
                    System.out.println();
                }
            }
        }
    }

### 2）实现全局ID生成

    使用Redis自增实现，生成思路：日期（yyyyMMddHHmmss）+redis原子生成的数字（不足6位前面补0）

    public class GenerateId {
        private enum EnumSingleton {
            /**
             * 懒汉枚举单例
             */
            INSTANCE;
            private GenerateId instance;
    
            EnumSingleton(){
                instance = new GenerateId();
            }
            public GenerateId getSingleton(){
                return instance;
            }
        }
    
        public static GenerateId getInstance(){
            return EnumSingleton.INSTANCE.getSingleton();
        }
    
        private JedisPool jedisPool = new JedisPool();
    
        private String generateOrderId() {
            LocalDateTime now = LocalDateTime.now();
            String orderIdPrefix = getOrderIdPrefix(now);
            return orderIdPrefix + String.format("%1$06d", generate(orderIdPrefix, 5));
        }
    
        private String getOrderIdPrefix(LocalDateTime now) {
            return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }
    
        private long generate(String key, int timeout) {
            try(Jedis jedis = jedisPool.getResource()) {
                Long id = jedis.incr(key);
                if (timeout > 0) {
                    jedis.expire(key, timeout);
                }
                return id;
            }
        }
    
        public static void main(String[] args) {
            for(int i = 0; i < 10; i++) {
                System.out.println(GenerateId.getInstance().generateOrderId());
            }
        }
    }

### 3）基于Bitmap实现id去重

    这里简单的使用redis的bitmap进行操作，写了简单的示例，前面直接植入1000个id，后面判断id是否重复
    
    public class BitMap {
    
        public static void main(String[] args) {
            try(JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
                String key = "deduplication";
                for (int i = 0; i < 1000; i++) {
                    jedis.setbit(key, i, true);
                }
    
                assert jedis.getbit(key, 100);
                assert !jedis.getbit(key, 10000);
            }
        }
    }

### 4）基于HLL实现点击量计数

    使用redis内置的数据结构，随机插入点击的用户id数据，最后打印统计结果
    
    public class HyperLogLog {
    
        public static void main(String[] args) {
            try(JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
                String key = "HyperLogLog";
                jedis.del(key);
    
                for(int i = 0; i < 10000; i++) {
                    long userId = new Random(System.currentTimeMillis()).nextInt(100000000);
                    jedis.pfadd(key, String.valueOf(userId));
                }
    
                System.out.println("click amount: " + jedis.pfcount(key));
            }
        }
    }

### 5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务
    
    使用lua脚本，内容如下，这里将 6379 的redis作为A
    
    local redis = require "resty.redis"
    
    -- 连接第一个redis数据库6379
    local red1 = redis:new()
    red1:set_timeout(1000)
    local ok, err = red1:connect("127.0.0.1", 6379)
    if not ok then
    ngx.say("failed to connect: ", err)
    return
    end
    
    -- 连接第二个redis数据库6380
    local red2 = redis:new()
    red2:set_timeout(1000)
    local ok, err = red2:connect("127.0.0.1", 6380)
    if not ok then
    ngx.say("failed to connect: ", err)
    return
    end

    -- 先扣人民币账号 成功后扣美元账户
    -- 先扣人民币账号失败 返回0即可
    -- 扣美元账户失败 需要补偿人民币账号

    if redis1.call("DECR", "CNYAccount") == 1 then
    if redis2.call("DECR", "USAccount") == 1 then
    return 1
    else
    redis1.call("INCR", "CNYAccount")
    return 0
    else
    return 0