import bean.exception.DAOException;
import bean.responses.Response;
import bean.user.UserRegBean;
import com.alibaba.fastjson.JSON;
import dao.UserDao;
import dao.UserDaoImpl;
import org.junit.Test;
import service.UserServiceImpl;
import util.JDBCUtil;
import util.SQLOperation;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

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
            try {
                for (var i : operation.executeQuery()) {
                    System.out.println(i.get("user"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Test
    public void TestInsert() {
        UserRegBean userRegistration = new UserRegBean();
        userRegistration.setUsername("test");
        userRegistration.setPasswd("123456");
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
    public void TestLogin() throws DAOException {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.getUserByUID(userDao.getUserIDByKeyword("test")));
    }
}
