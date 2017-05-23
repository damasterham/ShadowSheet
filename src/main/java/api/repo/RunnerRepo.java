package api.repo;//

import api.beans.Runner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

//Created by DaMasterHam on 23-05-2017.
//
@RepositoryRestResource(collectionResourceRel = "runners", path = "runners")
public interface RunnerRepo extends CrudRepository<Runner, Long>
{
    Runner findById(long id);


}
