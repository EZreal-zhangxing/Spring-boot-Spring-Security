package RestFul;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import Dao.AuthuserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private SqlSessionFactory sqlSessionFactory;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("WebSecurityConfig is doing");
		http
		.authorizeRequests()
			.anyRequest().authenticated()   //所有请求都需要被认证。
			.and()
			//登陆方法。注意：由于我有采用thymeleaf 所以这里我直接定位的是方法。这个方法不做任何操作 返回值就是"login" 
			//然后定位到src/main/resources/templates目录下的 login.html 文件。
			//如果不用thymeleaf 这里需要写下你的登陆页面界面
			//登陆成功 跳转的方法为 main (方法内容  同login 直接跳转到main.html)
			//登录失败 跳转到 error.html
			.formLogin().loginPage("/login").successForwardUrl("/main").failureForwardUrl("/error.html").permitAll()
			.and()
			//自定义登陆页面的时候spring security 默认需要csrf 这么一个参数。在这里我们可以关闭
		.httpBasic().and().csrf().disable();
		//实现RequestCache接口 但是不做任何操作，然后指定RequestCache 替代默认的HttpSessionRequestCache类 这样就不会使用RequestCache
		http.requestCache().requestCache(new NullRequestCache());
//		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests().antMatchers("*.js").permitAll();
//		http
//        .authorizeRequests()
//            .antMatchers("/").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//            .loginPage("/login")
//            .permitAll()
//            .and()
//        .logout()
//            .permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		//创建一个认证规则AuthuserService
		auth.userDetailsService(new AuthuserService(sqlSessionFactory));
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("session factory is : ["+sqlSessionFactory+"]");
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
}
