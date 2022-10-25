package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import svc.PresentRequestProcInSvc;
import vo.*;

@WebServlet("/present_request_proc_in")
public class PresentRequestProcInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PresentRequestProcInCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
   	 	MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
   	 	String mi_mail = loginInfo.getMi_mail();
   	 	String gi_brand = request.getParameter("gi_brand");
   	 	String gi_name = request.getParameter("gi_name");
   	 	int gi_price = Integer.parseInt(request.getParameter("gi_price"));
   	 	String gi_tag = request.getParameter("gi_tag");
   	 	String gi_content = request.getParameter("gi_content");
   	 	String gi_img1 = "";
   	 	if(request.getParameter("gi_img1") != null) {
   	 	gi_img1 = request.getParameter("gi_img1");
   	 	}
   	 	String gi_img2 = "";
   	 	if(request.getParameter("gi_img2") != null) {
   	 	gi_img2 = request.getParameter("gi_img2");
   	 	}
   	 	String gi_img3 = "";
   	 	if(request.getParameter("gi_img3") != null) {
   	 	gi_img3 = request.getParameter("gi_img3");
   	 	}
   	 	
   	 	GiftInfo giftInfo = new GiftInfo();
   	 	giftInfo.setMi_mail(mi_mail);  			giftInfo.setGi_brand(gi_brand);
   	 	giftInfo.setGi_name(gi_name);  			giftInfo.setGi_price(gi_price);
   	 	giftInfo.setGi_tag1(gi_tag);	   	 	giftInfo.setGi_content(gi_content);
   	 	giftInfo.setGi_img1(gi_img1);   	 	giftInfo.setGi_img2(gi_img2);
   	 	giftInfo.setGi_img3(gi_img3);
   	 	
   	 	
   	 	PresentRequestProcInSvc presentRequestProcInSvc = new PresentRequestProcInSvc();
   	 	int result = presentRequestProcInSvc.presentRequestInsert(giftInfo);
   	 	
   	 	
   	 	if(result > 0) {
   	 		response.setContentType("text/html; charset=utf-8");
   	 		PrintWriter out = response.getWriter();
   	 		out.println("<script>");
   	 		out.println("alert('상품 등록요청이 완료되었습니다.');");
   	 		out.println("window.close();");
   	 		out.println("</script>");
   	 		out.close();
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록에 실패했습니다. 다시 시도해 보세요.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
   	 	
	}

}
