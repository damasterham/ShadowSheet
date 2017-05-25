package api.repo;//

import api.beans.PersonalInfo;
import api.beans.Runner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

//Created by DaMasterHam on 23-05-2017.
//
@RepositoryRestResource(collectionResourceRel = "runners", path = "runners")
public interface RunnerRepository extends CrudRepository<Runner, Long>
{
    Runner findById(@Param("id") long id);

}
