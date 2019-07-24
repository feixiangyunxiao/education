package com.bootdo.welcome.dao;

import com.bootdo.welcome.domain.MemberStudentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:02
 */
@Mapper
public interface MemberStudentDao {

	MemberStudentDO get(Long id);
	
	List<MemberStudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MemberStudentDO memberStudent);
	
	int update(MemberStudentDO memberStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateByStudyNumber(Integer studyNumber, String openid);

	MemberStudentDO getByStudyNumber(Integer studyNumber);

	@Select("select * from ed_member_student where mobile = #{mobile} ")
	MemberStudentDO getByMobile(@Param("mobile") String mobile);
}
