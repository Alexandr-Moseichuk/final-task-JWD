package by.moseichuk.adlinker.controller.command.user;

import by.moseichuk.adlinker.bean.User;
import by.moseichuk.adlinker.bean.UserFile;
import by.moseichuk.adlinker.constant.Attribute;
import by.moseichuk.adlinker.constant.Jsp;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.controller.command.Command;
import by.moseichuk.adlinker.controller.servlet.Forward;
import by.moseichuk.adlinker.service.ServiceEnum;
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
    private static final String PROFILE_PHOTO_DIR = "profile/";

    public UploadPhoto() {
        getPermissionSet().addAll(Arrays.asList(UserRole.values()));
    }

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String servletWorkPath = request.getServletContext().getRealPath("");
        servletWorkPath = servletWorkPath.replace('\\', '/');

        File uploadDir = new File(servletWorkPath + PROFILE_PHOTO_DIR);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdir()) {
                request.setAttribute(Attribute.ERROR_MESSAGE, "Cant upload photo");
                return new Forward(Jsp.ERROR);
            }
        }

        try {
            String fileName = "";
            String filePath = "";
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                filePath = servletWorkPath + PROFILE_PHOTO_DIR + fileName;
                part.write(filePath);
            }

            UserFileService userFileService = (UserFileService) serviceFactory.getService(ServiceEnum.FILE);
            UserService userService = (UserService) serviceFactory.getService(ServiceEnum.USER);
            User authorizedUser = (User) request.getSession(false).getAttribute("authorizedUser");

            String photoSrc = PROFILE_PHOTO_DIR + fileName;
            if (authorizedUser.getUserInfo().getUserFile() == null) {
                UserFile userFile = new UserFile(photoSrc);
                Integer fileId = userFileService.create(userFile);
                userFile.setId(fileId);
                authorizedUser.getUserInfo().setUserFile(userFile);
                userService.update(authorizedUser);
            } else {
                UserFile userFile = authorizedUser.getUserInfo().getUserFile();
                File oldPhoto = new File(servletWorkPath + userFile.getPath());
                if (oldPhoto.delete()) {
                    LOGGER.debug("DELETED " + oldPhoto.getAbsolutePath());
                } else {
                    LOGGER.debug("NOT DELETED " + oldPhoto.getAbsolutePath());
                }
                userFile.setPath(photoSrc);
                userFileService.update(userFile);
            }
            return new Forward("/user/profile.html", true);
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(Attribute.ERROR_MESSAGE, e.getMessage());
            return new Forward(Jsp.ERROR);
        }
    }

}
