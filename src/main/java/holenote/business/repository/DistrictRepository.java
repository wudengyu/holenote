package holenote.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import holenote.business.entities.District;


public interface DistrictRepository extends CrudRepository<District,String>{
    
    District findByName(String name);
    List<District> findByEnabledTrue();

}
