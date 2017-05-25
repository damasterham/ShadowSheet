package api;//

import static org.junit.Assert.*;

import api.mock.RunnerRepoMockSimple;
import api.repo.RunnerRepository;
import api.beans.Attributes;
import api.beans.PersonalInfo;
import api.beans.Runner;

import api.beans.Skills;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

//Created by DaMasterHam on 23-05-2017.
//
public class RunnerRepoTest
{
    private RunnerRepository repo;

    @Before
    public void setUp() throws Exception
    {
        repo = new RunnerRepoMockSimple();
    }

    @Test
    public void GetAllRunnersSimple() throws Exception
    {
        Iterable<Runner> runners = Arrays.asList(
                new Runner(0, new PersonalInfo(), new Attributes(), new Skills()),
                new Runner(1, new PersonalInfo(), new Attributes(), new Skills()),
                new Runner(2, new PersonalInfo(), new Attributes(), new Skills())
        );

        Iterable<Runner> restRunners = repo.findAll();

        assertEquals(Lists.newArrayList(runners), Lists.newArrayList(restRunners));
        //assertThat(Lists.newArrayList(runners),is(Lists.newArrayList(restRunners)));
        //assertThat(runners, is(restRunners));
        
    }

    @Test
    public void GetRunnerZeroSimple() throws Exception
    {
        Runner runner = new Runner(0, new PersonalInfo(), new Attributes(), new Skills());
        Runner fromRest = repo.findById(0);

        assertEquals(runner,fromRest);
    }

    @Test
    public void CreateRunnerSimple() throws Exception
    {
        Runner newGuy = new Runner(new PersonalInfo(), new Attributes(), new Skills());

        Runner created = repo.save(newGuy);

        assertEquals(1, created.getId());
    }

    @Test
    public void AddPersonalInfoToRunnerZero() throws Exception
    {
        Runner zero = repo.findById(0);

        assertFalse(true);
    }
}
