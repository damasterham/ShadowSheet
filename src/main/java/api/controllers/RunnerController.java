package api.controllers;//

import api.beans.Alias;
import api.beans.PersonalInfo;
import api.beans.Runner;
import api.repo.AliasRepository;
import api.repo.PersonalInfoRepository;
import api.repo.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

//Created by DaMasterHam on 26-05-2017.
//
@RepositoryRestController
@RequestMapping("/api/runner")
public class RunnerController
{
    @Autowired
    private RunnerRepository runnerRepo;
    @Autowired
    private PersonalInfoRepository personalInfoRepo;
    @Autowired
    private AliasRepository aliasRepo;

    @RequestMapping(method = POST, value = "/{runnerId}/addpersonalinfo")
    public ResponseEntity<?> addPersonalInfo(@PathVariable("runnerId") long runnerId, @RequestBody PersonalInfo personalInfo)
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

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
