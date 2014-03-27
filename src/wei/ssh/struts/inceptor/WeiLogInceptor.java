package wei.ssh.struts.inceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class WeiLogInceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5710480952672099580L;
	
	private static final Logger log=LoggerFactory.getLogger(WeiLogInceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String name=invocation.getInvocationContext().getName();
		log.info("name:"+name);
		return invocation.invoke();
	}

}
