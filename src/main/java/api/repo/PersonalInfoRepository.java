package api.repo;//

import api.beans.PersonalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Created by DaMasterHam on 24-05-2017.
//

@RepositoryRestResource(collectionResourceRel = "personalinfo", path = "personalinfo")
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, Long>
{

}
