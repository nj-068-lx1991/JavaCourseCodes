package traincamp.dubbo.exchange.service;

import org.dromara.hmily.annotation.Hmily;
import traincamp.dubbo.hmily.common.exchange.entity.ExchangeTradeDO;

/**
 * 金额兑换
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface ExchangeOperationService {

    /**
     * 汇率兑换
     * @param exchangeTrade 对象
     * @return boolean
     */
    @Hmily
    boolean doExchangeOperation(ExchangeTradeDO exchangeTrade);
}
