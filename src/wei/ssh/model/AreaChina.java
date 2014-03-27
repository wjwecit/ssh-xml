package wei.ssh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * @author Jerry
 *
 */
@Entity(name="areaChina")
@Table(name="areachina")
@Cache(usage =CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="cache1")
public class AreaChina implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7859704013566331716L;
	private int areaCode;	
	private String areaName;	
	private String areaCodeDeprecated;	
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="areaCode",nullable = false)
	public int getAreaCode() {
		return areaCode;
	}

	
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name="areaCodeDeprecated")
	public String getAreaCodeDeprecated() {
		return areaCodeDeprecated;
	}

	public void setAreaCodeDeprecated(String areaCodeDeprecated) {
		this.areaCodeDeprecated = areaCodeDeprecated;
	}

	@Column(name="areaName")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
