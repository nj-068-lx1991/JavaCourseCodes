package traincamp.dubbo.hmily.common.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import traincamp.dubbo.hmily.common.account.entity.Account;
import traincamp.dubbo.hmily.common.account.entity.AccountChangeDTO;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Mapper
public interface CnyAccountMapper {

    /**
     * 修改用户信息
     * @param accountChangeDTO 对象
     * @return int
     */
    @Update("update t_cny_account set balance = balance - #{changeAmount}, updated = now() " +
            " where user_id = #{userId} and  balance - #{changeAmount} >= 0 ")
    int decreaseBalance(AccountChangeDTO accountChangeDTO);

    /**
     * 修改永辉信息
     * @param accountChangeDTO 对象
     * @return int
     */
    @Update("update t_cny_account set balance = balance + #{changeAmount}, updated = now() " +
            " where user_id = #{userId} and  #{changeAmount} > 0 ")
    int increaseBalance(AccountChangeDTO accountChangeDTO);

    /**
     * 根据id查询用户信息
     * @param userId 用户id
     * @return traincamp.dubbo.hmily.common.account.entity.Account
     */
    @Select("select id,user_id,balance, created, updated from t_cny_account where user_id =#{userId} limit 1")
    Account findByUserId(Long userId);

    /**
     * 根据id查询用户信息
     * @param id 主键
     * @return traincamp.dubbo.hmily.common.account.entity.Account
     */
    @Select("select id,user_id,balance, created, updated from t_cny_account where id =#{id} limit 1")
    Account findById(Long id);
}
