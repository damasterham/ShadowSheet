package repo;//

import beans.Runner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//Created by DaMasterHam on 23-05-2017.
//
@RepositoryRestResource(collectionResourceRel = "runners", path = "api/runners")
public interface RunnerRepo extends CrudRepository<Runner, Long>
{
    Runner findById(long id);
}

