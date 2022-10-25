package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/uploadPartProc")
@MultipartConfig(fileSizeThreshold = 0, location = "D:/ckw/jsp/work/woodaProject/WebContent/img")
public class UploadPartProc1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadPartProc1() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo) session.getAttribute("loginInfo");

		String mi_mail = request.getParameter("mi_mail");
		// cos.jar를 이용한 방법과 다르게 request객체를 이용하여 일반 문자열 데이터를 받을 수 있음
		Part part = request.getPart("img_file");
		// 업로드되는 파일을 Part형 인스턴스에 담음
		
		String contentDisposition = part.getHeader("content-disposition");
		// 예) form-data; name="file1"; filename="업로드할파일명.확장자"
		System.out.println("contentDisposition : " + contentDisposition);

		String uploadFileName = getUploadFileName(contentDisposition);
		part.write(uploadFileName);
		
		MyPageUpSvc mpc = new MyPageUpSvc();
		int result = 0;
		result = mpc.imgUp(uploadFileName, mi_mail);

		if (result != 0) {
			mi.setMi_pimg(uploadFileName);
		}
		response.sendRedirect("my_page");
	}

	private String getUploadFileName(String contentDisposition) {
		String uploadFileName = null;
		String[] contentSplitStr = contentDisposition.split(";");

		int fIdx = contentSplitStr[2].indexOf("\"");
		int sIdx = contentSplitStr[2].lastIndexOf("\"");

		uploadFileName = contentSplitStr[2].substring(fIdx + 1, sIdx);

		return uploadFileName;
	}
}
