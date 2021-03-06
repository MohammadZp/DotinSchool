package authorization;


import model.User;
import systemMessages.Message;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.LogRecord;

public class AdminAuthorization implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String html = "<html>\n" +
                "<head>\n" +
                "    <title>دسترسی غیر مجاز</title>\n" +
                "</head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<Center><h1  style=\"color: red;\"> .دسترسی غیر مجاز!شما اجازه ی انجام این عملیات را ندارید</h1></Center>\n" +
                "</body>\n" +
                "</html>";
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = servletResponse.getWriter();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().getCode().equals(Message.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else if(user.getManager()==null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            out.println(html);
        }
    }

    @Override
    public void destroy() {

    }
}
