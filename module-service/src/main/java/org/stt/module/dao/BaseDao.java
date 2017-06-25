package org.stt.module.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

/**
 * 提供dao共有操作
 * @author wangchao
 *
 */
public class BaseDao {

    @Resource(name = "sqlSession1")
    private SqlSessionTemplate session1;

    @Resource(name = "sqlSession2")
    private SqlSessionTemplate session2;

    /**
     * 传入1获取sqlSession1 ，传入2 获得 sqlSession2<br>
     * 不传获得sqlSession1
     * @param type
     * @return
     */
    public SqlSessionTemplate getSession(final Integer type) {

        if (null == type || type.intValue() == 1) {
            return session1;
        } else {
            return session2;

        }
    }

}
