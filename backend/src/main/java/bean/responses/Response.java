package bean.responses;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    //响应代码
    @JSONField
    int code = 0;
    //返回信息
    @JSONField(ordinal = 1)
    String msg;
    //返回数据
    @JSONField(ordinal = 2)
    Object Data;
}
