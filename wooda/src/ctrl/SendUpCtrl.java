package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/sendUp")
public class SendUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SendUpCtrl() { super();}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String inputEmail = request.getParameter("inputEmail").trim();
		String dDay = request.getParameter("dDay").trim();
		String shortMsg = request.getParameter("shortMsg");
		
	}

}
