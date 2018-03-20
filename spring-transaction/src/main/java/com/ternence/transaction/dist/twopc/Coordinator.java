package com.ternence.transaction.dist.twopc;

/**
 * 两阶段提交中的协调器
 *
 * @author Ternence
 * @version 1.0
 * @since 1.0
 */
public interface Coordinator {

    boolean prepare();

    boolean commit();

    void rollback();

}
