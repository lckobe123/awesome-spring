package com.ternence.spring.skills.mybatis.mapper;

import com.ternence.spring.skills.mybatis.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * 账户操作Mapper
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/3 21:20
 */
public interface AccountMapper extends BaseMapper<AccountEntity> {

    String getNameById(@Param("id") String id);

}
