package by.moseichuk.final_task_JWD.controller.command;

import by.moseichuk.final_task_JWD.controller.Command;
import by.moseichuk.final_task_JWD.controller.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

public class SendJpeg extends Command {
    private static final Logger LOGGER = LogManager.getLogger(SendJpeg.class);

    //TODO create service and DAO
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(getPathOfImg(request)));
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            int ch = 0;
            while((ch = bis.read()) != -1) {
                bos.write(ch);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return null;
    }

    private String getPathOfImg(HttpServletRequest request) throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String[] pathArray = fullPath.split("/WEB-INF/classes/");
        fullPath = pathArray[0] + request.getAttribute("imgURI");
        return fullPath;
    }
}
