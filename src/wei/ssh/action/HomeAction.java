/**
 * 
 */
package wei.ssh.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import wei.ssh.model.AreaChina;
import wei.ssh.model.User;
import wei.ssh.service.IService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.After;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.opensymphony.xwork2.interceptor.annotations.BeforeResult;
import com.opensymphony.xwork2.validator.annotations.*;

/**
 * @author wei
 * 
 */
@ParentPackage("condefault")
@Namespace("/")
@Results({ @Result(name = "success", location = "/success.jsp"),
		@Result(name = "error", location = "/error.jsp") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@InterceptorRefs({ @InterceptorRef("annotatedStack") })
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4237869065655878253L;

	private double dage;

	private List<String> courses;

	@Resource(name = "queryService")
	private IService service;
	private List<Map<String, Object>> acs;

	@Resource(name = "jdbcTemplateMysql")
	private JdbcTemplate jdbcTemplate;

	private List<AreaChina> areas;

	private String uname;
	private String upwd;
	
	private User user;

	@Action(value = "home", results = {
			@Result(name = "success", location = "/success.jsp"),
			@Result(name = "error", location = "/error.jsp"),
			@Result(name = "input", location = "/login.jsp") })
	public String execute() {
		dage = Math.random();
		acs = jdbcTemplate.queryForList("select * from AreaChina limit 5");
		areas = service.getList();
		return SUCCESS;
	}

	@Before
	public void before() {
		System.out.println("1:before struts2..");
	}

	@BeforeResult
	public void beforeResult() {
		System.out.println("2:before result..");
	}

	@After
	public void after() {
		System.out.println("3:after struts..");
	}

	public double getDage() {
		return dage;
	}

	public void setDage(double dage) {
		this.dage = dage;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public List<Map<String, Object>> getAcs() {
		return acs;
	}

	public void setAcs(List<Map<String, Object>> acs) {
		this.acs = acs;
	}

	public List<AreaChina> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaChina> areas) {
		this.areas = areas;
	}

	public String getUname() {
		return uname;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "You must enter a value.")
	@RegexFieldValidator(fieldName = "uname", regexExpression = "(?:\\w{4,12})-?(?:\\w{1,12})", message = "(?:\\w{4,12})-?(?:\\w{1,12})")
	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
