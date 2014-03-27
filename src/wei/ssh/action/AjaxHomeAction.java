/**
 * 
 */
package wei.ssh.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wei.db.util.QueryTable;
import wei.ssh.model.AreaChina;
import wei.ssh.service.IService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.After;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.opensymphony.xwork2.interceptor.annotations.BeforeResult;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 全注解方式的ajax风格action
 * 
 * @author wei
 * 
 */
@ParentPackage(value = "annodefault")
@Namespace("/")
@InterceptorRefs( {@InterceptorRef("annotatedStack")})
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Action(value = "ajaxaction", results = { @Result(name = "success", type = "json", params = {
		"encoding", "UTF-8", "statusCode", "200", "noCache", "true",
		"enableGZIP", "true" }),
		@Result(name="input",type="json")})
@Component("ajaxHomeAction")
@Scope(value="prototype") 
@SuppressWarnings("rawtypes")
public class AjaxHomeAction extends ActionSupport {

	private static final long serialVersionUID = -2573910827287628100L;

	private Date dt;

	private String name;

	private List<String> courses;
	
	private String code;
	
	private String msg;
	
	private String [][]data;
	
	@Resource(name="queryService")	
	private IService service;
	private List acs;
	private AreaChina area;
	
	


	// ajax的方法，返回的Type必须是json
	public String execute() {
		dt = new Date(Calendar.getInstance().getTimeInMillis());
		name = "Jerry Wang";
		
		courses = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			courses.add("course:" + i);
		}
		acs=service.getList(code);
		QueryTable table=new QueryTable("select * from areachina ",1,10);
		table.rend();
		msg=table.getSql();
		acs=table.getDataArray();
		//acs=new ArrayList<AreaChina>();
		//acs.add(service.getAreaChina(Integer.valueOf(code)));
		return SUCCESS;
	}
	
	@Before
	public void before(){
		System.out.println("1:before struts2..");
	}
	
	@BeforeResult
	public void beforeResult(){
		System.out.println("2:before result..");
	}
	
	@After
	public void after(){
		System.out.println("3:after struts..");
	}
	
	@JSON(serialize = false)
	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	@JSON(name = "dateTime",format="yyyy-MM-dd HH:mm:ss")
	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	@JSON(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 不需要被序列化成json的属性 **/
	@JSON(serialize = false)
	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public List  getAcs() {
		return acs;
	}

	public void setAcs(List  acs) {
		this.acs = acs;
	}

	public String getCode() {
		return code;
	}
	
	@RequiredStringValidator(message="need code!!!")
	public void setCode(String code) {
		this.code = code;
	}

	public AreaChina getArea() {
		return area;
	}

	public void setArea(AreaChina area) {
		this.area = area;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String [][] getData() {
		return data;
	}

	public void setData(String [][] data) {
		this.data = data;
	}

}
