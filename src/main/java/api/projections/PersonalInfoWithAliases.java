package api.projections;

import api.beans.Alias;
import api.beans.PersonalInfo;
import api.enums.Metatype;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by Andreas on 16-06-2017.
 */

@Projection(name = "personalInfoWithAliases", types = {PersonalInfo.class})
public interface PersonalInfoWithAliases
{
    String getRealName();
    List<Alias> getAliases();
    Metatype getMetatype();
    int getAge();
    String getEthnicity();
    String getSex();
    String getDescription();
    String getBackground();

}
