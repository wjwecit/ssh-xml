/**
 * 
 */
package wei.db.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;


/**
 * @author wangjw
 * 
 */
public class DBConnectionUtil {

	private static BasicDataSource ds;

	public static Connection getCommonConnection() {
		if (ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull");
			ds.setUsername("root");
			ds.setPassword("123");
			ds.setDefaultAutoCommit(false);
			ds.setMaxActive(30);
			ds.setMaxIdle(10);
			ds.setMaxWait(18000000);
			ds.setValidationQuery("select now()");
			ds.setInitialSize(1);
			ds.setMinIdle(1);
			ds.setPoolPreparedStatements(true);
		}
		try {
			/*String url="jdbc:mysql://192.168.10.247:3306/dodonew?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull";
			String user="dev";
			String password="dev";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			java.sql.Connection conn=java.sql.DriverManager.getConnection(url, user, password);
			*/
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
