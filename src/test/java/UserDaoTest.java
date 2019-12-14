import cn.xyh.dao.UserDao;
import cn.xyh.model.User;
import org.junit.Test;

public class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    public void testAddUser() {
        User user = new User();
        user.setGender("男");
        user.setPassword("111111");
        user.setActiveCode("JDKSFJSDKL");
        user.setEmail("3748738@qq.com");
        user.setState(0);
        user.setUsername("小明");
        user.setTelephone("13647634374");
        user.setRole("游客");
        user.setIntroduce("我最帅");
        int i = userDao.addUser(user);
        System.out.println(i);
    }

    @Test
    public void testFindUser() {
        User user = new User();
        user.setUsername("小明");
        user.setPassword("111111");
        User user1 = userDao.findUser(user);
        System.out.println(user1);
    }

    @Test
    public void testFindByUserId() {
        User byId = userDao.findById(7);
        System.out.println(byId);
    }

    @Test
    public void testFindByActiveCode() {
        User ddfsdsfs = userDao.findByActiveCode("20206cf897f64aa1b3eaaab1d84fe2ed");
        System.out.println(ddfsdsfs);
    }

    @Test
    public void testActive() {
        int active = userDao.active("20206cf897f64aa1b3eaaab1d84fe2ed");
        System.out.println(active);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setTelephone("126387264873");
        user.setPassword("222222");
        user.setGender("女");
        int i = userDao.updateUser(7, user);
        System.out.println(i);
    }
}
