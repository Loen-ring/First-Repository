import beans.service.PetService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPetService {
    private ClassPathXmlApplicationContext ctx;

    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("spring-aop-002.xml");
    }


    @Test
    public void test() throws Exception {
        PetService petService = (PetService) ctx.getBean("petServiceImpl");
        petService.saveObject();
    }

    @After
    public void after(){
        ctx.close();
    }

}
