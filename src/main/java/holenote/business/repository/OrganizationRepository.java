package holenote.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import holenote.business.entities.Organization;


public interface OrganizationRepository extends CrudRepository<Organization,Long> {

    List<Organization> findByEnabledTrue();
    
}