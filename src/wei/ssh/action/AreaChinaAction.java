/**
 * 
 */
package wei.ssh.action;

import java.util.List;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import wei.ssh.model.AreaChina;
import wei.ssh.model.UserInfo;
import wei.ssh.service.IService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author wei
 *
 */
@Repository("areaChina")
@Namespace("/")
@ParentPackage(value = "annodefault")
public class AreaChinaAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 534218693058651880L;

	private static final Logger log=LoggerFactory.getLogger(AreaChinaAction.class);
	
	private int code;
	
	@Resource(name="queryService")	
	private IService service;
	
	private List<UserInfo> userInfos;
	
	@Action(value="updateAC",results={@Result(name="success", type = "json", params = {
			"encoding", "UTF-8", "statusCode", "200", "noCache", "true",
			"enableGZIP", "true" })})
	public String updateAC() throws Exception {		
		AreaChina ac=service.getAreaChina(755);
		ac.setAreaName(String.valueOf(Math.random()));
		service.updateViaHibernate(ac);
		log.info("updateAC completed!!!");
		return SUCCESS;
	}
	
	@Action(value="fu",results={@Result(name="success", type = "json", params = {
			"encoding", "UTF-8", "statusCode", "200", "noCache", "true",
			"enableGZIP", "true" })})
	public String fetchAllUserInfo() throws Exception {		
		userInfos=service.getUserInfo();
		log.info("fetchAllUserInfo completed!!!");
		return SUCCESS;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
}
