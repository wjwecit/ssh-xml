/**
 * 
 */
package wei.ssh.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wei.ssh.model.User;

/**
 * @author wei
 *
 */
@SuppressWarnings("rawtypes")
public class UserConverter extends StrutsTypeConverter {
	
	private static final Logger log=LoggerFactory.getLogger(UserConverter.class);

	@Override
	public User convertFromString( Map context, String[] strs, Class type) {
		if(strs!=null&&strs.length>0){
			User user=new User();
			String[] str2=strs[0].split(";");
			if(str2.length>=2){
				String name=str2[0];
				user.setName(name);
				String password=str2[1];
				user.setPassword(password);
				log.info("user 转化成功!name:"+strs[0]+";password:"+password);
				return user;
			}			
		}
		
		return null;
	}

	@Override
	public String convertToString(Map context, Object obj) {
		
		return null;
	}

}
