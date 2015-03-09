//$Source: /petSys/petSys/src/java/com/drategy/pets/bom/Address.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 09:03:34 $


package com.drategy.pets.bom;

/**
* 系统的地址
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class Address {
    /**
     * id �����
     */
    public String id;
    
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 城市��
     */
    private String city;
    /**
     * 城区��
     */
    private String citySection;
    /**
     * 县
     */
    private String county;
    /**
     * 镇
     */
    private String town;
    /**
     * 乡镇
     */
    private String village;
    /**
     * 详细地址�
     */
    private String detailAddress;  
    
	/**
	 * @return 返回 city。
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city 要设置的 city。
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return 返回 citySection。
	 */
	public String getCitySection() {
		return citySection;
	}
	/**
	 * @param citySection 要设置的 citySection。
	 */
	public void setCitySection(String citySection) {
		this.citySection = citySection;
	}
	/**
	 * @return 返回 country。
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country 要设置的 country。
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return 返回 county。
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county 要设置的 county。
	 */
	public void setCounty(String county) {
		this.county = county;
	}
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
	 * @return 返回 province。
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province 要设置的 province。
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return 返回 street。
	 */
	public String getDetailAddress() {
		return detailAddress;
	}
	/**
	 * @param street 要设置的 street。
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	/**
	 * @return 返回 town。
	 */
	public String getTown() {
		return town;
	}
	/**
	 * @param town 要设置的 town。
	 */
	public void setTown(String town) {
		this.town = town;
	}
	/**
	 * @return 返回 village。
	 */
	public String getVillage() {
		return village;
	}
	/**
	 * @param village 要设置的 village。
	 */
	public void setVillage(String village) {
		this.village = village;
	}
}
