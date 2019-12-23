package edusystem.org.common.entity;

import edusystem.org.common.myEnum.ResultTypeEnum;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 * @description: 自定义统一响应返回类
 */

public class Result extends HashMap<String, Object>{

    public Result code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public Result message(String message) {
        this.put("message", message);
        return this;
    }

    public Result info(Object info) {
        this.put("code", 200);
        this.put("message", "success");
        this.put("info", info);
        return this;
    }

    public Result success() {
        this.put("message", "success");
        this.put("info", HttpStatus.values());
        this.code(HttpStatus.OK);
        return this;
    }

    public Result fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}