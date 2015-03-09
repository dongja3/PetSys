//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/RfidChip.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

import com.drategy.pets.domain.Employee;


/**
* 系统的rfidchip
* @author Jason Jiang
* @author $Author: jackie.dong $
* @$Revision: 1.2 $
*/
public class RfidChip {
	 //正在使用
    public static final int USING_YES = 1 ;
    //停止使用
    public static final int USING_NO = 0;
    
    
    /**
     * 对应的猪对象
     */    
    public static final String 	TYPE_PIG = "pig" ;
    /**
     * 对应的宠物
     */
    public static final String 	TYPE_PET = "pet" ;
    /**
     * 对应的奶牛
     */
    public static final String 	TYPE_COW = "cow" ;
    /**
     * 对应的工作人员
     */
    public static final String 	TYPE_EMPLOYEE = "employee" ;
        
    /**
     * id 是主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 芯片的内部号码
     */
    private String code;
    /**
     * 芯片的类型
     */
    private String type;

    /**
     *当前使用次数 
     */
    private String startDate;
    
    /**
     * 对应的人员
     */
    private Employee employee;
    
    /**
     * 描述
     */
    private String description;    
    /**
     * 是否正在使用
     */
    private int using;
        
    public void setUsing(int using){
        this.using = using;
    }
    
    public int getUsing(){
        return this.using ;
    }
            
        public void setStartDate(String startDate){
            this.startDate = startDate ;            
        }
        
        public String getStartDate(){
            return this.startDate;
        }     
      
		public String getCode() {
		    return code;
		}
		public String getDescription() {
		    return description;
		}
		public String getId() {
		    return id;
		}
		public String getName() {
		    return name;
		}
		public Employee getEmployee() {
		    return employee;
		}
	
	  public String getType() {
	    return type;
	  }
	  public void setType(String type) {
	    this.type = type;
	  }
	
	  public void setEmployee(Employee employee) {
	    this.employee = employee;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public void setId(String id) {
	    this.id = id;
	  }
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  public void setCode(String code) {
	    this.code = code;
	  }
	}
