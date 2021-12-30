package traincamp.dubbo.hmily.common.account.api;

import org.dromara.hmily.annotation.Hmily;
import traincamp.dubbo.hmily.common.account.entity.AccountChangeDTO;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface CnyAccountService {

    /**
     * 用户信息
     * @param userId 用户id
     * @return java.lang.Long
     */
    Long getAccountId(Long userId);

    /**
     * 用户描述
     * @param accountChange 对象
     * @return boolean
     */
    @Hmily
    boolean accountDecrease(AccountChangeDTO accountChange);

    /**
     * 用户增长
     * @param accountChange 对象
     * @return boolean
     */
    @Hmily
    boolean accountIncrease(AccountChangeDTO accountChange);
}