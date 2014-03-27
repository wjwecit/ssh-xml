package wei.ssh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 使用注解形式的hibernate实体类,本实体类不与数据库表进行关联，仅通过原生态的sql进行映射
 * @author wei
 * 2013-12-5
 */
@Entity
@Cache(usage =CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="cache2")
@SqlResultSetMapping(name="implicit",entities=@EntityResult(entityClass=UserInfo.class))
@NamedNativeQuery(name = "queryUserInfo", query = "select u.id,u.name,a.areaName,a.areaCode from "
		+ "userarea u left join areachina a on "
		+ "u.areaCode=a.areaCode where u.areaCode=:areaCode", resultSetMapping = "implicit")
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -2117530530819988235L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="areaName")
	private String areaName;

	@Column(name="areaCode")
	private int areaCode;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
