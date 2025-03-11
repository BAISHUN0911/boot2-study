package com.baishun.mystudy.demotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @description: Service层使用Spring事务
 * @Author BAISHUN
 * @Date 2024/9/2 21:53
 */
@Service
public class TransactionDemoServiceImpl {

//    @Autowired
//    private PlatformTransactionManager transactionManager;

    @Autowired(required = false)
    private TransactionTemplate transactionTemplate;

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ,
            timeout = 60,
            readOnly = false,
            rollbackFor = RuntimeException.class
    )
    public void testTransactionByAnnotation() {

    }

    public static void main(String[] args) {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        System.out.println(defaultTransactionDefinition);
    }

    public void createUser() {
        transactionTemplate.execute(status -> {
            try {
                // 业务代码 可模拟异常
                return null;  // 事务成功
            }catch (Exception e) {
                status.setRollbackOnly(); // 标记事务回滚
                throw e;    // 抛出异常
            }
        });
    }

    // 执行事务不需要返回值的情况
    public void testTransactionByTemplate() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    // ....  业务代码
                } catch (Exception e){
                    //回滚
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }


//    public void testTransaction() {
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        try {
//
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            transactionManager.rollback(status);
//        }
//    }


}
