package RestFul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class task {
//	@Scheduled(cron="0/3 * * * * ?")
	public void test()
	{
		System.out.println("atere");
	}
}
