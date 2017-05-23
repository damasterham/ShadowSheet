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

    private PrivateInfo privavteInfo;
    private Attributes attributes;
    private Skills skills;

}
