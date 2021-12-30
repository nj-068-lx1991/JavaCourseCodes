package traincamp.dubbo.hmily.common.exchange.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import traincamp.dubbo.hmily.common.exchange.entity.ExchangeTradeDO;

/**
 * 往来交易
 *
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Mapper
public interface ExchangeMapper {

    /**
     * 新增交易记录
     * @param exchangeTradeDO 对象
     * @return int
     */
    @Insert("insert into `t_exchange` (id,user_id,out_account_id,out_currency_type,out_amount," +
            "in_account_id,in_currency_type,in_amount,status,created)" +
            " values ( #{id},#{userId},#{outAccountId},#{outCurrencyType},#{outAmount}," +
            "#{inAccountId},#{inCurrencyType},#{inAmount},#{status},now())")
    int save(ExchangeTradeDO exchangeTradeDO);

    /**
     * 修改交易记录
     * @param exchangeTradeDO 对象
     * @return int
     */
    @Update("update `t_exchange` set status=1, updated = now() where id=#{id} and status=0")
    int updatePrepareStatus(ExchangeTradeDO exchangeTradeDO);

    /**
     * 修改交易记录
     * @param exchangeTradeDO 对象
     * @return int
     */
    @Update("update `t_exchange` set status=2, updated = now() where id=#{id} and status=1")
    int updateSuccessStatus(ExchangeTradeDO exchangeTradeDO);

    /**
     * 修改交易记录
     * @param exchangeTradeDO 对象
     * @return int
     */
    @Update("update `t_exchange` set status=3, updated = now() where id=#{id} and status=1")
    int updateFailStatus(ExchangeTradeDO exchangeTradeDO);
}
