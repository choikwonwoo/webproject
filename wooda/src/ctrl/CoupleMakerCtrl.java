package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/make_couple")
public class CoupleMakerCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CoupleMakerCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
		String jdate = request.getParameter("jdate");
		String senderEmail = (String)request.getParameter("senderEmail");
		String receiverEmail = (String)request.getParameter("receiverEmail");
		CoupleMakerSvc cms = new CoupleMakerSvc();
		int result = 0;
		result = cms.coupleMaker(jdate, senderEmail, receiverEmail);
		
		if (result != 0) {
			mi.setMi_dding("b");
			mi.setMi_iscouple("y");
			CoupleInfoSvc cs = new CoupleInfoSvc();
			CoupleInfo ci = cs.getCoupleInfo(mi.getCi_idx(), mi.getMi_mail());
			session.setAttribute("coupleInfo", ci);
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("opener.document.location.reload();");
			out.println("window.close();");
			out.println("</script>");
			out.close();
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('등록 실패'); window.close(); </script>");
			out.close();
		}
	}

}
