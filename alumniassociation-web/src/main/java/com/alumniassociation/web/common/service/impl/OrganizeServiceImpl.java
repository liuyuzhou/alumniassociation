package com.alumniassociation.web.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alumniassociation.common.utils.RandomCharUtil;
import com.alumniassociation.common.utils.StringUtil;
import com.alumniassociation.web.common.dao.OrganizeDao;
import com.alumniassociation.web.common.entity.Organize;
import com.alumniassociation.web.common.service.OrganizeService;


@Service("organizeService")
@Transactional
public class OrganizeServiceImpl implements OrganizeService {
	@Autowired
	private OrganizeDao organizeDao;
	@Override
	public Organize queryObject(String orgId){
		return organizeDao.queryObject(orgId);
	}
	
	@Override
	public List<Organize> queryList(Map<String, Object> map){
		return organizeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return organizeDao.queryTotal(map);
	}
	
	@Override
	public void save(Organize organize){
		String orgId = RandomCharUtil.getNumberRand();
		organize.setOrgId(orgId);
		organize.setInDate(new Date());
		organizeDao.save(organize);
		
		if("1".equals(organize.getOrgType())){
			organizeDao.saveOperator(organize);
		}
		if("2".equals(organize.getOrgType())){
			organizeDao.saveFactory(organize);
		}
		if("3".equals(organize.getOrgType())){
			organize.setCity(organize.getProvinceCode()+","+organize.getCityCode()+","+organize.getAreaCode());
			organizeDao.saveRespository(organize);
		}
	}
	
	@Override
	public void update(Organize organize){
		organizeDao.update(organize);
		if("1".equals(organize.getOrgType())){
			organizeDao.updateOperator(organize);
		}
		if("2".equals(organize.getOrgType())){
			organizeDao.updateFactory(organize);
		}
		if("3".equals(organize.getOrgType())){
			organizeDao.updateRespository(organize);
		}
	}
	
	@Override
	public void delete(String orgId){
		Organize org = organizeDao.get(orgId);
		organizeDao.delete(orgId);
		List<Organize> organizes = organizeDao.queryByParentId(orgId);
		for (Organize organize:organizes){
			if (StringUtil.isEmpty(organize.getParentOrgId())){
				organizeDao.delete(orgId);
			}{
				this.delete(organize.getOrgId());
			}
		}
	}
	
	@Override
	public void deleteBatch(String[] orgIds){
		organizeDao.deleteBatch(orgIds);
	}

    @Override
    public void updateState(String[] ids,String stateValue) {
        for (String id:ids){
			Organize organize=queryObject(id);
			organize.setState(stateValue);
            update(organize);
        }
    }

	@Override
	public List<Organize> getList() {
		return organizeDao.getList();
	}

	@Override
	public Organize queryByOrgCode(String orgCode) {
		return organizeDao.queryByOrgCode(orgCode);
	}
	
	@Override
	public List<Organize> queryByParentId(String orgId){
		return organizeDao.queryByParentId(orgId);
	}

    @Override
    public List<Organize> selectList(Map<String, Object> map) {
        return organizeDao.selectList(map);
    }
}
