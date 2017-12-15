package cn.wsg.iovyw_search.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.wsg.iovyw_search.service.SearchService;

/**
 * ���Ʋ�
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	
	private SearchService searchService = new SearchService();

	/**
	 * ����doPost����
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ����ݹ�����ismi��sn
		String ismi_or_sn = request.getParameter("ISMI_or_SN");
		String dn = null;
		//�ж�������Ƿ�Ϊ��
		if(ismi_or_sn==null || ismi_or_sn.equals("")) {
			request.setAttribute("msg", "�������Ϊ�գ���");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//�жϳ����Ƿ���ȷ
		if((ismi_or_sn.length()!=6) && (ismi_or_sn.length()!=15)) {
			request.setAttribute("msg", "����ĳ��Ȳ���ȷ����");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//�жϸ�ʽ�Ƿ���ȷ
		if(!ismi_or_sn.matches("[A-Za-z0-9]+")) {
			request.setAttribute("msg", "����ĸ�ʽ����ȷ��ֻ�ܰ������ֺ���ĸ!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//����ַ�������Ϊ6��Ϊsn,����Ϊimsi
		if(ismi_or_sn.length() == 6) {
			dn = this.dnToCompany(searchService.findDnBySn(ismi_or_sn));
			request.setAttribute("msg", dn);
			request.setAttribute("SN", ismi_or_sn);
			if(dn.equals("�����ҵ����ݲ����ڣ�")) {
				request.setAttribute("code", 0);
			} else {
				request.setAttribute("code", 1);
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		} else {
			dn = this.dnToCompany(searchService.findDnByIMSI(ismi_or_sn));
			request.setAttribute("msg", dn);
			request.setAttribute("ISMI", ismi_or_sn);
			if(dn.equals("�����ҵ����ݲ����ڣ�")) {
				request.setAttribute("code", 0);
			} else {
				request.setAttribute("code", 1);
			}
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
	}
	
	/**
	 * ������ת���ɹ�˾��
	 * @return
	 */
	private String dnToCompany(String dn) {
		if(dn==null) {
			return "�����ҵ����ݲ����ڣ�";
		} else if(dn.equals("mt.iovyw.com")) {
			return "�乾����";
		} else if(dn.equals("yx.iovyw.com")) {
			return "������";
		} else if(dn.equals("jfei.iovyw.com")) {
			return "��AC����ƽ̨";
		} else if(dn.equals("hs.iovyw.com")) {
			return "����";
		} else if(dn.equals("test.iovyw.com")) {
			return "΢˼��";
		} else if(dn.equals("wc.iovyw.com")) {
			return "�ĳ�";
		} else if(dn.equals("30kt.iovyw.com")) {
			return "���㿭��";
		} else if(dn.equals("hooenergy.iovyw.com")) {
			return "����";
		} else if(dn.equals("xjhb.iovyw.com")) {
			return "�½��Ʋ�";
		} else if(dn.equals("gem.iovyw.com")){
			return "���ľ�κ�";
		}
		return "";
	}

}






