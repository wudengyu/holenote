package holenote.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import holenote.business.BusinessConfig;
import holenote.business.entities.User;
import holenote.business.repository.UserRepository;
@Ignore
public class UserDaoTest {
    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    private GenericApplicationContext ctx;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(BusinessConfig.class);
        userRepository = ctx.getBean(UserRepository.class);
        assertNotNull(userRepository);
    }

    @Test
    public void testSpecificaiton() {
        User user=userRepository.findByUsername("admin");
        assertNotNull(user);
        logger.info(user.getUsername());
    }

}
