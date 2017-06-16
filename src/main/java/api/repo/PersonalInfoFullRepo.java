package api.repo;

import api.projections.PersonalInfoWithAliases;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andreas on 16-06-2017.
 */
@RepositoryRestResource(excerptProjection = PersonalInfoWithAliases.class, collectionResourceRel = "personalinfofull",
        path = "personalinfofull")
public interface PersonalInfoFullRepo extends CrudRepository<PersonalInfoFullRepo, Long>
{
}
