/**
 * @FileName: service
 * @Author: zzc
 * @Date: 2020年01月23日 15:09:12
 * @Version V1.0.0
 */

package com.freezer.service;

import freezer.dto.Log;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class logService {
    @Value("${log-path}")
    private String logPath;

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("${rabbitmq.queue-log}"),
            exchange = @Exchange("${rabbitmq.exchange}"),
            key = "${rabbitmq.routing-key-log}"
    ))
    public void recordLog(Log log) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.getDatetime());
        sb.append("  ");
        sb.append(log.getLevel());
        sb.append("  ");
        sb.append(log.getClassName());
        sb.append("  ");
        sb.append(log.getMethodName());
        sb.append(System.getProperty("line.separator"));
        sb.append(log.getMessage());
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(logPath), true), StandardCharsets.UTF_8));
            bw.write(sb.toString());
            bw.newLine();
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
