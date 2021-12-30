package traincamp.dubbo.exchange.service;

import traincamp.dubbo.hmily.common.exchange.entity.ExchangeTradeDO;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface ExchangeService {

    /**
     * 汇率交换1
     * @param userId 用户id
     * @param amount 金额
     * @return traincamp.dubbo.hmily.common.exchange.entity.ExchangeTradeDO
     */
    ExchangeTradeDO exchangeCny2Usd(Long userId, Long amount);

    /**
     * 汇率交换2
     * @param userId 用户id
     * @param amount 金额
     * @return traincamp.dubbo.hmily.common.exchange.entity.ExchangeTradeDO
     */
    ExchangeTradeDO exchangeUsd2Cny(Long userId, Long amount);
}
