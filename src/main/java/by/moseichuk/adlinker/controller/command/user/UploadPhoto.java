package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserFile;
import by.moseichuk.adlinker.bean.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
import by.moseichuk.adlinker.service.ServiceFactory;
import by.moseichuk.adlinker.service.UserFileService;
import by.moseichuk.adlinker.service.UserService;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class UploadPhoto extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UploadPhoto.class);
    private static final String ERROR_JSP = "jsp/error.jsp";
    private static final String PROFILE_PHOTO_DIR = "profile/";

    public UploadPhoto() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String uploadPath = request.getServletContext().getRealPath("");
        uploadPath = uploadPath.replace('\\', '/');
        uploadPath = uploadPath.concat(PROFILE_PHOTO_DIR);

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            String fileName = "";
            String filePath = "";
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                filePath = uploadPath + fileName;
                part.write(filePath);
            }

            UserFileService userFileService = (UserFileService) serviceFactory.getService(ServiceEnum.FILE);
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");
            UserFile userFile = new UserFile(PROFILE_PHOTO_DIR + fileName);
            Integer fileId = userFileService.create(userFile);
            userFile.setId(fileId);
            authorizedUser.getUserInfo().setUserFile(userFile);
            userService.update(authorizedUser);

            return new Forward("/user/profile.html", true);
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", e.getMessage());
            return new Forward(ERROR_JSP);
        }
    }

}
