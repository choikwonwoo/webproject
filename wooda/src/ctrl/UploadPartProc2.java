package ctrl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

import act.*;

@WebServlet("/uploadPartProc2")
@MultipartConfig(
	fileSizeThreshold = 0, 
	location = "D:/ckw/jsp/work/woodaProject/WebContent/img"
)
public class UploadPartProc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadPartProc2() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 서블릿에서 화면에 출력시키는 기능을 사용하기 위한 PrintWriter객체 생성
		
		// 받아올 정보 : 변경 전 값 히든으로 넣어 다 가져옴 위치값은 인덱스 값과 동일 하니 괜찮을것 같고 이미지, am_code 값
		// (am_code 변경 전 후, 이미지 이름 변경 전(변경 후는 여기 있음), 위치값..? ) 이케 가져오자
		String localName0 = request.getParameter("local_name0");
		String localName1 = request.getParameter("local_name1");
		String localName2 = request.getParameter("local_name2");
		String localName3 = request.getParameter("local_name3");
		String localName4 = request.getParameter("local_name4");
		// update 
		String imgName0 = request.getParameter("img_name0");
		String imgName1 = request.getParameter("img_name1");
		String imgName2 = request.getParameter("img_name2");
		String imgName3 = request.getParameter("img_name3");
		String imgName4 = request.getParameter("img_name4");
		String arr1[] = {localName0,localName1,localName2,localName3,localName4};
		String arr2[] = {imgName0,imgName1,imgName2,imgName3,imgName4};
		
//		
//
//		test tes = new test();
//		tes.saveImg(request.getParts());
//		
		
		int i = 0;
		for (Part part : request.getParts()) {
		// getParts() : 사용자가 보낸 데이터(컨트롤)들을 Collection<Part>에 담아 리턴
		// getParts()로 받아온 Part 객체들을 하나씩 Part형 인스턴스 part에 담아 루프를 돔
			if (part.getName().equals("img_file")) {
			// part로 받아온 컨트롤의 이름이 'name'이 아니면(file 컨트롤만 작업하겠다는 의미)
				String contentDisposition = part.getHeader("content-disposition");
				
				String uploadFileName = getUploadFileName(contentDisposition);
				
				if (!uploadFileName.equals("") || uploadFileName!=null) {
					
					arr2[i] = uploadFileName;
					i++;
					part.write(uploadFileName);
				}
			}
			
		}
		
		int result = 0;
		
		AdminImgSvc adminImgSvc = new AdminImgSvc();
		result = adminImgSvc.updateAdimg(arr1 ,arr2);
		
		
		response.sendRedirect("admin_main_img");
		
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
