package Dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pojo.role;
import pojo.user;
@Configuration
public class AuthuserService extends SqlSessionTemplate implements UserDetailsService{
	public AuthuserService(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("this is AuthuserService");
		UserServices us=new UserServices(getSqlSessionFactory());
		List<role> list=us.getRoleByusername(username);
		user u=us.getUserByusername(username);
		System.out.println(list);
		//权限列表 由于Spring security 4 去掉了GrantedAuthorityImpl 这个是Spring security3 里的 GrantedAuthority 的实现类
		//这里替换的 是SimpleGrantedAuthority 类 进行权限的封装
		List<GrantedAuthority> rolelist=new ArrayList<GrantedAuthority>();
		for (int i = 0; i < list.size(); i++) {
			role rol=list.get(i);
			System.out.println(rol.getRoleCode());
			GrantedAuthority gi=new SimpleGrantedAuthority(rol.getRoleCode());
			rolelist.add(gi);
		}
		return new  User(username, u.getPassword(), rolelist);
	}
	
}
