package holenote.repository;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import holenote.business.BusinessConfig;
import holenote.business.entities.District;
import holenote.business.repository.DistrictRepository;

@Ignore
public class DistrictDaoTest {
    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    private GenericApplicationContext ctx;
    private EntityManagerFactory emf;
    private DistrictRepository districtRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(BusinessConfig.class);
        emf= ctx.getBean(EntityManagerFactory.class);
        districtRepository = ctx.getBean(DistrictRepository.class);
        assertNotNull(emf);
        assertNotNull(districtRepository);
    }

    @Test
    public void testSpecificaiton() {
        District district=new District();
        //district.setCode("522300");
        district.setName("abc");
        district.setEnabled(true);
        emf.createEntityManager().persist(district);
        //districtRepository.save(district);
    }

    @Test
    public void testFindDistrict(){
        District district=districtRepository.findByName("abc");
        assertNotNull(district);
        logger.info(district.getCode().toString());
        logger.info(district.getName());
        logger.info(district.isEnabled()?"True":"False");


    }


}