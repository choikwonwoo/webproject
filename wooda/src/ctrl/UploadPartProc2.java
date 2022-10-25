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
		// �������� ȭ�鿡 ��½�Ű�� ����� ����ϱ� ���� PrintWriter��ü ����
		
		// �޾ƿ� ���� : ���� �� �� �������� �־� �� ������ ��ġ���� �ε��� ���� ���� �ϴ� �������� ���� �̹���, am_code ��
		// (am_code ���� �� ��, �̹��� �̸� ���� ��(���� �Ĵ� ���� ����), ��ġ��..? ) ���� ��������
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
		// getParts() : ����ڰ� ���� ������(��Ʈ��)���� Collection<Part>�� ��� ����
		// getParts()�� �޾ƿ� Part ��ü���� �ϳ��� Part�� �ν��Ͻ� part�� ��� ������ ��
			if (part.getName().equals("img_file")) {
			// part�� �޾ƿ� ��Ʈ���� �̸��� 'name'�� �ƴϸ�(file ��Ʈ�Ѹ� �۾��ϰڴٴ� �ǹ�)
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
