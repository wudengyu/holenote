package holenote.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import holenote.business.entities.User;


public interface UserRepository extends CrudRepository<User,Long>,JpaSpecificationExecutor<User>{

    User findByUsername(String username);
}
