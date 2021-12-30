package traincamp.dubbo.exchange.constant;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public class ExchangeConstant {

    public final static Long EXCHANGE_WORKER = 1L;
    /**
     * 以下是货币代码，参考http://www.webmasterhome.cn/huilv/huobidaima.asp
     */
    public final static String CURRENCY_CNY = "CNY";
    public final static String CURRENCY_USD = "USD";

    public final static Integer STATUS_CREATE = 0;
    public final static Integer STATUS_PREPARE = 1;
    public final static Integer STATUS_SUCCESS = 2;
    public final static Integer STATUS_FAIL = 3;
}
