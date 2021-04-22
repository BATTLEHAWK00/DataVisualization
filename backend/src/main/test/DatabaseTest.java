import bean.responses.Response;
import bean.user.UserRegBean;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import service.UserServiceImpl;
import util.JDBCUtil;
import util.SQLOperation;

public class DatabaseTest {
    @Test
    public void TestConnection() {
        JDBCUtil.getInstance().getConnection(connection -> {

        });
    }

    @Test
    public void TestQuery() {
        JDBCUtil.getInstance().getConnection(connection -> {
            var operation = new SQLOperation(connection);
            operation.setSql("SELECT * FROM test");
            for (var i : operation.executeQuery()) {
                System.out.println(i.get("user"));
            }
        });
    }

    @Test
    public void TestInsert() {
        UserRegBean userRegistration = new UserRegBean();
        userRegistration.setUsername("test");
        userRegistration.setPasswd("test");
        userRegistration.setUserType(1);
        new UserServiceImpl().doRegisterUser(userRegistration);
    }

    @Test
    public void BeanJsonTest() {
        Response response = new Response();
        response.setMsg("Hello!");
        Response response1 = new Response();
        response1.setMsg("1");
        response.setData(response1);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void TestLogin() {

    }
}
