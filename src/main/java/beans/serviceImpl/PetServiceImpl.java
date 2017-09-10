package beans.serviceImpl;


import org.springframework.stereotype.Service;

import beans.common.Permission;
import beans.service.PetService;

@Service
//注解将类存入xml文件中作为一个bean元素，此时类文件被当做一个对象，首字母小写
//spring容器将bean存入的时候可以看做是一个map
//key是所存入当前类类名首字母小写
//value就是当前类对象
//e.g map<petServiceImpl,new PetServiceImpl{}>
public class PetServiceImpl implements PetService {

	@Permission("pet:save")
	public void saveObject() throws Exception {
		System.out.println("PetImpl 执行ִsave方法");
		//throw new Exception("抛出save异常");

	}

	@Permission("pet:update")
	public void updateObject() throws  Exception{
		System.out.println("PetImpl ִ执行update方法");
		//throw new Exception("抛出update异常");
	}
	
	
	

}
