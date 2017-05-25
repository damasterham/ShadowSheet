package api.mock;//

import api.beans.Attributes;
import api.beans.PersonalInfo;
import api.beans.Runner;
import api.beans.Skills;
import api.repo.RunnerRepository;

import java.util.Arrays;

//Created by DaMasterHam on 23-05-2017.
//
public class RunnerRepoMockSimple implements RunnerRepository
{
    private static Iterable<Runner> runners = Arrays.asList(
            new Runner(0, new PersonalInfo(), new Attributes(), new Skills()),
            new Runner(1, new PersonalInfo(), new Attributes(), new Skills()),
            new Runner(2, new PersonalInfo(), new Attributes(), new Skills())
    );

    private static Runner zero = new Runner(0, new PersonalInfo(), new Attributes(), new Skills());


    @Override
    public <S extends Runner> S save(S s)
    {
        s.setId(1);
        return s;
    }

    @Override
    public <S extends Runner> Iterable<S> save(Iterable<S> iterable)
    {
        return null;
    }

    @Override
    public Runner findOne(Long aLong)
    {
       /* if (aLong == 0)
            return zero;*/
       return null;
    }

    @Override
    public Runner findById(long id)
    {
        if (id == 0)
            return zero;
        return null;
    }

    @Override
    public boolean exists(Long aLong)
    {
        return false;
    }

    @Override
    public Iterable<Runner> findAll()
    {
        return runners;
    }

    @Override
    public Iterable<Runner> findAll(Iterable<Long> iterable)
    {
        return null;
    }

    @Override
    public long count()
    {
        return 0;
    }

    @Override
    public void delete(Long aLong)
    {

    }

    @Override
    public void delete(Runner runner)
    {

    }

    @Override
    public void delete(Iterable<? extends Runner> iterable)
    {

    }

    @Override
    public void deleteAll()
    {

    }
}
