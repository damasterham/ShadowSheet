package api.beans;//

import api.enums.Metatype;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.persistence.*;
import java.util.List;

//Created by DaMasterHam on 23-05-2017.
//
@Entity
public class PersonalInfo
{
    public static final String FIELD_REF = "personalInfo";
    public static final String ID_REF = "personal_info_id";

    @Id
    @GeneratedValue
    @Column(name = "personal_info_id")
    private long id;

    @OneToOne(mappedBy = FIELD_REF)
    private Runner runner;

    private String realName;
    @OneToMany( mappedBy = FIELD_REF)
    private List<Alias> aliases;
    private Metatype metatype;
    private int age;
    private String ethnicity;
    private String sex;
    private String description;
    private String background;

    public PersonalInfo()
    {
    }

    public PersonalInfo(String realName, Metatype metatype, int age, String ethnicity, String sex, String description, String background)
    {
        this.realName = realName;
        this.metatype = metatype;
        this.age = age;
        this.ethnicity = ethnicity;
        this.sex = sex;
        this.description = description;
        this.background = background;
    }

    public PersonalInfo(long id, Runner runner, String realName, Metatype metatype, int age, String ethnicity, String sex, String description, String background)
    {
        this.id = id;
        this.runner = runner;
        this.realName = realName;
        this.metatype = metatype;
        this.age = age;
        this.ethnicity = ethnicity;
        this.sex = sex;
        this.description = description;
        this.background = background;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Runner getRunner()
    {
        return runner;
    }

    public void setRunner(Runner runner)
    {
        this.runner = runner;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public List<Alias> getAliases()
    {
        return aliases;
    }

    public void setAliases(List<Alias> aliases)
    {
        this.aliases = aliases;
    }

    public Metatype getMetatype()
    {
        return metatype;
    }

    public void setMetatype(Metatype metatype)
    {
        this.metatype = metatype;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getEthnicity()
    {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity)
    {
        this.ethnicity = ethnicity;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getBackground()
    {
        return background;
    }

    public void setBackground(String background)
    {
        this.background = background;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PersonalInfo)) return false;

        PersonalInfo that = (PersonalInfo) o;

        if (getId() != that.getId()) return false;
        if (getAge() != that.getAge()) return false;
        //if (getRunner().getId() != that.getRunner().getId()) return false;
        if (!getRealName().equals(that.getRealName())) return false;
        if (getMetatype() != that.getMetatype()) return false;
        if (!getEthnicity().equals(that.getEthnicity())) return false;
        if (!getSex().equals(that.getSex())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        return getBackground().equals(that.getBackground());
    }

    @Override
    public int hashCode()
    {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
