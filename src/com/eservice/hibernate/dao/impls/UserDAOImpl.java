package com.eservice.hibernate.dao.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eservice.core.beans.LocationContact;
import com.eservice.core.beans.User;
import com.eservice.core.beans.VoiceContact;
import com.eservice.core.daos.UserDAO;
import com.eservice.hibernate.entities.LocationContactEntity;
import com.eservice.hibernate.entities.UserEntity;
import com.eservice.hibernate.entities.VoiceContactEntity;
import com.eservice.hibernate.utils.HibernateExceptionUtil;

//This will make easier to autowired
@Repository("userDAO")
// Default is read only
@Transactional
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
 
	private HibernateTemplate hibernateTemplate;

    @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(getSessionFactory());
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Map saveUser(User user) {
		Map resultMap = new HashMap();
		Long id =0L;
		Transaction trns = null;
		trns = getSessionFactory().getCurrentSession().beginTransaction();
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(user.getUserName());
		userEntity.setPassword(user.getPassword());
		userEntity.setOtherNames(user.getOtherNames());
		userEntity.setLastName(user.getLastName());
		userEntity.setTitle(user.getTitle());
		userEntity.setEnabled(user.isEnabled());
		userEntity.setGender(user.getGender());
		userEntity.setEmail(user.getEmail());
		Set<VoiceContactEntity> voiceContactDetails=new HashSet<VoiceContactEntity>();
		for(VoiceContact voiceContact : user.getVoiceContactDetailList()){
			VoiceContactEntity voiceContactEntity=new VoiceContactEntity();
			voiceContactEntity.setContactNumber(voiceContact.getContactNumber());
			voiceContactEntity.setVoiceContactType(voiceContact.getVoiceContactType());
			voiceContactEntity.setPriorityLevel(voiceContact.getPriorityLevel());
			voiceContactDetails.add(voiceContactEntity);
		}
		userEntity.setVoiceContactDetails(voiceContactDetails);
		Set<LocationContactEntity> locationContactDetails=new HashSet<LocationContactEntity>();
		for(LocationContact locationContact : user.getLocationContactDetailList()){
			LocationContactEntity locationContactEntity=new LocationContactEntity();
			locationContactEntity.setAddressLine1(locationContact.getAddressLine1());
			locationContactEntity.setAddressLine2(locationContact.getAddressLine2());
			locationContactEntity.setCity(locationContact.getCity());
			locationContactEntity.setStateCode(locationContact.getStateCode());
			locationContactEntity.setPostCode(locationContact.getPostCode());
			locationContactEntity.setCountryCode(locationContact.getCountryCode());
			locationContactEntity.setPrioriyLevel(locationContact.getPrioriyLevel());
			locationContactDetails.add(locationContactEntity);
		}
		userEntity.setLocationContactDetails(locationContactDetails);
		try {
			resultMap.put("id",id);
			id =  (Long)getHibernateTemplate().save(userEntity);
			trns.commit();
			user.setId(userEntity.getId());
			List<VoiceContact> voiceContactDetailList = new ArrayList<VoiceContact>();
			for(VoiceContactEntity objEntity:userEntity.getVoiceContactDetails()){
				voiceContactDetailList.add(objEntity);
			}
			user.setVoiceContactDetailList(voiceContactDetailList);
			resultMap.put("user",user);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
			if (HibernateExceptionUtil.isViolatedConstraint(ex))
				resultMap.put("message", HibernateExceptionUtil.getViolatedConstraintName(ex));
			else
				resultMap.put("message", ex.getCause().getMessage());
			return resultMap;
		}
		resultMap.put("success", true);
		resultMap.put("message","Successfully saved");
		return resultMap;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Map updateUser(User user) {
		Map resultMap = new HashMap();
		Transaction trns = null;
		trns = getSessionFactory().getCurrentSession().beginTransaction();
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setUserName(user.getUserName());
		userEntity.setPassword(user.getPassword());
		userEntity.setOtherNames(user.getOtherNames());
		userEntity.setLastName(user.getLastName());
		userEntity.setTitle(user.getTitle());
		userEntity.setEnabled(user.isEnabled());
		userEntity.setGender(user.getGender());
		userEntity.setEmail(user.getEmail());
		Set<VoiceContactEntity> voiceContactDetails=new HashSet<VoiceContactEntity>();
		for(VoiceContact voiceContact : user.getVoiceContactDetailList()){
			VoiceContactEntity voiceContactEntity=new VoiceContactEntity();
			voiceContactEntity.setId(voiceContact.getId());
			voiceContactEntity.setContactNumber(voiceContact.getContactNumber());
			voiceContactEntity.setVoiceContactType(voiceContact.getVoiceContactType());
			voiceContactEntity.setPriorityLevel(voiceContact.getPriorityLevel());
			
			voiceContactDetails.add(voiceContactEntity);
		}
		userEntity.setVoiceContactDetails(voiceContactDetails);
		Set<LocationContactEntity> locationContactDetails=new HashSet<LocationContactEntity>();
		for(LocationContact locationContact : user.getLocationContactDetailList()){
			LocationContactEntity locationContactEntity=new LocationContactEntity();
			locationContactEntity.setAddressLine1(locationContact.getAddressLine1());
			locationContactEntity.setAddressLine2(locationContact.getAddressLine2());
			locationContactEntity.setCity(locationContact.getCity());
			locationContactEntity.setStateCode(locationContact.getStateCode());
			locationContactEntity.setPostCode(locationContact.getPostCode());
			locationContactEntity.setCountryCode(locationContact.getCountryCode());
			locationContactEntity.setPrioriyLevel(locationContact.getPrioriyLevel());
			locationContactDetails.add(locationContactEntity);
		}
		userEntity.setLocationContactDetails(locationContactDetails);
		try {
			getHibernateTemplate().update(userEntity);
			trns.commit();
			resultMap.put("user",user);
		} catch (Exception ex) {
			resultMap.put("success", false);
			if (trns != null) {
				trns.rollback();
			}
			if (HibernateExceptionUtil.isViolatedConstraint(ex))
				resultMap.put("message", HibernateExceptionUtil.getViolatedConstraintName(ex));
			else
				resultMap.put("message", ex.getCause().getMessage());
			return resultMap;
		}
		resultMap.put("success", true);
		resultMap.put("message","Successfully updated");
		return resultMap;
	}

	@Override
	public User selectUser(String userName, String password) {
		
		String criteria="from UserEntity u where u.userName=? and u.password=? and u.enabled=?";
		Object[] criteriaValues={userName,password,true};
		List<UserEntity> userList =new ArrayList<UserEntity>();
		Transaction trns = getSessionFactory().getCurrentSession().beginTransaction();
		userList =  getHibernateTemplate().find(criteria,criteriaValues);
		trns.commit();
		if(userList!=null && userList.size()==1){
			return userList.get(0);
		}
		
		
		return null;
	}

}
