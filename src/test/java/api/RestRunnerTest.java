package api;//

import api.beans.Alias;
import api.beans.PersonalInfo;
import api.beans.Runner;
import api.enums.Metatype;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.assertj.core.groups.FieldsOrPropertiesExtractor;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.io.Console;
import java.util.List;

import static org.junit.Assert.assertEquals;

//Created by DaMasterHam on 24-05-2017.
//
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestRunnerTest
{

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private EntityManager entityManager;

    private static String RUNNER_ENDPOINT = "http://localhost:8080/api/runners";
    private static String RUNNER_THRU_PI_ENDPOINT = "http://localhost:8080/api/personalinfo/%s/runner";

    private static String PERSONAL_INFO_ENDPOINT = "http://localhost:8080/api/personalinfo";
    private static String PI_THRU_RUNNER_ENDPOINT = "http://localhost:8080/api/runners/%s/personalinfo";

    private static String ALIAS_ENDPOINT = "http://localhost:8080/api/aliases";

    @Before
    public void setUp() throws Exception
    {

        // Creates an empty Runner
        Runner runner = new Runner();

        // Adds the empty runner to its repository
        template.postForEntity(RUNNER_ENDPOINT, runner, Runner.class);

        // Creates PersonalInfo, meant for the newly created runner
        PersonalInfo personalInfo = new PersonalInfo(
                "Prefab-Runner",
                Metatype.HUMAN,
                20,
                "Caucasian",
                "Male",
                "I'm a fancy man.",
                "City skyline"
        );

        // Adds the PersonalInfo to its repository, not yet bound to the runner
        template.postForEntity(PERSONAL_INFO_ENDPOINT, personalInfo, PersonalInfo.class);


        // Binds the presonal info in its repository to the runner in its repositor
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity
                = new HttpEntity<>(PERSONAL_INFO_ENDPOINT + "/1", requestHeaders);
        template.exchange(String.format(PI_THRU_RUNNER_ENDPOINT, "1"),
                HttpMethod.PUT, httpEntity, String.class);
    }

    /*
    @After
    public void tearDown() throws Exception
    {
        entityManager
                .createNativeQuery(
                        "DELETE * FROM personal_info;" +
                        "DELETE * FROM runner;" +
                        "ALTER TABLE runner AUTO_INCREMENT = 1;" +
                        "ALTER TABLE personal_info AUTO_INCREMENT = 1;"
                     "SET FOREIGN_KEY_CHECKS = 0;" +
                             "SET AUTOCOMMIT = 0;" +
                             "START TRANSACTION;"+
                             "TRUNCATE runner;" +
                        "TRUNCATE personalInfo;" +
                             "SET FOREIGN_KEY_CHECKS = 1;" +
                             "COMMIT;" +
                             "SET AUTOCOMMIT = 1;")
                .executeUpdate();
    }
    */

    @Test
    public void createRunnerAndPersonalInfo_thenAssociate() throws Exception
    {
        // Creates an empty Runner
        Runner runner = new Runner();

        // Adds the empty runner to its repository
        template.postForEntity(RUNNER_ENDPOINT, runner, Runner.class);

        // Creates PersonalInfo, meant for the newly created runner
        PersonalInfo personalInfo = new PersonalInfo(
                "Bob Bobsen",
                Metatype.HUMAN,
                20,
                "Aryan",
                "often",
                "I'm a fancy man of the masterrace!",
                "City skyline"
        );

        // Adds the PersonalInfo to its repository, not yet bound to the runner
        template.postForEntity(PERSONAL_INFO_ENDPOINT, personalInfo, PersonalInfo.class);

        // Binds the presonal info in its repository to the runner in its repositor
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity
                = new HttpEntity<>(PERSONAL_INFO_ENDPOINT + "/2", requestHeaders);
        template.exchange(String.format(PI_THRU_RUNNER_ENDPOINT, "2"),
                HttpMethod.PUT, httpEntity, String.class);

        // Gets PersonalInfo through its uri
        ResponseEntity<PersonalInfo> personalInfoResponseEntity
                = template.getForEntity(PERSONAL_INFO_ENDPOINT + "/2", PersonalInfo.class);

        // Gets PersonalInfo through runner uri
        ResponseEntity<PersonalInfo> personalInfoGetResultThruRunner
                = template.getForEntity(String.format(PI_THRU_RUNNER_ENDPOINT, "2"), PersonalInfo.class);

        PersonalInfo expected = personalInfoResponseEntity.getBody();
        PersonalInfo actual = personalInfoGetResultThruRunner.getBody();

        //System.out.println(expected.toString());

        // Test
        assertEquals("Personal Info is incorrect",
                personalInfo
                ,actual);
    }

    @Test
    public void addAliasToPersonalInfo() throws Exception
    {
        Alias sillyAlias = new Alias("ST4LK3R");

        List<Alias> aliases = Lists.newArrayList(
                new Alias("Skipper"),
                new Alias("Beast Man"),
                sillyAlias
                );

        for (Alias alias : aliases)
        {
            template.postForEntity(ALIAS_ENDPOINT, alias, Alias.class);
        }

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> aliasHttpEntity
                = new HttpEntity<>(PERSONAL_INFO_ENDPOINT + "/1", requestHeaders);

        for (int i = 1; i <= aliases.size(); i++)
        {
            template.exchange(ALIAS_ENDPOINT + "/" + i + "/personalinfo",
                    HttpMethod.PUT, aliasHttpEntity, String.class);
        }

        ResponseEntity<Alias> aliasGetResultThruPersonalInfo =
                template.getForEntity(PERSONAL_INFO_ENDPOINT + "/1/aliases", Alias.class);
        assertEquals("library is incorrect", "Skipper",
                aliasGetResultThruPersonalInfo.getBody().getValue() );
    }
}