/**
 * @FileName: SqlInterceptor
 * @Author: zzc
 * @Date: 2020年01月23日 13:15:26
 * @Version V1.0.0
 */

package com.freezer.intercepter;

import freezer.dto.Log;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class}),
})
public class SqlInterceptor implements Interceptor {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Value("${rabbitmq.queue-log}")
    private String queue;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing-key-log}")
    private String routingKey;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement statement = (MappedStatement) objects[0];
        BoundSql boundSql = statement.getSqlSource().getBoundSql(objects[1]);
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        long beforeExcuteTime = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long afterExcuteTime = System.currentTimeMillis();
        Log log = new Log.Builder().datetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").
                format(new Date())).level("INFO").className(statement.getId().substring(0, statement.getId().
                lastIndexOf("."))).methodName(statement.getId().substring(statement.getId().lastIndexOf(".")
                + 1)).message("[sql] " + sql + System.getProperty("line.separator") + "[parameter] " + parameterObject
                + System.getProperty("line.separator") + "cost time: " + (afterExcuteTime - beforeExcuteTime) + "ms").build();
        amqpTemplate.convertAndSend(exchange, routingKey, log);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor){
            //如果是Executor（执行增删改查操作），则拦截下来
            return Plugin.wrap(target,this);
        }else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }
}
