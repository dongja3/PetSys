//$Source: /petSys/petSys/src/java/com/drategy/pets/action/InitAuthorizationConfigAction.java,v $
//LasterModified By:$Author: jason.jiang $
//$Date $
package com.drategy.pets.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import com.drategy.pets.context.Global;
import com.drategy.pets.domain.User;
import com.drategy.pets.springservice.StructService;

/**
 * InitAuthorizationConfigAction Action
 * @author Jason Jiang 
 * @author $Author: jason.jiang $
 * @$Revision: 1.2 $
 */
public class InitAuthorizationConfigAction extends BaseDispatchAction{
    public ActionForward execute(ActionMapping map, ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response){
        /**取得request参数**/
        String userId = request.getParameter("userId");
        
        /**创建服务**/
        StructService securityService = (StructService)Global.getInstance().getService("structService");
        
        User user = securityService.findUser(userId);
        java.util.Set authorizationSet = user.getAuthorizationSet();  
        
        /**保存参数**/
        request.setAttribute("user",user);
        request.setAttribute("employeeId",user.getEmployee().getId());
        request.setAttribute("authorizationSet",authorizationSet);        
        
        /**页面转向**/
        return map.findForward("success");  
        
    }    
}
