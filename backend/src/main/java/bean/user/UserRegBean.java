package bean.user;

import lombok.Getter;
import lombok.Setter;

//Lombok库基操，快捷添加Getter,Setter
@Getter
@Setter
public class UserRegBean {
    //用户名
    String username;
    //密码
    String passwd;
    //用户类型
    int userType = 0;
}
