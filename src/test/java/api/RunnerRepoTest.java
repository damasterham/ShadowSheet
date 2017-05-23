package api;//

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import api.mock.RunnerRepoMockSimple;
import beans.Attributes;
import beans.PrivateInfo;
import beans.Runner;

import beans.Skills;
import org.assertj.core.util.IterableUtil;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import repo.RunnerRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Created by DaMasterHam on 23-05-2017.
//
public class RunnerRepoTest
{
    private RunnerRepo repo = new RunnerRepoMockSimple();

    @Test
    public void GetAllRunnersSimple() throws Exception
    {
        Iterable<Runner> runners = Arrays.asList(
                new Runner(0, new PrivateInfo(), new Attributes(), new Skills()),
                new Runner(1, new PrivateInfo(), new Attributes(), new Skills()),
                new Runner(2, new PrivateInfo(), new Attributes(), new Skills())
        );

        Iterable<Runner> restRunners = repo.findAll();

        assertEquals(Lists.newArrayList(runners), Lists.newArrayList(restRunners));
        //assertThat(Lists.newArrayList(runners),is(Lists.newArrayList(restRunners)));
        //assertThat(runners, is(restRunners));
        
    }

    @Test
    public void GetRunnerZeroSimple() throws Exception
    {
        Runner runner = new Runner(0, new PrivateInfo(), new Attributes(), new Skills());
        Runner fromRest = repo.findById(0);

        assertEquals(runner,fromRest);
    }

    @Test
    public void CreateRunnerSimple() throws Exception
    {
        Runner newGuy = new Runner(new PrivateInfo(), new Attributes(), new Skills());

        Runner created = repo.save(newGuy);

        assertEquals(1, created.getId());
    }
}
