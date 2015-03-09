//$Source: /petSys/petSys/src/java/com/drategy/pets/biz/InsertTestData.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.biz;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.drategy.pets.bom.Address;
import com.drategy.pets.bom.Image;
import com.drategy.pets.bom.Phone;
import com.drategy.pets.context.Configuration;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.Area;
import com.drategy.pets.domain.Pet;
import com.drategy.pets.domain.PetOwner;
import com.drategy.pets.domain.RfidChip;
import com.drategy.pets.domain.TreatmentRecord;
import com.drategy.pets.domain.Vaccine;
import com.drategy.pets.domain.Variety;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.DateTools;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;

/**
 * 此类主要功能是插入测试数据.在每一个区域里插入一个宠物主人信息,
 * 每一个宠物主人下完成一个宠物,一个宠物完成三次,疫苗记录,三看病记录.
 * @author Jason.jiang
 * @author $Author: jackie.dong $
 * @version   $Revision: 1.2 $
 */
public class InsertTestData {
    
    //一个StructService服务
    public StructService structService;
    
    //用于宠物主人名字
    public int intSum = 0 ; 
    
    //用于宠物数目
    public int intPetSum = 0 ;

    /**
     * 构造函数
     */
    public InsertTestData() {
        
        // 创建Struct服务
        structService = (StructService)Global.getInstance().getService("structService");
        
    } 
    
    /**
     * 插入测试数据入口
     */
    public  void insertData(){      
        
        //等到最高area的areacode
        String areaCode = Configuration.getAppConfig().findConfigs("areaNote").find("topArea");
        
        //得到省节点
        Area topArea = structService.findAreaByCode(areaCode);
        
        //得到市级节点
        Set  cityArea = topArea.getChilds();
        
        //各个区域节点
        Iterator cityItem = cityArea.iterator();
        
        //循环得到市级节点
        while(cityItem.hasNext()){
            
            //得到具体市级的节点
            Area curCity = (Area)cityItem.next();
            
            //读出区域级节点
            Set areaSet =  curCity.getChilds();
            
            //循环得到区域节点
            Iterator areaItem = areaSet.iterator();
            
            while(areaItem.hasNext()){
                
                //得到具体的区域节点
                Area   currCityArea = (Area)areaItem.next();
                
                //在这个区域里插入宠物主人信息
                insertPetOwner(currCityArea);
            }
            
        } 
    }
    
    /**
     * 在每一个区域里插入1000
     * 个宠物主人信息
     * @param currArea
     */
    public void insertPetOwner(Area currArea){
        //循环插入1000宠物主人记录
        for(int i=0;i<1000;i++){
            intSum++;
            PetOwner petOwner = new PetOwner();
            petOwner.setId(Tools.genResourceId(""));
            petOwner.setName("petOwnerName"+intSum);
            petOwner.setResidentID(Tools.genResourceId("").substring(14,32));
            petOwner.setAge(DateTools.getStandardYearMonth());
            
            //是否被2整除,是的话就雌
            if(intSum %2 == 0){
                petOwner.setSex("female");
            }else{
                petOwner.setSex("male");
            }
            
            petOwner.setSmsService("N");
            petOwner.setArea(currArea);
            
            //创建对象    
            java.util.Set addrSet = new HashSet() ;
            
            //创建地址
            Address address = new Address();
            address.setDetailAddress("杭州市"+currArea.getName());
            address.setId(Tools.genResourceId(""));
            structService.addAddress(address);
            addrSet.add(address );
            
            //创建联系电话
            Phone phone = new Phone();   
            phone.setCompanyTelephone("0571-99896589");
            phone.setHomeTelephone("0571-99896589");
            phone.setMobileTelephone("135789465213");
            phone.setId(Tools.genResourceId(""));
            
            //保存对象	       
            petOwner.setPhone(phone);
            petOwner.setAddrSet(addrSet);
            structService.addPhone(phone);
            
            //保存对象
            structService.addPetOwner(petOwner);
            SystemLogger.info("增加宠物主人第"+intSum+"个");
            
            //在宠物主人下插入宠物信息
            insertPet(petOwner);
        }
        
    }
    
    /**
     * 在一个宠物主人下插入2个宠物信息
     * @param petOwner
     */
    public void insertPet(PetOwner petOwner){
        
        
        for(int i =0 ;i<2;i++){
            
            intPetSum ++;
            
	        //创建宠物对象
	        Pet curPet = new Pet();
	        
	        //设置属性
	        curPet.setAge("2");
	        curPet.setCertificateUnit("杭州市畜牧局");
	        curPet.setCharacter("可爱");
	        curPet.setDateIssceed(DateTools.getStandardYearMonthDay());
	        curPet.setHairColor("");
	        curPet.setId(Tools.genResourceId(""));
	        curPet.setPetName("宝宝"+intPetSum);
	        curPet.setPetNo(Tools.genResourceId("").substring(16,32));
	        curPet.setPetOwner(petOwner);	        
	        
            //是否被2整除,是的话就雌
            if(intPetSum %2 == 0){
                curPet.setSex("female");            
            }else{
                curPet.setSex("male");              
            }
	        
	        //创建芯片对象
	        RfidChip rfidChip = new RfidChip();        
	        rfidChip.setId(Tools.genResourceId(""));
	        rfidChip.setCode(Tools.genResourceId("").substring(16,32));
	        
	        //设置芯片对象
	        curPet.setRfidChip(rfidChip);
	        
	        //创建品种对象
	        Variety variety = null;
	        switch(intPetSum % 14){
	        	case 0:
	        	    variety = structService.findVarietyByName("苏格兰牧羊犬"); 
	        	    break;
	        	case 1:
	        	    variety = structService.findVarietyByName("喜乐蒂");
	        	    break;
	        	case 2:
	        	    variety = structService.findVarietyByName("哈士奇");
	        	    break;
	        	case 3:
	        	    variety = structService.findVarietyByName("斑点狗");
	        	    break;
	        	case 4:
	        	    variety = structService.findVarietyByName("拉布拉多犬");
	        	    break;
	        	case 5:
	        	    variety = structService.findVarietyByName("金毛犬");
	        	    break;
	        	case 6:
	        	    variety = structService.findVarietyByName("比熊犬");
	        	    break;
	        	case 7:
	        	    variety = structService.findVarietyByName("可卡犬");
	        	    break;
	        	case 8:
	        	    variety = structService.findVarietyByName("京巴狗");
	        	    break;
	        	case 9:
	        	    variety = structService.findVarietyByName("德国牧羊犬");
	        	    break;
	        	case 10:
	        	    variety = structService.findVarietyByName("博美犬");
	        	    break;
	        	case 11:
	        	    variety = structService.findVarietyByName("日本尖嘴");
	        	    break;
	        	case 12:
	        	    variety = structService.findVarietyByName("约克夏");
	        	    break;	
	        	default:
	        	    variety = structService.findVarietyByName("约克夏");
	        	    
	        }
	          
	        SystemLogger.info("%14="+intPetSum % 14);
	        
	        //设置宠物的品种
	        curPet.setVariety(variety);
	        
	        //创建宠物照片
	        Image petImage = new Image();	        
	        petImage.setId(Tools.genResourceId(""));
	        
	        curPet.setPetImage(petImage);
	        
	        //创建宠物证书
	        Image petCertificate = new Image();	        
	        petCertificate.setId(Tools.genResourceId(""));
	        
	        //设置宠物证书
	        curPet.setCertificate(petCertificate);	        
	        
	        //保存对象
	        structService.addImage(petCertificate);
	        structService.addImage(petImage);
	        structService.addRfidChip(rfidChip);
	        structService.addPet(curPet);
	        
	        //增加看病记录
	        insertTreatmentRecord(curPet);
	        
	        //增加疫苗记录
	        insertVaccine(curPet);
	        
        }
        
    }
    
    /***
     * 在一个宠物下插入两条疫苗记录
     * @param pet
     */
    public void insertVaccine(Pet pet){
        int ii =100 ;
        
        if( intPetSum % 2 ==0){
        
	        //插入三条疫苗记录
	        for(int i=0;i<3;i++){
	            
	            //创建疫苗对象
	            Vaccine vaccine = new Vaccine();
	            
	            //设置属性
	            switch(i){
	               case 0:
	                   vaccine.setName("犬瘟热");
	                   break;
	               case 1:
	                   vaccine.setName("犬窝咳苗");
	                   break;
	               case 2:
	                   vaccine.setName("狂犬病");
	                   break;
	            }
	            
	         
	            ii = ii+1;
	            
	            vaccine.setBatchNo(String.valueOf(ii));
	            vaccine.setId(Tools.genResourceId(""));
	            vaccine.setInjectDate(DateTools.getStandardYearMonthDay());
	            vaccine.setActionLocation("body");
	            
	            //设置对象
	            vaccine.setPet(pet);
	            
	            //保存对象
	            structService.addVaccine(vaccine);          
	            
	        }
        }
        
        
    }
    
    /**
     * 在一个宠物下插入两条看病记录
     * @param pet
     */
    public void insertTreatmentRecord(Pet pet){
        
        //插入三条疫苗记录
        for(int i=0;i<3;i++){
            
            //创建疫苗对象
            TreatmentRecord treatmentRecord = new TreatmentRecord();
            
            //设置属性
            String currDate = "";
            
            currDate = DateTools.addDate(Integer.parseInt(DateTools.getYear()),Integer.parseInt(DateTools.getMonth()),Integer.parseInt(DateTools.getDay()),(i+1)*10);
            
            treatmentRecord.setId(Tools.genResourceId(""));
            treatmentRecord.setDate(currDate); 
            treatmentRecord.setVisitDoctorTime(i+1);
            treatmentRecord.setDescription("符合犬绦虫病表现");
            treatmentRecord.setDiseaseHistory("有过病史");
            treatmentRecord.setDiseaseState("好转");
            
            //设置对象
            treatmentRecord.setPet(pet);
            
            //保存对象
            structService.addTreatmentRecord(treatmentRecord);          
            
        }
    }

}
