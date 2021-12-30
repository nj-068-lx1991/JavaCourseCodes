package traincamp.dubbo.hmily.common.exchange.entity;

import lombok.Data;

import java.util.Date;

/**
 * 往来交易
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Data
public class ExchangeTradeDO {
    private Long id;
    private Long userId;
    private Long outAccountId;
    private String outCurrencyType;
    private Long outAmount;
    private Long inAccountId;
    private String inCurrencyType;
    private Long inAmount;
    private Integer status;
    private Date created;
    private Date updated;
}
