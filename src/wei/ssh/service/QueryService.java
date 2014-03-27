/**
 * 
 */
package wei.ssh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wei.ssh.model.AreaChina;
import wei.ssh.model.UserArea;
import wei.ssh.model.UserInfo;

/**
 * @author wei
 * 
 */
@Service("queryService")
public class QueryService implements IService {

	@Resource(name = "jdbcTemplateMysql")
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "hibernateTemplete")
	private HibernateTemplate htemplate;

	public List<Map<String, Object>> queryList() {
		String sql = "select * from areachina where areaCode like '%5%'";

		return jdbcTemplate.queryForList(sql);
	}

	public void update(Object object) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false, value = "txMysql", propagation = Propagation.REQUIRES_NEW)
	public void updateViaHibernate(final Object object) {
		AreaChina ac = (AreaChina) object;
		htemplate.saveOrUpdate(ac);
	}

	@SuppressWarnings("unchecked")
	public List<AreaChina> getList() {
		final String sql = "from wei.ssh.model.AreaChina ac where ac.areaCode like '%5%'";
		return htemplate.execute(new HibernateCallback<List<AreaChina>>() {

			public List<AreaChina> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(sql).setMaxResults(500).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserInfo() {
		return htemplate.executeFind(new HibernateCallback<List<UserInfo>>() {
			@Override
			public List<UserInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				return session.getNamedQuery("queryUserInfo").setParameter("areaCode", 755).setMaxResults(500).list();
			}

		});
	}

	public AreaChina getAreaChina() {
		return htemplate.get(AreaChina.class, 755);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaChina> getList(final String code) {
		final String sql = "from wei.ssh.model.AreaChina ac where ac.areaCode like '%"+code+"%'";
		return htemplate.execute(new HibernateCallback<List<AreaChina>>() {

			public List<AreaChina> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(sql).setMaxResults(500).list();
			}
		});
	}

	@Override
	public AreaChina getAreaChina(int id) {
		return htemplate.get(AreaChina.class, id);
	}
}
