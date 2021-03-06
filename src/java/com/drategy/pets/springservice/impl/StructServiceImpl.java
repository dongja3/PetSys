//$Source: /petSys/petSys/src/java/com/drategy/pets/springservice/impl/StructServiceImpl.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/21 01:07:20 $

package com.drategy.pets.springservice.impl;

import java.util.List;

import com.drategy.pets.bom.Address;
import com.drategy.pets.bom.Image;
import com.drategy.pets.bom.Phone;
import com.drategy.pets.dao.AddressDAO;
import com.drategy.pets.dao.AreaDAO;
import com.drategy.pets.dao.AuthorizationDAO;
import com.drategy.pets.dao.EmployeeDAO;
import com.drategy.pets.dao.ImageDAO;
import com.drategy.pets.dao.NewsDAO;
import com.drategy.pets.dao.PetDAO;
import com.drategy.pets.dao.PetOwnerDAO;
import com.drategy.pets.dao.PhoneDAO;
import com.drategy.pets.dao.RfidChipDAO;
import com.drategy.pets.dao.SmsDAO;
import com.drategy.pets.dao.SmsSendDAO;
import com.drategy.pets.dao.TreatmentRecordDAO;
import com.drategy.pets.dao.UserDAO;
import com.drategy.pets.dao.VaccineDAO;
import com.drategy.pets.dao.VarietyDAO;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.Authorization;
import com.drategy.pets.domain.Employee;
import com.drategy.pets.domain.News;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.RfidChip;
import com.drategy.pets.domain.Sms;
import com.drategy.pets.domain.SmsSend;
import com.drategy.pets.domain.TreatmentRecord;
import com.drategy.pets.domain.User;
import com.drategy.pets.domain.Vaccine;
import com.drategy.pets.domain.Variety;
import com.drategy.pets.springservice.StructService;

/**
 * 系统的structService接口的实现
 * 
 * @author Jason Jiang
 * @author $Autho $
 * @$Revision: 1.27 $
 */
public class StructServiceImpl implements StructService {

	/**
	 * 初始化的数据
	 */
	public void initData() {
		throw new java.lang.UnsupportedOperationException(
				"Method initData() not yet implemented.");
	}

	/** 定义UserDAO */
	private UserDAO userDAO;

	/** 定义AreaDAO* */
	private AreaDAO areaDAO;

	/** 定义AuthorizationDAO* */
	private AuthorizationDAO authorizationDAO;

	/** 定义EmployeeDAO* */
	private EmployeeDAO employeeDAO;

	/** 定义PetDAO* */
	private PetDAO petDAO;

	/** 定义PetOwnerDAO* */
	private PetOwnerDAO petOwnerDAO;

	/** 定义RfidChipDAO* */
	private RfidChipDAO rfidChipDAO;

	/** 定义TreatmentRecordDAO* */
	private TreatmentRecordDAO treatmentRecordDAO;

	/** 定义VaccineDAO* */
	private VaccineDAO vaccineDAO;

	/** 定义AddressDAO* */
	private AddressDAO addressDAO;

	/** 定义PhoneDAO* */
	private PhoneDAO phoneDAO;

	/** 定义了ImageDAO* */
	private ImageDAO imageDAO;

	private NewsDAO newsDAO;
	
	private SmsDAO smsDAO;
	
	private VarietyDAO varietyDAO;
	
	private SmsSendDAO smsSendDAO;

	/** 增加增加登录用户 */
	public void addUser(User user) {
		userDAO.add(user);
	}

	/** 查询一个登录用户 */
	public User findUser(String id) {
		return userDAO.find(id);
	}

	/** 删除一个登录用户 */
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	/** 修改一个登录用户 */
	public void updateUser(User user) {
		userDAO.update(user);
	}

	/** 通过userName 和 password 查找用户 */
	public User findUserByUserName(String userName, String password) {
		return userDAO.findByUserName(userName, password);
	}

	/** *查找一个user通过name */
	public User findUserByName(String name) {
		return userDAO.findByName(name);
	}

	/** *通过AreaId查找user */
	public List findUserByAreaId(String id) {
		return userDAO.findbyAreaId(id);
	}

	/** 增加区域 */
	public void addArea(Area area) {
		areaDAO.add(area);
	}

	/** 查找一个区域对象 */
	public Area findArea(String id) {
		return areaDAO.find(id);
	}

	/** 删除一个区域对象 */
	public void deleteArea(Area area) {
		areaDAO.delete(area);
	}

	/** 修改一个区域对象 */
	public void updateArea(Area area) {
		areaDAO.update(area);
	}

	/** *查找一个area通过name */
	public Area findAreaByName(String name) {
		return areaDAO.findByName(name);
	}

	/** *通过区域代码查找一个area */
	public Area findAreaByCode(String areaCode) {
		return areaDAO.findByCode(areaCode);
	}

	/** *通过区域代码和AreaId查找一个area */
	public Area findAreaByCodeandId(String areaCode, String id) {
		return areaDAO.findByCodeandId(areaCode, id);
	}

	/** *通过区域名字和ID查找一个area */
	public Area findAreaByNameandId(String areaName, String id) {
		return areaDAO.findByNameandId(areaName, id);
	}
    
	/** 查找一个通过areaCode* */
	public List findAreaListByCode(String areaCode){
	    return areaDAO.findLikeCode(areaCode);
	}
	
	public List findAllArea() {
		return areaDAO.getAll();
	}

	/** 增加一个权限 */
	public void addAuthorization(Authorization authorization) {
		authorizationDAO.add(authorization);
	}

	/** 查找一个权限 */
	public Authorization findAuthorization(String id) {
		return authorizationDAO.find(id);
	}

	/** 删除一个权限 */
	public void deleteAuthorization(Authorization authorization) {
		authorizationDAO.delete(authorization);
	}

	/** 修改一个权限 */
	public void updateAuthorization(Authorization authorization) {
		authorizationDAO.update(authorization);
	}

	/** 查找一个权限通过模块名* */
	public Authorization findAuthorizationBymoudleName(String moudleName,
			String childMoudleName, String userId) {
		return authorizationDAO.findBymoudleName(moudleName, childMoudleName,
				userId);
	}

	/** 增加一个员工 */
	public void addEmployee(Employee employee) {
		employeeDAO.add(employee);
	}

	/** 查找一个员工 */
	public Employee findEmployee(String id) {
		return employeeDAO.find(id);
	}

	/** 删除一个员工 */
	public void deleteEmployee(Employee employee) {
		employeeDAO.delete(employee);
	}

	/** 修改一个员工 */
	public void updateEmployee(Employee employee) {
		employeeDAO.update(employee);
	}

	/** *查找一个area通过name */
	public Employee findEmployeeByName(String name) {
		return employeeDAO.findByName(name);
	}

	/** 增加一个宠物 */
	public void addPet(Pet pet) {
		petDAO.add(pet);
	}

	/** 查找一个宠物 */
	public Pet findPet(String id) {
		return petDAO.find(id);
	}

	/** 删除一个宠物 */
	public void deletePet(Pet pet) {
		petDAO.delete(pet);
	}

	/** 修改一个宠物对象 */
	public void updatePet(Pet pet) {
		petDAO.update(pet);
	}

	/** 通过芯片号码找宠物* */
	public Pet findPetByChipNo(String chipNo) {
		return petDAO.findPetByChipNo(chipNo);
	}

	/** 通过宠物编号找宠物* */
	public Pet findPetByPetNo(String petNo, String petId) {
		return petDAO.findPetByPetNo(petNo, petId);
	}

	/** 通过宠物编号找宠物* */
	public Pet findPetByPetNo(String petNo) {
		return petDAO.findPetByPetNo(petNo);
	}
	
	/** 通过areacode找宠物* */
	public List findPetListByAreaCode(String areaCode){
	    return petDAO.findPetListByAreaCode(areaCode);
	}

	/** 增加一个宠物主人* */
	public void addPetOwner(PetOwner petOwner) {
		petOwnerDAO.add(petOwner);
	}

	/** 查找一个宠物主人* */
	public PetOwner findPetOwner(String id) {
		return petOwnerDAO.find(id);
	}

	/** 删除一个宠物主人* */
	public void deletePetOwner(PetOwner petOwner) {
		petOwnerDAO.delete(petOwner);
	}

	/** 通过身份证查找一个宠物主人, */
	public PetOwner findPetOwnerByResidentID(String residentID) {
		return petOwnerDAO.findByResidentID(residentID);
	}

	/** 修改一个宠物对象* */
	public void updatePetOwner(PetOwner petOwner) {
		petOwnerDAO.update(petOwner);
	}

	/** 增加一个RfidChip* */
	public void addRfidChip(RfidChip rfidChip) {
		rfidChipDAO.add(rfidChip);
	}

	/** 查找一个RfidChip对象* */
	public RfidChip findRfidChip(String id) {
		return rfidChipDAO.find(id);
	}

	/** 查找一个RfidChip对象* */
	public RfidChip findRfidChipbyCode(String chipCode) {
		return rfidChipDAO.findbyChiCode(chipCode);
	}

	/** 删除一个RfidChip对象* */
	public void deleteRfidChip(RfidChip rfidChip) {
		rfidChipDAO.delete(rfidChip);
	}

	/** 修改一个RfidChip对象* */
	public void updateRfidChip(RfidChip rfidChip) {
		rfidChipDAO.update(rfidChip);
	}

	/** 增加一个看病记录对象* */
	public void addTreatmentRecord(TreatmentRecord treatmentRecord) {
		treatmentRecordDAO.add(treatmentRecord);
	}

	/** 查找一个看病记录对象* */
	public TreatmentRecord findTreatmentRecord(String id) {
		return treatmentRecordDAO.find(id);
	}

	/** 删除一个看病记录对象* */
	public void deleteTreatmentRecord(TreatmentRecord treatmentRecord) {
		treatmentRecordDAO.delete(treatmentRecord);
	}

	/** 修改一个看病记录对象* */
	public void updateTreatmentRecord(TreatmentRecord treatmentRecord) {
		treatmentRecordDAO.update(treatmentRecord);
	}

	/** 增加一个疫苗记录* */
	public void addVaccine(Vaccine vaccine) {
		vaccineDAO.add(vaccine);
	}

	/** 查找一个疫苗记录对象* */
	public Vaccine findVaccine(String id) {
		return vaccineDAO.find(id);
	}

	/** 删除一个疫苗记录对象* */
	public void deleteVaccine(Vaccine vaccine) {
		vaccineDAO.delete(vaccine);
	}

	/** 修改一个疫苗记录对象* */
	public void updateVaccine(Vaccine vaccine) {
		vaccineDAO.update(vaccine);
	}

	/** 增加address对象* */
	public void addAddress(Address address) {
		addressDAO.add(address);
	}

	/** 修改address对象* */
	public void updateAddress(Address address) {
		addressDAO.edit(address);
	}

	/** 删除address对象* */
	public void deleteAddress(Address address) {
		addressDAO.delete(address);
	}

	/** 增加电话对象* */
	public void addPhone(Phone phone) {
		phoneDAO.add(phone);
	}

	/** 修改电话对象* */
	public void updatePhone(Phone phone) {
		phoneDAO.edit(phone);
	}

	/** 删除电话对象* */
	public void deletePhone(Phone phone) {
		phoneDAO.delete(phone);
	}

	/** 增加图片对象* */
	public void addImage(Image image) {
		imageDAO.add(image);
	}

	/** 修改图片对象* */
	public void updateImage(Image image) {
		imageDAO.update(image);
	}

	/** 删除图片对象* */
	public void deleteImage(Image image) {
		imageDAO.delete(image);
	}

	/** 查找图片对象* */
	public Image findImage(String id) {
		return imageDAO.find(id);
	}

	/**增加一个发送信息*/
    public void addSmsSend(SmsSend smsSend){
        smsSendDAO.add(smsSend);
    }	
	
	/**
	 * @return 返回 userDAO。
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 *            要设置的 userDAO。
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return 返回 areaDAO。
	 */
	public AreaDAO getAreaDAO() {
		return areaDAO;
	}

	/**
	 * @param areaDAO
	 *            要设置的 areaDAO。
	 */
	public void setAreaDAO(AreaDAO areaDAO) {
		this.areaDAO = areaDAO;
	}

	/**
	 * @return 返回 authorizationDAO。
	 */

	public AuthorizationDAO getAuthorizationDAO() {
		return authorizationDAO;
	}

	/**
	 * @param authorizationDAO
	 *            要设置的 authorizationDAO。
	 */

	public void setAuthorizationDAO(AuthorizationDAO authorizationDAO) {
		this.authorizationDAO = authorizationDAO;
	}

	/**
	 * @return 返回 employeeDAO。
	 */

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	/**
	 * @param employeeDAO
	 *            要设置的 employeeDAO。
	 */
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	/**
	 * @return 返回 petDAO。
	 */
	public PetDAO getPetDAO() {
		return petDAO;
	}

	/**
	 * @param petDAO
	 *            要设置的 petDAO。
	 */
	public void setPetDAO(PetDAO petDAO) {
		this.petDAO = petDAO;
	}

	/**
	 * @param petOwner
	 *            要设置的 petOwner。
	 */
	public void setPetOwnerDAO(PetOwnerDAO petOwnerDAO) {
		this.petOwnerDAO = petOwnerDAO;
	}

	/**
	 * @return 返回 rfidChipDAO。
	 */
	public RfidChipDAO getRfidChipDAO() {
		return rfidChipDAO;
	}

	/**
	 * @param rfidChipDAO
	 *            要设置的 rfidChipDAO。
	 */
	public void setRfidChipDAO(RfidChipDAO rfidChipDAO) {
		this.rfidChipDAO = rfidChipDAO;
	}

	/**
	 * @return 返回 treatmentRecordDAO。
	 */
	public TreatmentRecordDAO getTreatmentRecordDAO() {
		return treatmentRecordDAO;
	}

	/**
	 * @param treatmentRecordDAO
	 *            要设置的 treatmentRecordDAO。
	 */
	public void setTreatmentRecordDAO(TreatmentRecordDAO treatmentRecordDAO) {
		this.treatmentRecordDAO = treatmentRecordDAO;
	}

	/**
	 * @return 返回 vaccineDAO。
	 */
	public VaccineDAO getVaccineDAO() {
		return vaccineDAO;
	}

	/**
	 * @param vaccineDAO
	 *            要设置的 vaccineDAO。
	 */
	public void setVaccineDAO(VaccineDAO vaccineDAO) {
		this.vaccineDAO = vaccineDAO;
	}

	/**
	 * @return 返回 phoneDAO。
	 */
	public PhoneDAO getPhoneDAO() {
		return phoneDAO;
	}

	/**
	 * @param phoneDAO
	 *            要设置的 phoneDAO。
	 */
	public void setPhoneDAO(PhoneDAO phoneDAO) {
		this.phoneDAO = phoneDAO;
	}

	/**
	 * @return 返回 petOwnerDAO。
	 */
	public PetOwnerDAO getPetOwnerDAO() {
		return petOwnerDAO;
	}

	/**
	 * @return 返回 addressDAO。
	 */
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	/**
	 * @param addressDAO
	 *            要设置的 addressDAO。
	 */
	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	/**
	 * @return 返回 imageDAO。
	 */
	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	/**
	 * @param imageDAO
	 *            要设置的 imageDAO。
	 */
	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}
    
	
	
    /**
     * @return 返回 smsSendDAO。
     */
    public SmsSendDAO getSmsSendDAO() {
        return smsSendDAO;
    }
    /**
     * @param smsSendDAO 要设置的 smsSendDAO。
     */
    public void setSmsSendDAO(SmsSendDAO smsSendDAO) {
        this.smsSendDAO = smsSendDAO;
    }
	/** news 的所有操作* */

	public void addNews(News news) {
		newsDAO.add(news);
	}

	public void deleteNews(News news) {
		newsDAO.delete(news);
	}

	public News findNews(String id) {
		return newsDAO.find(id);
	}

	public List getAllNews() {
		return newsDAO.getAll();
	}

	public List getTopNews(int count) {
		return newsDAO.getTop(count);
	}

	public void updateNews(News news) {
		newsDAO.update(news);
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public void addSms(Sms sms) {
		smsDAO.add(sms);
	}

	public Sms findSms(String id) {
		return smsDAO.find(id);
	}
    
	
	 /**通过发送状态*/
    public List findSmsBySendState(String sendState,String sendDate){
        return smsDAO.findSms(sendState,sendDate);
    }
	
	public List getAllSms() {
		return smsDAO.getAll();
	}
    
	public SmsDAO getSmsDAO() {
		return smsDAO;
	}

	public void setSmsDAO(SmsDAO smsDAO) {
		this.smsDAO = smsDAO;
	}

	public void updateSms(Sms sms) {
		smsDAO.update(sms);
	}
	
    public void deleteSms(Sms sms){
    	smsDAO.delete(sms);
    }
	
	public void addVariety(Variety variety) {
		varietyDAO.add(variety);

	}

	public void deleteVariety(Variety variety) {
		varietyDAO.delete(variety);
	}

	public Variety findVariety(String id) {
		return varietyDAO.find(id);
	}

	public List getAllVariety() {
		return varietyDAO.getAll();
	}

	public void updateVariety(Variety variety) {
		varietyDAO.update(variety);

	}

	public VarietyDAO getVarietyDAO() {
		return varietyDAO;
	}

	public void setVarietyDAO(VarietyDAO varietyDAO) {
		this.varietyDAO = varietyDAO;
	}
	
    public Variety findVarietyByName(String name){
    	return varietyDAO.findByName(name);
    }

}
