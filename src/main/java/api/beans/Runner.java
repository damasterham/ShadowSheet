package api.beans;//

//Created by DaMasterHam on 23-05-2017.
//

import org.hibernate.annotations.Columns;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
public class Runner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = PersonalInfo.ID_REF)
    //rel = "personalInfo"
    @RestResource(rel = PersonalInfo.FIELD_REF, path = PersonalInfo.PATH_REF)
    private PersonalInfo personalInfo;

//    @OneToOne
//    private Attributes attributes;
//    @OneToOne
//    private Skills skills;



    public Runner()
    {
    }

    public Runner(PersonalInfo personalInfo, Attributes attributes, Skills skills)
    {
        this.personalInfo = personalInfo;
//        this.attributes = attributes;
//        this.skills = skills;
    }

    public Runner(long id, PersonalInfo personalInfo, Attributes attributes, Skills skills)
    {
        this.id = id;
        this.personalInfo = personalInfo;
//        this.attributes = attributes;
//        this.skills = skills;
    }



    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo()
    {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo)
    {
        this.personalInfo = personalInfo;
    }
//
//    public Attributes getAttributes()
//    {
//        return attributes;
//    }
//
//    public void setAttributes(Attributes attributes)
//    {
//        this.attributes = attributes;
//    }
//
//    public Skills getSkills()
//    {
//        return skills;
//    }
//
//    public void setSkills(Skills skills)
//    {
//        this.skills = skills;
//    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Runner)) return false;

        Runner runner = (Runner) o;

        //if (id != runner.id) return false;
        return id == runner.id;
//        if (!personalInfo.equals(runner.personalInfo)) return false;
//        if (!attributes.equals(runner.attributes)) return false;
//        return skills.equals(runner.skills);
    }

    @Override
    public int hashCode()
    {
        return (int) (id ^ (id >>> 32));
    }
}
