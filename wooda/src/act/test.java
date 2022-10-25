package act;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

public class test {
	public void saveImg(Collection<Part> collection) throws IOException {
		for (Part part : collection) {
			
			if (part.getName().equals("img_file")) { // 이미지가 들어있던 input type=file 의 이름 설정
				String contentDisposition = part.getHeader("content-disposition");
				System.out.println(contentDisposition); //잘 받았나 확인

				// file 객체가 비었으면 form-data; name="file1"; filename="" 로 출력됨
				// 예) form-data; name="file1"; filename="업로드할파일명.확장자"
				String uploadFileName = getUploadFileName(contentDisposition);
				if (!uploadFileName.equals("")) {// 업로드할 파일이 있으면
					System.out.println("엥?");
					System.out.println(uploadFileName); //잘 받았나 확인
					part.write(uploadFileName);
				}
			}
		}
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
