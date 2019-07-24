package com.bootdo.welcome.service.impl;

import com.bootdo.welcome.domain.MemberStudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.dao.MemberParentDao;
import com.bootdo.welcome.domain.MemberParentDO;
import com.bootdo.welcome.service.MemberParentService;



@Service
public class MemberParentServiceImpl implements MemberParentService {
	@Autowired
	private MemberParentDao memberParentDao;
	
	@Override
	public MemberParentDO get(Long id){
		return memberParentDao.get(id);
	}
	
	@Override
	public List<MemberParentDO> list(Map<String, Object> map){
		return memberParentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return memberParentDao.count(map);
	}
	
	@Override
	public int save(MemberParentDO memberParent){
		if (memberParent.getId() != null) {
			return memberParentDao.update(memberParent);
		}
		return memberParentDao.save(memberParent);
	}
	
	@Override
	public int update(MemberParentDO memberParent){
		return memberParentDao.update(memberParent);
	}
	
	@Override
	public int remove(Long id){
		return memberParentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return memberParentDao.batchRemove(ids);
	}

	@Override
	public MemberParentDO getByStudyNumber(String openid, Integer studyNumber) {
		// 先保存用户的openid，然后根据用户的studynumber得出对象
		memberParentDao.updateByStudyNumber(studyNumber, openid);
		MemberParentDO byStudyNumber = memberParentDao.getByStudyNumber(studyNumber);
		return byStudyNumber;
	}

}
