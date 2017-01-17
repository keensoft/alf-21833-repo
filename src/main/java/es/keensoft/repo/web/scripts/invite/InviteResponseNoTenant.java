package es.keensoft.repo.web.scripts.invite;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.repo.web.scripts.invite.InviteResponse;
import org.alfresco.service.cmr.invitation.Invitation;
import org.alfresco.service.cmr.invitation.InvitationExceptionForbidden;
import org.alfresco.service.cmr.invitation.InvitationExceptionUserError;
import org.alfresco.service.cmr.invitation.InvitationService;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class InviteResponseNoTenant extends InviteResponse {
	
    private static final String RESPONSE_ACCEPT = "accept";
    private static final String RESPONSE_REJECT = "reject";
    private static final String MODEL_PROP_KEY_RESPONSE = "response";
    private static final String MODEL_PROP_KEY_SITE_SHORT_NAME = "siteShortName";
    
    // properties for services
    private InvitationService invitationService;
    private TenantService tenantService;
    
    
    public void setInvitationService(InvitationService invitationService)
    {
        this.invitationService = invitationService;
    }
    
    public void setTenantService(TenantService tenantService)
    {
        this.tenantService = tenantService;
    }
    
    @Override
	protected Map<String, Object> executeImpl(final WebScriptRequest req, final Status status) {
		
        // Alfresco original behaviour
		if (tenantService.isEnabled()) {
        	return super.executeImpl(req, status);
        }
		
        // Run as system user
        return AuthenticationUtil.runAsSystem(new RunAsWork<Map<String, Object>>() {
        	
			public Map<String, Object> doWork() throws Exception {
                return execute(req, status);
            }
            
        });
		
	}	
    
    private Map<String, Object> execute(WebScriptRequest req, Status status)
    {
        // initialise model to pass on for template to render
        Map<String, Object> model = new HashMap<String, Object>();
        
        String inviteId = req.getServiceMatch().getTemplateVars().get("inviteId");
        String inviteTicket = req.getServiceMatch().getTemplateVars().get("inviteTicket");
               
        // Check that the task is still open.
        //if(inviteStart)
        
        // process response
        String action = req.getServiceMatch().getTemplateVars().get("action");
        if (action.equals("accept"))
        {
            try
            {
                Invitation invitation = invitationService.accept(inviteId, inviteTicket);
                // add model properties for template to render
                model.put(MODEL_PROP_KEY_RESPONSE, RESPONSE_ACCEPT);
                model.put(MODEL_PROP_KEY_SITE_SHORT_NAME, invitation.getResourceName());
            }
            catch (InvitationExceptionForbidden fe)
            {
                throw new WebScriptException(Status.STATUS_FORBIDDEN, fe.toString());
            }
            catch (InvitationExceptionUserError fe)
            {
                throw new WebScriptException(Status.STATUS_CONFLICT, fe.toString());
            }
        }
        else if (action.equals("reject"))
        {
            try 
            {
                Invitation invitation = invitationService.reject(inviteId, "Rejected");
                // add model properties for template to render
                model.put(MODEL_PROP_KEY_RESPONSE, RESPONSE_REJECT);
                model.put(MODEL_PROP_KEY_SITE_SHORT_NAME, invitation.getResourceName());
            }
            catch (InvitationExceptionForbidden fe)
            {
                throw new WebScriptException(Status.STATUS_FORBIDDEN, fe.toString());
            }
            catch (InvitationExceptionUserError fe)
            {
                throw new WebScriptException(Status.STATUS_CONFLICT, fe.toString());
            }
        }
        else
        {
            /* handle unrecognised method */
            throw new WebScriptException(Status.STATUS_BAD_REQUEST,
                    "action " + action + " is not supported by this webscript.");
        }
        
        return model;
    }

}
