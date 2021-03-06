package com.ssm.hui.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.ssm.hui.dao.BaseDao;
import com.ssm.hui.domain.Phone;
import com.ssm.hui.domain.Phone;
import com.ssm.hui.service.PhoneService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author hui 
 * @date 创建时间：2018年5月5日 下午4:25:14 吴清辉新建
 * @version 1.0 
 **/
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {
	
	//==注入dao	
	@Resource(name="baseDao")
	protected BaseDao baseDao;
	
	@Override
	public JSONArray getPhoneList() {
		Phone p=new Phone();
		JSONArray ja=new JSONArray();
		try {
			List<Phone> PhoneList=(List<Phone>) baseDao.findForList("PhoneMapper.getPhoneList", p);
			
			for(Phone hmo:PhoneList){
				ja.add(hmo);
			}
		} catch (Exception e) {
			ja=null;
			e.printStackTrace();
		}
		return ja;
	}

	@Override
	public String saveOrUpdatePhone(Phone p) {
		try {
			if(p.getPhoneId()==0){//新增
				baseDao.save("PhoneMapper.savePhone", p);
			}else{
				baseDao.update("PhoneMapper.updatePhoneById", p);
			}
			
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
			
		}
	}

	@Override
	public String deletePhone(Phone p) {
		try {
			baseDao.delete("PhoneMapper.deletePhone", p);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
			
		}
	}

	@Override
	public Phone getPhoneById(Phone p) {
		try {
			return (Phone) baseDao.findForObject("PhoneMapper.getPhone", p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public String updatePhoneById(Phone p) {
		try {
			baseDao.update("PhoneMapper.updatePhoneById", p);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
			
		}
	}

	@Override
	public JSONArray getPhoneListByList(JSONArray ja) {
		Iterator iter =ja.iterator();
		JSONArray jar=new JSONArray();
        //hasNext():判断是否存在下一个元素  
        while(iter.hasNext()){  
            //如果存在，则调用next实现迭代  
            JSONObject j=(JSONObject)iter.next();
            Phone p=new Phone();
            p.setPhoneName(j.getString("phone"));           
            try {
            	Phone pr=(Phone) baseDao.findForObject("PhoneMapper.getPhone", p);
            	if(pr!=null){
            		jar.add(pr);
            	}				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        } 
        return jar;
	}

	@Override
	public JSONObject getPhonePageList(Integer pageNum, Integer pageSize) {
		RowBounds rb=new RowBounds(pageNum,pageSize);
		JSONObject jo=new JSONObject();
		JSONArray ja=new JSONArray();
		try {
			Page<Phone> page=(Page<Phone>) baseDao.findForPageList("PhoneMapper.getPhoneList", null,rb);
			System.out.println(page);			
			long total=page.getTotal();					
			List<Phone> PhoneList=page.getResult() ;	
			for(Phone ado:PhoneList){
				if(ado!=null){
					ja.add(ado);
				}else{
					--total;
				}
				
			}
			jo.put("total", total);//总页数
			jo.put("rows",ja);			
		} catch (Exception e) {
			jo=null;
			e.printStackTrace();
		}		
		return jo;
	}

}
