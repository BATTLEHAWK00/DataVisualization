package bean.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户数据Bean
 */
@Getter
@Setter
@ToString
public class UserBean {
    String userid;
    String username;
    String passwd;
    String nickname;
    String regtime;
    int usertype;
    String email;
    String phone;
    boolean isLocked;
}
