package api.controllers;

import api.beans.Alias;
import api.beans.Attributes;
import api.beans.PersonalInfo;
import api.beans.Runner;
import api.repo.AliasRepository;
import api.repo.PersonalInfoRepository;
import api.repo.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Andreas on 16-06-2017.
 */

@RepositoryRestController
@RequestMapping("/runner")
public class RunnerController
{

    //Endpoints
    private static final String PERSONAL_INFO_REL = "personalInfo";
    private static final String PERSONAL_INFO_URL = "/add/personalinfo";
    private static final String ATTRIBUTES_REL = "attributes";
    private static final String ATTRIBUTES_URL = "/add/attributes";
    private static final String SKILLS_REL = "skills";
    private static final String SKILLS_URL = "/add/skills";

    @Autowired
    private RunnerRepository runnerRepo;
    @Autowired
    private PersonalInfoRepository personalInfoRepo;
    @Autowired
    private AliasRepository aliasRepo;


    @RequestMapping(method = GET, value = "/{runnerId}/add/breadcrumbs")
    public ResponseEntity<?> breadcrumbs(@PathVariable("runnerId") long runnerId)
    {
        Resources<String> resources = new Resources<String>(new ArrayList<>(),
                Arrays.asList(
                        new Link("/" + runnerId + PERSONAL_INFO_URL, PERSONAL_INFO_REL),
                        new Link("/" + runnerId + ATTRIBUTES_URL, ATTRIBUTES_REL),
                        new Link("/" + runnerId +SKILLS_URL, SKILLS_REL)
                )
        );


        return ResponseEntity.ok(resources);
    }

    @RequestMapping(method = POST, value = "/{runnerId}/add/{step}")
    public <T> ResponseEntity<?> saveAndGo(@PathVariable("runnerId") long runnerId,
                                       @PathVariable("step") String step,
                                       @RequestBody T entity)
    {

        ResponseEntity<?> response;

        if (entity.getClass().equals(PersonalInfo.class))
            response = addPersonalInfo(runnerId, (PersonalInfo) entity);
        else if (entity.getClass().equals(Attributes.class))
            response = addAttributes(runnerId, (Attributes) entity);
        else
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return response;
    }

    private ResponseEntity<?> addAttributes(long runnerId, Attributes entity)
    {
        return null;
    }

    private ResponseEntity<?> addPersonalInfo(long runnerId, PersonalInfo personalInfo)
    {
        // Create Aliases
        List<Alias> aliasesCreated = (List<Alias>) aliasRepo.save(personalInfo.getAliases());

        // Associate Aliases to PersonalInfo
        personalInfo.setAliases(aliasesCreated);

        // Create PersonalInfo
        PersonalInfo createdPersonalInfo = personalInfoRepo.save(personalInfo); // Might need to remove Aliases.

        // Associate PersonalInfo to Runner
        Runner runner = runnerRepo.findById(runnerId);
        runner.setPersonalInfo(createdPersonalInfo);
        runnerRepo.save(runner);

        //Resource resource = new Resource(HttpStatus.CREATED, new Link(ATTRIBUTES_URL, "current"));


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
