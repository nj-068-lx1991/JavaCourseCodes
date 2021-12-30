package traincamp.dubbo.hmily.common.account.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Data
public class AccountChangeDTO implements Serializable {
    private static final long serialVersionUID = -1579635746902263733L;

    private Long userId;
    private Long accountId;
    private Long changeAmount;
    private String currencyType;
    private Long exchangeId;
}
