package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;


@WebServlet("/my_page_up")
public class MyPageUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageUpCtrl() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String kind = request.getParameter("kind");
		if(kind == "a") {
			// 닉네임, 커플 닉네임 , day 받아오고 db update 수정
			String nick = request.getParameter("nick");
			String cnick = request.getParameter("cnick");
			String jdate = request.getParameter("jdate");
		}else if(kind =="b") {
			// 이름,  비밀번호,  받아와서 수정 update
			
		}else {
			// 연인정보 해제 : 테이블 mi_dding(알림), mi_iscouple(연인 유무)/ 멤버 인포ㅓ, ci_idx(커플 일련번호) 멤버 인포 +커플 테이블  상태 변경
			
		}
	}

}
