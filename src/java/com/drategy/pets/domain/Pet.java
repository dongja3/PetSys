//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/Pet.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $


package com.drategy.pets.domain;

import java.util.Set;

import com.drategy.pets.bom.Image;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.RfidChip;


/**
* 系统的pet
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.6 $
*/
public class Pet  {
    
      /**性别常量*/
     public static final  String SEX_MALE = "male" ;
     public static final  String SEX_FEMALE = "female" ;
      
	 /**
	    * id 是主键
	    */
	   private String id ;
	   /**
	    * 宠物编号
	    */  
	   private String petNo;
	   /**
	    * 宠物主人
	    */   
	   private PetOwner petOwner;   
	   /**
	    * 发证单位
	    */
	   private String certificateUnit;
	   /**
	    * 发证日期
	    */
	   private String dateIssceed;
	   /**
	    * 宠物名字
	    */
	   private String petName;
	   /**
	    *品种
	    */
	   
	   private Variety variety;
	   /**
	    * 年龄
	    */
	   private String   age;
	   /**
	    * 性别
	    */
	   private String sex;
	   /**
	    * 发色
	    */
	   private String hairColor;
	   /**
	    * 特征
	    */
	   private String character;
	   /**
	    * 用途
	    */
	   private String usage;
	   
	   /**
	    * 宠物的疫苗记录
	    */
	   private java.util.Set vaccineSet; 
	   /**
	    * 宠物的驱虫记录
	    */
	   private Set wormRemoveSet;
	   /**
	    * 宠物照片
	    */
	   private Image petImage;
	   
	   /**
	    * 对应的芯片
	    */
	   private RfidChip rfidChip;
	   /**
	    * 对应的看病记录
	    */   
	   private java.util.Set treatmentRecordSet;
	   /**
	    * 宠物的父亲
	    */
	   private String father;
	   /**
	    * 宠物的母亲
	    */
	   private String mother;
	   /**
	    * 引进地
	    */
	   private String importAddr;  
	   /**
	    * 相关证书
	    */
	   private Image certificate;
	   
	   public Image getCertificate() {
	    return certificate;
	   }
	   public String getFather() {
	    return father;
	   }
	   public String getImportAddr() {
	    return importAddr;
	   }
	   public String getMother() {
	    return mother;
	   }
	   public void setMother(String mother) {
	    this.mother = mother;
	   }
	   public void setImportAddr(String importAddr) {
	    this.importAddr = importAddr;
	   }
	   public void setFather(String father) {
	    this.father = father;
	   }
	   
	   public void setCertificate(Image certificate) {
	    this.certificate = certificate;
	   }
	  
	   public java.util.Set getTreatmentRecordSet() {
	       return treatmentRecordSet;
	   }
	   
	   public void setTreatmentRecordSet(java.util.Set treatmentRecordSet) {
	       this.treatmentRecordSet = treatmentRecordSet;
	   }
	   
	   public Image getPetImage() {
	       return this.petImage;
	   }
	  
	   public void setPetImage(Image petImage) {  
	       this.petImage = petImage;
	   }
	     
	   public void setRfidChip(RfidChip rfidChip){
	       this.rfidChip = rfidChip ;
	       
	   }
	   
	   public RfidChip getRfidChip(){
	       return this.rfidChip;
	   }
	   
	   public String getCertificateUnit() {
	       return certificateUnit;
	   }
	   
	   public String getCharacter() {
	       return character;
	   }
	   public String getDateIssceed() {
	      return dateIssceed;
	   }
	   public String getHairColor() {
	       return hairColor;
	   }
	   public String getId() {
	       return id;
	   }
	     
	     public String getPetName() {
	       return petName;
	     }
	     public String getSex() {
	       return sex;
	     }
	     public String getPetNo() {
	       return petNo;
	     }
	     public String getUsage() {
	       return usage;
	     }
	     public java.util.Set getVaccineSet() {
	       return vaccineSet;
	     }
	     public Set getWormRemoveSet() {
	       return wormRemoveSet;
	     }
	     public void setWormRemoveSet(Set wormRemoveSet) {
	       this.wormRemoveSet = wormRemoveSet;
	     }
	     public void setVaccineSet(java.util.Set vaccineSet) {
	       this.vaccineSet = vaccineSet;
	     }
	     public void setUsage(String usage) {
	       this.usage = usage;
	     }
	     public void setSex(String sex) {
	       this.sex = sex;
	     }
	     public void setPetNo(String petNo) {
	       this.petNo = petNo;
	     }
	     public void setPetName(String petName) {
	       this.petName = petName;
	     }
	     
	     public void setId(String id) {
	       this.id = id;
	     }
	     public void setHairColor(String hairColor) {
	       this.hairColor = hairColor;
	     }
	     public void setDateIssceed(String dateIssceed) {
	       this.dateIssceed = dateIssceed;
	     }
	     public void setCharacter(String character) {
	       this.character = character;
	     }
	     public void setCertificateUnit(String certificateUnit) {
	       this.certificateUnit = certificateUnit;
	     }
	     public void setAge(String age) {
	       this.age = age;
	     }
	     public String getAge() {
	       return age;
	     }
	     
	     public void setPetOwner(PetOwner petOwner){
	         this.petOwner = petOwner;
	     }
	     
	     public PetOwner getPetOwner(){
	         return this.petOwner;
	     }
		public Variety getVariety() {
			return variety;
		}
		public void setVariety(Variety variety) {
			this.variety = variety;
		}
		
		

}