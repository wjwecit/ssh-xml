/**
 * 
 */
package wei.ssh.service;

import java.util.List;
import java.util.Map;

import wei.ssh.model.AreaChina;
import wei.ssh.model.UserArea;
import wei.ssh.model.UserInfo;

/**
 * @author Jerry
 *
 */
/*@Transactional(readOnly = true)*/
public interface IService {
	/*@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)*/
	public void update(Object object);
	
	public void updateViaHibernate(Object object);
	
	public List<AreaChina> getList();
	
	public List<AreaChina> getList(String code);
	
	public List<UserInfo> getUserInfo();
	
	public AreaChina getAreaChina(int id);

	public List<Map<String, Object>> queryList();
	
}
