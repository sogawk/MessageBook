package util;

import service.UserService;

public class CheckUtil {
    public int loginCheck(String name, String password) throws Exception {
        UserService userService = new UserService();
        return userService.selectIn(name, password);

    }

    public Boolean registerCheck(String name, String password, String rePassword) {
        return true;
    }
}
