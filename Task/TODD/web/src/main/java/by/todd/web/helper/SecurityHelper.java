package by.todd.web.helper;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sergey on 22.11.2014.
 */
public class SecurityHelper {

    public static User getCurrentUser(HttpServletRequest req) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        return user;
    }
}
