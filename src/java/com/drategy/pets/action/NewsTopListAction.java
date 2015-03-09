//$Source: /petSys/petSys/src/java/com/drategy/pets/action/NewsTopListAction.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date $
package com.drategy.pets.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.drategy.pets.context.Global;
import com.drategy.pets.springservice.StructService;

/**
* 
* @author Jackie Dong
* @author $Author: jackie.dong $
* @$Revision: 1.1 $
*/
public class NewsTopListAction extends BaseDispatchAction{
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
    	 
    	/**创建服务**/
        StructService structService = (StructService)Global.getInstance().getService("structService");
        
        List newsList = structService.getTopNews(10);
        
        request.setAttribute("newsList",newsList);
        
        //页面转向          
        return mapping.findForward("success");

    }
}