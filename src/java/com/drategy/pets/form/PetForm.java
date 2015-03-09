//$Source: /petSys/petSys/src/java/com/drategy/pets/form/PetForm.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/02/22 08:44:38 $

package com.drategy.pets.form;

import org.apache.struts.upload.FormFile;

/**
 * 宠物注册 Form
 * 
 * @author Jackie Dong
 * @author $Author: jason.jiang $
 * @$Revision: 1.6 $
 */

public class PetForm extends BaseForm {
    
	private static final long serialVersionUID = 1L;

	private String id = null;
	
	private String method = null;

	private String petName = null;

	private String petNo = null;

	private String certificateUnit = null;

	private String dateIssceed = null;

	private String varietyName = null;

	private String age = null;

	private String sex = null;

	private String hairColor = null;
	
	private String character=null;

	private String usage = null;

	private String rfidNo = null;

	private FormFile petImageUpFile = null;	
	
	private String petImageId ;
  
	private String father = null;

	private String mother = null;

	private String importAddr = null;

	private FormFile petCertificate = null;
	
    /**
     * @return 返回 petCertificate。
     */
    public FormFile getPetCertificate() {
        return petCertificate;
    }
    /**
     * @param petCertificate 要设置的 petCertificate。
     */
    public void setPetCertificate(FormFile petCertificate) {
        this.petCertificate = petCertificate;
    }
    /**
     * @return 返回 petCertificateId。
     */
    public String getPetCertificateId() {
        return petCertificateId;
    }
    /**
     * @param petCertificateId 要设置的 petCertificateId。
     */
    public void setPetCertificateId(String petCertificateId) {
        this.petCertificateId = petCertificateId;
    }
    /**
     * @return 返回 petImage。
     */
    public FormFile getPetImageUpFile() {
        return petImageUpFile;
    }
    /**
     * @param petImage 要设置的 petImage。
     */
    public void setPetImageUpFile(FormFile petImageUpFile) {
        this.petImageUpFile = petImageUpFile;
    }
    /**
     * @return 返回 petImageId。
     */
    public String getPetImageId() {
        return petImageId;
    }
    /**
     * @param petImageId 要设置的 petImageId。
     */
    public void setPetImageId(String petImageId) {
        this.petImageId = petImageId;
    }
	private String  petCertificateId ;
	
     
    /**
     * @return 返回 petOwnerId。
     */
    public String getPetOwnerId() {
        return petOwnerId;
    }
    /**
     * @param petOwnerId 要设置的 petOwnerId。
     */
    public void setPetOwnerId(String petOwnerId) {
        this.petOwnerId = petOwnerId;
    }
	private String petOwnerId = null ;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}


	public String getCertificateUnit() {
		return certificateUnit;
	}

	public void setCertificateUnit(String certificateUnit) {
		this.certificateUnit = certificateUnit;
	}

	public String getRfidNo() {
		return rfidNo;
	}

	public void setRfidNo(String rfidNo) {
		this.rfidNo = rfidNo;
	}

	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getDateIssceed() {
		return dateIssceed;
	}

	public void setDateIssceed(String dateIssceed) {
		this.dateIssceed = dateIssceed;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	
	public String getImportAddr() {
		return importAddr;
	}

	public void setImportAddr(String importAddr) {
		this.importAddr = importAddr;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetNo() {
		return petNo;
	}

	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

}
