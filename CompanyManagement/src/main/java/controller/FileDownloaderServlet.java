package controller;

import hibernateConfig.HibernateUtil;
import model.Attachment;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloaderServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String id=req.getParameter("id");
            Session session= HibernateUtil.getSessionFactory().openSession();
            Attachment attachment=session.get(Attachment.class,Long.parseLong(id));
            session.close();
            byte[] file=attachment.getAttachment();
            String fileName=attachment.getFileName();
            String format=attachment.getFormat();
            resp.setContentType(format);
            resp.setHeader( "Content-Disposition", "attachment;" + fileName );
            ServletOutputStream outputStream=resp.getOutputStream();
            outputStream.write(file);
            outputStream.close();
        }
}
