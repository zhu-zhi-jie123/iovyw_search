package cn.wsg.iovyw_search.service;

import cn.wsg.iovyw_search.dao.SearchDao;

/**
 * ҵ���߼���
 * @author zhuzhijie
 *
 */
public class SearchService {

	private SearchDao searchDao = new SearchDao();
	
	/**
	 * ����sn��ѯdn
	 * @param sn
	 * @return
	 */
	public String findDnBySn(String sn) {
		String dn = searchDao.findDnBySn(sn);
		return dn;
	}
	
	/**
	 * ����imsi��ѯdn
	 * @param imsi
	 * @return
	 */
	public String findDnByIMSI(String imsi) {
		String dn = searchDao.findDnByIMSI(imsi);
		return dn;
	}
	
}
