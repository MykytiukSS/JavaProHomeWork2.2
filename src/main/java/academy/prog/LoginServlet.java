package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        int ageInt = Integer.parseInt(age);


        if (LOGIN.equals(login) && PASS.equals(password) && checkPass(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);

        }
        if (ageInt < 18)
            response.sendRedirect("age.jsp");
        else if (!checkPass(password))
            response.sendRedirect("passWrong.jsp");
         else
            response.sendRedirect("index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }

    public static boolean checkPass(String password) {
        boolean x;
        String string = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{10,}";
        if (password.matches(string)) {
            return x = true;
        } else {
            return x = false;
        }
    }
}
