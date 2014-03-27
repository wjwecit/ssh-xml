/**
 * 
 */
package wei.ssh.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wei.ssh.model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.ConversionType;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * @author wei
 *
 */
@ParentPackage(value = "annodefault")
@Namespace("/")
@Action(value="conversion", results = {
		@Result(name = "success", location = "/success.jsp"),
		@Result(name = "error", location = "/error.jsp"),
		@Result(name = "input", location = "/login.jsp") })
public class TypeConversionTestAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4273562503629462596L;
	
	private static final Logger log=LoggerFactory.getLogger(TypeConversionTestAction.class);
	private String name;
	
	private User user;
	
	

	public String execute(){
		
		log.info("转化 action 执行完成! name:"+name+";user:"+user);
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser() {
		return user;
	}

	@TypeConversion(key = "user",type=ConversionType.CLASS, converter = "wei.ssh.converter.UserConverter")
	public void setUser(User user) {
		this.user = user;
	}
}
