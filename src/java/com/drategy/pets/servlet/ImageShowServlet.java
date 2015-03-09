//$Source: /petSys/petSys/src/java/com/drategy/pets/servlet/ImageShowServlet.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/03/25 04:18:35 $
package com.drategy.pets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drategy.pets.bom.Image;
import com.drategy.pets.context.Global;
import com.drategy.pets.springservice.StructService;
import com.drategy.pets.util.SystemLogger;
import com.drategy.pets.util.Tools;
/**
* IinitSystemLogger servlet
* @author Jason Jiang
* @author  $
* @$Revision $
*/
public class ImageShowServlet extends HttpServlet {
    
    
    private static final String CONTENT_TYPE = "image/gif";
    private static final String FILE_PATH =  "image\\na.jpg";


    //Initialize global variables
    public void init() throws ServletException {
    
    }

    //Process the HTTP Get request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /**设置输出类型**/
        response.setContentType(this.CONTENT_TYPE);
        
        /**取得imageId参数**/
        String imageId = request.getParameter("imageId");
        
        /**是否null**/
        if(Tools.isNullOrEmpty(imageId)){  
            SystemLogger.error("imageShowServlet imageId is null");   
            /**输出图片**/
	        try{
	            ServletOutputStream sout = response.getOutputStream();               
                sout.write(Tools.getFileAsBytes(request.getRealPath("/")+FILE_PATH)); 
	           
	            sout.flush(); 
	            
	            /***输入完毕，清除缓冲*/
	            sout.close();
	            
	        }catch(Exception ex){
	          SystemLogger.error("imageShowServlet 图片输出错："+ex.toString());
	        } 
        }else{        
	        /**创建服务**/
	        StructService structService = (StructService)Global.getInstance().getService("structService");
	        
	        Image image  = structService.findImage(imageId);
	        
	        /**输出图片**/
	        try{
	            ServletOutputStream sout = response.getOutputStream();
	            if(image.getImageContent() == null){	
	                //SystemLogger.info("filePath:"+request.getRealPath("/")+FILE_PATH);
	                sout.write(Tools.getFileAsBytes(request.getRealPath("/")+FILE_PATH)); 
	            }else{ 
	                sout.write(image.getImageContent()); 
	            }
	            
	            sout.flush(); 
	            
	            /***输入完毕，清除缓冲*/
	            sout.close();
	            
	        }catch(Exception ex){
	          SystemLogger.error("imageShowServlet 图片输出错："+ex.toString());
	        } 
        }
 
      }

    //Clean up resources
    public void destroy() {
        
    }

}
