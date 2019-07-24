package com.bootdo.welcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.welcome.dao.MemberStudentDao;
import com.bootdo.welcome.domain.MemberStudentDO;
import com.bootdo.welcome.service.MemberStudentService;



@Service
public class MemberStudentServiceImpl implements MemberStudentService, MemberStudentDao {
	@Autowired
	private MemberStudentDao memberStudentDao;
	
	@Override
	public MemberStudentDO get(Long id){
		return memberStudentDao.get(id);
	}
	
	@Override
	public List<MemberStudentDO> list(Map<String, Object> map){
		return memberStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return memberStudentDao.count(map);
	}
	
	@Override
	public int save(MemberStudentDO memberStudent){
		if (memberStudent.getId() != null) {
			return memberStudentDao.update(memberStudent);
		}
		return memberStudentDao.save(memberStudent);
	}
	
	@Override
	public int update(MemberStudentDO memberStudent){
		return memberStudentDao.update(memberStudent);
	}
	
	@Override
	public int remove(Long id){
		return memberStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return memberStudentDao.batchRemove(ids);
	}

	@Override
	public int updateByStudyNumber(Integer studyNumber, String openid) {
		return 0;
	}

	@Override
	public MemberStudentDO getByStudyNumber(Integer studyNumber) {
		return null;
	}


	@Override
	public MemberStudentDO getByStudyNumber(String openid, Integer studyNumber) {
		// 先保存用户的openid，然后根据用户的studynumber得出对象
		memberStudentDao.updateByStudyNumber(studyNumber, openid);
		MemberStudentDO byStudyNumber = memberStudentDao.getByStudyNumber(studyNumber);
		return byStudyNumber;
	}


	@Override
	public MemberStudentDO getByMobile(String mobile) {
		return memberStudentDao.getByMobile(mobile);
	}

}
