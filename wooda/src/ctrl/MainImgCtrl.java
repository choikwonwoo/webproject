package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminImgSvc;
import vo.AdminImg;


@WebServlet("/admin_main_img")
public class MainImgCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainImgCtrl() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AdminImgSvc adminImgSvc = new AdminImgSvc();
		ArrayList<AdminImg> adminImg = (ArrayList<AdminImg>)adminImgSvc.getImgInfo();
		
		request.setAttribute("adminImg",adminImg);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("admin/index_main.jsp");
			dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
