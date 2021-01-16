package by.moseichuk.final_task_JWD.controller.filter;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.bean.UserStatus;
import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.command.Login;
import by.moseichuk.final_task_JWD.controller.command.Logout;
import by.moseichuk.final_task_JWD.controller.command.Registration;
import by.moseichuk.final_task_JWD.controller.command.show.IndexVisual;
import by.moseichuk.final_task_JWD.controller.command.show.LoginVisual;
import by.moseichuk.final_task_JWD.controller.command.show.RegistrationVisual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);
    private static final List<Class<? extends Command>> publicCommands = new ArrayList<>();
//    private static final ResourceBundle rb = ResourceBundle.getBundle("localisation.pagecontent");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        publicCommands.add(Login.class);
        publicCommands.add(Logout.class);
        publicCommands.add(LoginVisual.class);
        publicCommands.add(Registration.class);
        publicCommands.add(RegistrationVisual.class);
        publicCommands.add(IndexVisual.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            Command command = (Command) request.getAttribute("command");
            HttpSession session = request.getSession(false);
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
            }

            LOGGER.debug("Command: " + command.getClass());
            LOGGER.debug("User: " + user);

            if (publicCommands.contains(command.getClass())) {
                LOGGER.debug("Public command...");
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user == null) {
                LOGGER.debug("User null. Permission denied...");
                response.sendRedirect(request.getContextPath() + "/login.html");
            } else if (user.getStatus() != UserStatus.VERIFIED) {
                LOGGER.debug("User not verified...");
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/permission_denied.jsp").forward(servletRequest, servletResponse);
            } else if (command.getPermissionSet().contains(user.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("Permission denied...");
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/permission_denied.jsp").forward(servletRequest, servletResponse);
            }
        }
    }

}
