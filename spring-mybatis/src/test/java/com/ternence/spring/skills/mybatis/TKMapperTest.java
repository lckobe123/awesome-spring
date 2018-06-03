package com.ternence.spring.skills.mybatis;

import com.ternence.spring.skills.mybatis.entity.AccountEntity;
import com.ternence.spring.skills.mybatis.mapper.AccountMapper;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通用mapper功能测试
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/6/3 21:09
 */
@ContextConfiguration({"classpath:spring-tkmapper.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TKMapperTest {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void query() {
        List<AccountEntity> accountEntities = accountMapper.selectAll();
        if (accountEntities != null) {
            for (AccountEntity accountEntity : accountEntities) {
                System.out.println(accountEntity.getName() + ":" + accountEntity.getBalance());
            }
        }
    }

    @Test
    public void getNameById() {
        String name = accountMapper.getNameById("4885dfb6673011e885900242ac110002");
        System.out.println("name:" + name);
    }

    @Test
    public void getNameByIdWithSqlSessionTemplate() {
        DefaultResultHandler defaultResultHandler = new DefaultResultHandler();
        /*指定好扫描到的Mapper就可以了*/
        sqlSessionTemplate.select("com.ternence.spring.skills.mybatis.mapper.AccountMapper.getNameById",
                "4885dfb6673011e885900242ac110002", defaultResultHandler);
        System.out.println("name:" + defaultResultHandler.getResultList());
    }
}
