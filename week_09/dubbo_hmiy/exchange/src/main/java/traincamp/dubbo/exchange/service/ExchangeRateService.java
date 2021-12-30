package traincamp.dubbo.exchange.service;

import java.math.BigDecimal;

/**
 * 交换汇率
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface ExchangeRateService {

    /**
     * 获取汇率
     * @param outCurrencyType 汇出类型
     * @param inCurrencyType 当前汇入类型
     * @return java.math.BigDecimal
     */
    BigDecimal getExchangeRate(String outCurrencyType, String inCurrencyType);
}
