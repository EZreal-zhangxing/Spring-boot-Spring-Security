package RestFul;

import org.springframework.cache.annotation.Cacheable;  
import org.springframework.stereotype.Service;  
import pojo.role;
import pojo.user;
  
/** 
 * Created by wisely on 2015/5/25. 
 */  
@Service  
public class DemoService {  
    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
    public user findUser(Integer id,String username,String password){  
        System.out.println("无缓存的时候调用这里");  
        return new user(id,username,password);  
    }  
    @Cacheable(value = "rolecache",keyGenerator = "wiselyKeyGenerator")  
    public role findRole(Integer id,Integer userid,String roleCode){  
        System.out.println("无缓存的时候调用这里");  
        return new role(id,userid,roleCode);  
    }  
}  
