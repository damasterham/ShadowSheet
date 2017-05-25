package api.beans;//

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//Created by DaMasterHam on 23-05-2017.
//
@Entity
public class Alias
{
    public static final String ID_REF = "alias_id";

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = PersonalInfo.ID_REF)
    @RestResource(rel = PersonalInfo.FIELD_REF, path = PersonalInfo.PATH_REF)
    private PersonalInfo personalInfo;

    public Alias()
    {
    }

    public Alias(String value)
    {
        this.value = value;
    }

    public Alias(String value, PersonalInfo personalInfo)
    {
        this.value = value;
        this.personalInfo = personalInfo;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public PersonalInfo getPersonalInfo()
    {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo)
    {
        this.personalInfo = personalInfo;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Alias)) return false;

        Alias alias = (Alias) o;

        if (getId() != alias.getId()) return false;
        return getValue().equals(alias.getValue());
    }

    @Override
    public int hashCode()
    {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
