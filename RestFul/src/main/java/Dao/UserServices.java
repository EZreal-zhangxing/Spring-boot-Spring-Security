package Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import pojo.role;
import pojo.user;



public class UserServices extends SqlSessionTemplate {

	public UserServices(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		// TODO Auto-generated constructor stub
	}
	public List<user> getUsers(){
		SqlSession session=this.getSqlSessionFactory().openSession();
		List<user> list=session.selectList("selectAll");
		return list;
	}
	public List<role> getRoleByusername(String username)
	{
		SqlSession session=this.getSqlSessionFactory().openSession();
		user u=session.selectOne("selectUser", username);
		List<role> list=session.selectList("selectRole",u.getId());
		return list;
	}
	public user getUserByusername(String username)
	{
		SqlSession session=this.getSqlSessionFactory().openSession();
		user u=session.selectOne("selectUser", username);
		return u;
	}
}
