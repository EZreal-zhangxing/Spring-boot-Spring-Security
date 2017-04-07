package RestFul;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import pojo.user;


import Dao.UserServices;

@Controller//根据返回的字符串去定位 页面 注意 字符串应该和页面同名
//@RestController//返回json字符串
@SessionAttributes("userid")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private SqlSessionFactory sqlsessionFactory;
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
//    @RequestMapping("/test")
//    @ResponseStatus(value=HttpStatus.BAD_GATEWAY)
//    public String test(@RequestParam(value="username",defaultValue="zhangxing") String username,@RequestParam(value="password",defaultValue="zhangxing") String password) {
//    	user u=new user(1,username, password,1);
//		return "this is defind String";
//    }
    @RequestMapping(path="/index/{name}")
    public List<Greeting> index(@PathVariable(value="name") String name) throws Exception {
    	Greeting g1= new Greeting(1,String.format(template, name));
    	Greeting g2= new Greeting(2,String.format(template, name));
    	List<Greeting> list=new ArrayList<Greeting>();
    	list.add(g1);
    	list.add(g2);
    	JSONObject json=new JSONObject();
    	json.put("name", "zx");
    	json.put("age", "23");
        return list;
    }
    @RequestMapping(path="/jsonp/{name}")
    public void jsonp(@PathVariable(value="name") String name,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	response.setContentType("application/javascript; charset=utf-8");      
    	String callback=request.getParameter("callback");      
    	PrintWriter ss = response.getWriter();
    	JSONObject json=new JSONObject();
    	json.put("name", "zx");
    	json.put("age", "23");
    	json.put("youinput", name);
    	JSONObject json2=new JSONObject();
    	json2.put("name", "zy");
    	json2.put("age", "21");
    	json2.put("youinput", name);
    	JSONArray ja=new JSONArray();
    	ja.add(json);
    	ja.add(json2);
    	ss.print(callback+"("+ja.toString()+")");
    }
    @RequestMapping("/map")
    public Map<String,Greeting> map(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting g1= new Greeting(1,String.format(template, name));
    	Greeting g2= new Greeting(2,String.format(template, name));
    	Map<String,Greeting> map=new HashMap<String, Greeting>();
    	map.put("index1", g1);
    	map.put("index2", g2);
        return map;
    }
    @RequestMapping("/main")
    @PreAuthorize("hasRole('ROLE_SERVICE')")
    public String main(Model model)
    {
    	model.addAttribute("name", "zx");
    	model.addAttribute("user", getPrincipal());
    	return "main";
    }
    @RequestMapping("/users")
    @PreAuthorize("hasRole('ROLE_SERVICE')")
    public String users(Model model)
    {
    	UserServices us=new UserServices(sqlsessionFactory);
    	model.addAttribute("users", us.getUsers());
    	model.addAttribute("userid", counter.incrementAndGet());
    	model.addAttribute("user", getPrincipal());
    	return "user";
    }
    @RequestMapping("/login")
    public String login(Model model){
    	model.addAttribute("user", getPrincipal());
    	return "login";
    }
    @RequestMapping("/argular")
    public String argular(){
    	return "argular";
    }
    @RequestMapping("/fileupload")
    public String upload(){
    	return "upload";
    }
    private String getPrincipal(){  
        String userName = null;  
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
   
        if (principal instanceof UserDetails) {  
            userName = ((UserDetails)principal).getUsername();  
        } else {  
            userName = principal.toString();  
        }  
        return userName;  
    }  
	public SqlSessionFactory getSqlsessionFactory() {
		return sqlsessionFactory;
	}
	public void setSqlsessionFactory(SqlSessionFactory sqlsessionFactory) {
		this.sqlsessionFactory = sqlsessionFactory;
	}
}