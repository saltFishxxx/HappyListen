package cn.xyh.service;

import cn.xyh.dao.UserDao;
import cn.xyh.model.User;
import cn.xyh.util.MailUtils;

public class UserService {
    private UserDao userDao = new UserDao();

    //注册
    public boolean register(User user) {
        boolean flag = false;
        if (userDao.addUser(user) > 0) {
            flag = true;
            MailUtils.sendMail(user.getEmail(), "<a href='http://localhost/bookstore/user/active?activeCode="+ user.getActiveCode() +"'>欢迎使用咸鱼听书，点击进行激活</a>", "激化邮件");
        }
        return flag;
    }

    //登陆
    public User login(User user) {
        return userDao.findUser(user);
    }

    //激活
    public boolean active(String activeCode) {
        boolean flag = false;
        if (userDao.findByActiveCode(activeCode)!=null) {
            if (userDao.active(activeCode) > 0) {
                flag = true;
            }
        }
        return flag;
    }

    //判断是否激活
    public boolean ifActive(String activeCode) {
        User byActiveCode = userDao.findByActiveCode(activeCode);
        boolean flag = true;
        if (byActiveCode.getState() == 1) {
            flag = false;
        }
        return flag;
    }

    //更新用户信息
    public boolean updateUser(User user) {
        boolean flag = false;
        if (userDao.updateUser(user.getId(), user) > 0) {
            flag = true;
        }
        return flag;
    }

    //根据用户id得出用户信息
    public User findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }
}
