package com.bootdo.welcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.dao.MemberRelationDao;
import com.bootdo.welcome.domain.MemberRelationDO;
import com.bootdo.welcome.service.MemberRelationService;



@Service
public class MemberRelationServiceImpl implements MemberRelationService {
	@Autowired
	private MemberRelationDao memberRelationDao;
	
	@Override
	public MemberRelationDO get(Long id){
		return memberRelationDao.get(id);
	}
	
	@Override
	public List<MemberRelationDO> list(Map<String, Object> map){
		return memberRelationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return memberRelationDao.count(map);
	}
	
	@Override
	public int save(MemberRelationDO memberRelation){
		if (memberRelation.getId() != null) {
			return memberRelationDao.update(memberRelation);
		}
		return memberRelationDao.save(memberRelation);
	}
	
	@Override
	public int update(MemberRelationDO memberRelation){
		return memberRelationDao.update(memberRelation);
	}
	
	@Override
	public int remove(Long id){
		return memberRelationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return memberRelationDao.batchRemove(ids);
	}
	
}
