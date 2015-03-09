//$Source: /petSys/petSys/src/java/com/drategy/pets/bom/Image.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date: 2005/12/12 09:03:34 $


package com.drategy.pets.bom;

import java.util.Set;


/**
* 系统的图片
* @author Jason Jiang
* @author $Author: jason.jiang $
* @$Revision: 1.1 $
*/
public class Image {
	/**
     * id �����
     */
    private String id;
    /**
     * 名称���
     */
    private String imageName;
    /**
     * �内容��
     */    
    private byte[] imageContent;
    /**
     *  类型����
     */
    private String imageType;
    /**
     * �宠物集���
     */
    private Set  petSet;
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
	 * @return 返回 imageContent。
	 */
	public byte[] getImageContent() {
		return imageContent;
	}
	/**
	 * @param imageContent 要设置的 imageContent。
	 */
	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}
	/**
	 * @return 返回 imageName。
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName 要设置的 imageName。
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return 返回 imageType。
	 */
	public String getImageType() {
		return imageType;
	}
	/**
	 * @param imageType 要设置的 imageType。
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	/**
	 * @return 返回 petSet。
	 */
	public Set getPetSet() {
		return petSet;
	}
	/**
	 * @param petSet 要设置的 petSet。
	 */
	public void setPetSet(Set petSet) {
		this.petSet = petSet;
	}
    
    
   

}
