package Cims.PFE.Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity 
@DiscriminatorValue("PersonnelCorrespondant")
public class PersonnelCorrespondant  extends Personnel{

	
	public PersonnelCorrespondant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	



	
}
