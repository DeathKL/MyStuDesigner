package com.ssm.hui.service;

import com.ssm.hui.domain.Admin;

/** 
 * @author hui 
 * @date 创建时间：2018年4月23日 下午2:26:49 吴清辉新建
 * @version 1.0 
 **/
public interface AdminService {
	String saveAdmin(Admin am);
	String adminLogin(Admin am);
}
