package com.alumniassociation.commpara.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alumniassociation.commpara.dao.CommparaDao;
import com.alumniassociation.commpara.entity.Commpara;
import com.alumniassociation.commpara.service.CommparaService;


@Service("commparaService")
@Transactional
public class CommparaServiceImpl implements CommparaService {
	@Autowired
	private CommparaDao commparaDao;
	
	@Override
	public Commpara queryObject(Integer paraId){
		return commparaDao.queryObject(paraId);
	}
	
	@Override
	public List<Commpara> queryList(Map<String, Object> map){
		return commparaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return commparaDao.queryTotal(map);
	}
	
	@Override
	public void save(Commpara commpara){
		commparaDao.save(commpara);
	}
	
	@Override
	public void update(Commpara commpara){
		commparaDao.update(commpara);
	}
	
	@Override
	public void delete(Integer paraId){
		commparaDao.delete(paraId);
	}
	
	@Override
	public void deleteBatch(Integer[] paraIds){
		commparaDao.deleteBatch(paraIds);
	}

    @Override
    public void updateState(Integer[] ids,String stateValue) {
        for (Integer id:ids){
			Commpara commpara=queryObject(id);
			commpara.setState(stateValue);
            update(commpara);
        }
    }

	@Override
	public List<Commpara> getCodeValues(Map<String, Object> params) {
		return commparaDao.getCodeValues(params);
	}


	@Override
	public List<Map<String,Object>> getCodeForValues(Map<String, Object> params) {
		return commparaDao.getCodeForValues(params);
	}
	
	@Override
	public List<Commpara> findByVerify(Commpara commpara) {
		return commparaDao.findByVerify(commpara);
	}

	@Override
	public List<Map<String,Object>> getApiList(Map<String,Object> map) {
		return commparaDao.getApiList(map);
	}

	@Override
	public List<Map<String,Object>> getApiListNew(Map<String,Object> map) {
		return commparaDao.getApiListNew(map);
	}

	@Override
	public List<Map<String, Object>> getIdValues(Map<String, Object> params) {
		return commparaDao.getIdValues(params);
	}

	@Override
	public String getCodeName(String code, String value) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("paraCode", code);
		params.put("paraKey", value);
		return commparaDao.getCodeName(params);
	}

}
