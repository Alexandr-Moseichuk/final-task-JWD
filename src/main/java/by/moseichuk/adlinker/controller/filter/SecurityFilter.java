package by.moseichuk.adlinker.controller.filter;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.controller.command.*;
import by.moseichuk.adlinker.controller.command.Registration;
import by.moseichuk.adlinker.controller.command.application.ApplicationUpdate;
import by.moseichuk.adlinker.controller.command.show.IndexVisual;
import by.moseichuk.adlinker.controller.command.show.LoginVisual;
import by.moseichuk.adlinker.controller.command.show.RegistrationVisual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtrates invalid requests and prevents unauthorized access.
 *
 * @author Moseichuk Alexandr
 */
public class SecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);
    private static final List<Class<? extends Command>> publicCommands = new ArrayList<>();

    /**
     * Fills map of public commands.
     *
     * @param filterConfig      a FilterConfig object containing the filter's configuration and initialization parameters
     * @throws ServletException if an exception has occurred that interferes with the filter's normal operation
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        publicCommands.add(Lang.class);
        publicCommands.add(Login.class);
        publicCommands.add(Logout.class);
        publicCommands.add(LoginVisual.class);
        publicCommands.add(Registration.class);
        publicCommands.add(RegistrationVisual.class);
        publicCommands.add(IndexVisual.class);
        publicCommands.add(ApplicationUpdate.class);
        publicCommands.add(NotApprovedVisual.class);
    }

    /**
     * Allows only http requests and prevents unauthorized access to non public commands.
     *
     * @param servletRequest    the ServletRequest object contains the client's request
     * @param servletResponse   the ServletResponse object contains the filter's response
     * @param filterChain       the FilterChain for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the filter's normal operation
     */
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

            if (publicCommands.contains(command.getClass())) {
                LOGGER.debug("Public command...");
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user == null) {
                LOGGER.debug("User null. Permission denied...");
                response.sendRedirect(request.getContextPath() + "/login.html");
            } else if (user.getStatus() != UserStatus.VERIFIED) {
                LOGGER.debug("User not verified...");
                response.sendRedirect(request.getContextPath() + "/not_approved.html");
                //request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/application/not_approved.jsp").forward(servletRequest, servletResponse);
            } else if (command.getPermissionSet().contains(user.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("Permission denied...");
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/permission_denied.jsp").forward(servletRequest, servletResponse);
            }
        }
    }

}
