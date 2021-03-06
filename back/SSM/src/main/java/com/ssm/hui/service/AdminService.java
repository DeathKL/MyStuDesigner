package com.ssm.hui.service;

import com.ssm.hui.domain.Admin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author hui 
 * @date 创建时间：2018年4月23日 下午2:26:49 吴清辉新建
 * @version 1.0 
 **/
public interface AdminService {
	String saveAdmin(Admin am);	
	JSONObject adminLogin(Admin am);
	JSONArray getAdminList();
	String saveOrUpdateAdmin(Admin am);
	String deleteAdmin(Admin am);
	Admin getAdminById(Admin am);
	String updateAdminPsw(Admin am);
	JSONObject getAdminPageList(Integer pageNum, Integer pageSize);
}
