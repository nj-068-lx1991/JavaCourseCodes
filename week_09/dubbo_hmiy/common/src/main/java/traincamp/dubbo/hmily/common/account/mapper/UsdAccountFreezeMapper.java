package traincamp.dubbo.hmily.common.account.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import traincamp.dubbo.hmily.common.account.entity.AccountChangeDTO;
import traincamp.dubbo.hmily.common.account.entity.AccountFreeze;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Mapper
public interface UsdAccountFreezeMapper {

    /**
     * 新增用户
     * @param accountFreeze 对象
     * @return int
     */
    @Insert(" insert into `t_usd_freeze` (id,user_id,account_id,amount,exchange_id,created) " +
            " values ( #{id},#{userId},#{accountId},#{amount},#{exchangeId},#{created})")
    int save(AccountFreeze accountFreeze);

    /**
     * 删除用户
     * @param accountFreeze 对象
     * @return int
     */
    @Delete("delete from `t_usd_freeze` where id = #{id} and user_id= #{userId}")
    int deleteById(AccountFreeze accountFreeze);

    /**
     * 删除用户
     * @param accountChangeDTO 对象
     * @return int
     */
    @Delete("delete from `t_usd_freeze` where exchange_id = #{exchangeId} and user_id= #{userId}")
    int deleteByChangeDTO(AccountChangeDTO accountChangeDTO);
}
