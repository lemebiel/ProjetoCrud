package com.seuprojeto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("admin".equals(user) && "123".equals(pass)) {
            // Criar sessão e armazenar usuário
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", user);

            // Redirecionar para página de cadastro/listagem de produtos
            response.sendRedirect("produtos");
        } else {
            response.sendRedirect("index.jsp?error=Usuário ou senha incorretos!");
        }
    }
}
