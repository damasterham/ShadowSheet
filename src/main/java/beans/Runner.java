package beans;//

//Created by DaMasterHam on 23-05-2017.
//

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Runner
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private PrivateInfo privateInfo;
    private Attributes attributes;
    private Skills skills;

    public Runner()
    {
    }

    public Runner(PrivateInfo privateInfo, Attributes attributes, Skills skills)
    {
        this.privateInfo = privateInfo;
        this.attributes = attributes;
        this.skills = skills;
    }

    public Runner(long id, PrivateInfo privateInfo, Attributes attributes, Skills skills)
    {
        this.id = id;
        this.privateInfo = privateInfo;
        this.attributes = attributes;
        this.skills = skills;
    }



    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public PrivateInfo getPrivateInfo()
    {
        return privateInfo;
    }

    public void setPrivateInfo(PrivateInfo privateInfo)
    {
        this.privateInfo = privateInfo;
    }

    public Attributes getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Attributes attributes)
    {
        this.attributes = attributes;
    }

    public Skills getSkills()
    {
        return skills;
    }

    public void setSkills(Skills skills)
    {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Runner)) return false;

        Runner runner = (Runner) o;

        //if (id != runner.id) return false;
        return id == runner.id;
//        if (!privateInfo.equals(runner.privateInfo)) return false;
//        if (!attributes.equals(runner.attributes)) return false;
//        return skills.equals(runner.skills);
    }

    @Override
    public int hashCode()
    {
        return (int) (id ^ (id >>> 32));
    }
}
