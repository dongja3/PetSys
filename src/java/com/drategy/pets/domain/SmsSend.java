//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/SmsSend.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2006/03/12 02:53:57 $
package com.drategy.pets.domain;

/**
* 短信类
* 
* @author jason.jiang
* @author $Author: jason.jiang $
* @$Revision: 1.2 $
*/

public class SmsSend {
     
	public static final String NOT_SEND="待发送";
	
	public static final String SEND_FINISH="已发送";
	
	public static final String SEND_FAIL="发送失败";
    
     private String id ;
     
     /**发送状态*/
     private String smsSendState ;
     
     /**发送内容*/
     private String smsContext ;
     
     /**发送号码*/
     private String smsNo ;
     
     /**发送次数*/
     private int sendTime;
     
    /**
     * @return 返回 id。
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id 要设置的 id。
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return 返回 smsContext。
     */
    public String getSmsContext() {
        return smsContext;
    }
    /**
     * @param smsContext 要设置的 smsContext。
     */
    public void setSmsContext(String smsContext) {
        this.smsContext = smsContext;
    }
    /**
     * @return 返回 smsNo。
     */
    public String getSmsNo() {
        return smsNo;
    }
    /**
     * @param smsNo 要设置的 smsNo。
     */
    public void setSmsNo(String smsNo) {
        this.smsNo = smsNo;
    }
    /**
     * @return 返回 smsSendState。
     */
    public String getSmsSendState() {
        return smsSendState;
    }
    /**
     * @param smsSendState 要设置的 smsSendState。
     */
    public void setSmsSendState(String smsSendState) {
        this.smsSendState = smsSendState;
    }
    
    
    /**
     * @return 返回 sendTime。
     */
    public int getSendTime() {
        return sendTime;
    }
    /**
     * @param sendTime 要设置的 sendTime。
     */
    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }
}
