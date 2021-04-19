import bean.user.UserRegBean;
import dao.UserDaoImpl;
import org.junit.Test;
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
        new UserDaoImpl().doRegisterUser(userRegistration);
    }
}
