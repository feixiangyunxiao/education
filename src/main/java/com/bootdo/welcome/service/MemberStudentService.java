package com.bootdo.welcome.service;

import com.bootdo.welcome.domain.MemberStudentDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:02
 */
public interface MemberStudentService {
	
	MemberStudentDO get(Long id);
	
	List<MemberStudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberStudentDO memberStudent);
	
	int update(MemberStudentDO memberStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	MemberStudentDO getByStudyNumber(@Param("openid") String openid, @Param("studyNumber") Integer studyNumber);

	MemberStudentDO getByMobile(@Param("mobile") String mobile);
}
