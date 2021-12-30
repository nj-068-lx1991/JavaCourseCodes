package traincamp.dubbo.hmily.common.account.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Data
public class AccountFreeze implements Serializable {
    private static final long serialVersionUID = -7783407511280986494L;

    private Long id;

    private Long userId;

    private Long accountId;

    private Long amount;

    private Long exchangeId;

    private Date created;
}
