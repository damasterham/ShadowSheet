package beans;//

import javax.persistence.Entity;
import java.util.List;

//Created by DaMasterHam on 23-05-2017.
//
@Entity
public class PersonalInfo
{

    private String realName;
    private List<Alias> aliases;
    private Metatype metatype;
    private int age;
    private String ethnicity;
    private String sex;
    private String description;
    private String background;

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
        return this.aliases;
    }

    /*public void setAliases(List<String> aliases)
    {
        this.aliases = aliases;
    }*/

    public void addAlias(String newAlias)
    {
        this.aliases.add(new Alias(newAlias));
    }

    public void removeAlias(byte index)
    {
        this.aliases.remove(index);
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
}
