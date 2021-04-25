package bean.responses;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Json响应体
 * 默认响应为{code:0,msg:"OK"}
 */
@Getter
@Setter
public class Response {
    //响应代码
    @JSONField
    int code = 0;
    //返回信息
    @JSONField(ordinal = 1)
    String msg = "OK";
    //返回数据
    @JSONField(ordinal = 2)
    Object Data;
}
