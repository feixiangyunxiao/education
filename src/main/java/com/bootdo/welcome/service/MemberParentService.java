package com.bootdo.welcome.service;

import com.bootdo.welcome.domain.MemberParentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lxj
 * @email 15596029711@qq.com
 * @date 2019-05-17 11:12:08
 */
public interface MemberParentService {
	
	MemberParentDO get(Long id);
	
	List<MemberParentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberParentDO memberParent);
	
	int update(MemberParentDO memberParent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	MemberParentDO getByStudyNumber(String openid, Integer studyNumber);
}
