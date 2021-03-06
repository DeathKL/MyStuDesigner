package com.ssm.hui.dao;

import org.apache.ibatis.session.RowBounds;

/** 
 * 基础dao类，基本的CRUD方法
 * @author hui 
 * @date 创建时间：2017年9月2日 下午2:25:43 吴清辉新建
 * @version 1.0 
 **/
public interface BaseDao {


	/** 
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception 
	 * @returnType Object   
	 */
	public Object save(String str, Object obj) throws Exception;
	
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception;
	
	/**
	 * 删除对象 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception;

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception;

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception;
	
	
	/**
	 * 根据条件查询分页列表 
	 * @param str
	 * @param obj
	 * @param rb
	 * @return
	 * @throws Exception 
	 * @returnType Object
	 */
	public Object findForPageList(String str, Object obj ,RowBounds rb) throws Exception;
	
	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String sql, Object obj, String key , String value) throws Exception;
}
