package api;//

//Created by DaMasterHam on 26-05-2017.
//


import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter
{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.setBasePath("/api");
    }
}

