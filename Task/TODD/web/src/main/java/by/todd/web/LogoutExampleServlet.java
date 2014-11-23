package by.todd.web;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.todd.api.Status;
import by.todd.web.helper.ResponseHelper;

/**
 * Created by sergey on 22.11.2014.
 */
public class LogoutExampleServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user != null) {
            resp.sendRedirect(userService.createLogoutURL(req.getRequestURI()));
        } else {
            ResponseHelper.write(resp, Status.OK);
        }
    }
}