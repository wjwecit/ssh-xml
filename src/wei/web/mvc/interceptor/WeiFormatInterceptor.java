package wei.web.mvc.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 采用spring面向切面处理
 * @author wei
 * @since 2014-01-09
 */
@Repository
@Aspect
public class WeiFormatInterceptor implements IFormatInterceptor {

	Logger log=LoggerFactory.getLogger(WeiFormatInterceptor.class);
	
	@Before("doThis()")
	public void preFormat() {
		log.info("--preFormat--");
	}
	
	/**the pointcut expression**/
	@Pointcut("execution(* wei.ssh.service.*.*(..))")
	public void doThis(){
		log.info("doThis");
	};
	
	@After("doThis()")
	public void afterFormat() {
		log.info("--afterFormat--");
	}

}
