package learnspring.business.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import learnspring.business.entities.Organization;


public interface OrganizationRepository extends PagingAndSortingRepository<Organization,Long> {

    long countByEnabledTrue();
    List<Organization> findByEnabledTrue();
    Page<Organization> findByEnabledTrue(Pageable pageable);
    
}