/**
 * @FileName: Log
 * @Author: zzc
 * @Date: 2020年01月23日 13:45:59
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;

public class Log implements Serializable {
    private String datetime;
    private String level;
    private String className;
    private String methodName;
    private String message;

    public static class Builder {
        private String datetime;
        private String level;
        private String className;
        private String methodName;
        private String message;

        public Builder datetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }

    public Log(Builder builder) {
        this.datetime = builder.datetime;
        this.level = builder.level;
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.message = builder.message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
