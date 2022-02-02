package Cims.PFE.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  
@Table(name="Gouvernorat")
public class Gouvernorat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="id_gouv")
	private Long id_gouv;
	
	@Column(name="nom_gouvfr")
	private String nom_gouvfr;

	@Column(name="nom_gouvar")
	private String nom_gouvar;
	
	@OneToMany(mappedBy="gouvernorat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Site> Sites ;
	
	
	public Gouvernorat(Long id_gouv, String nom_gouvfr, String nom_gouvar) {
		super();
		this.id_gouv = id_gouv;
		this.nom_gouvfr = nom_gouvfr;
		this.nom_gouvar = nom_gouvar;
		
	}
	public Gouvernorat() {
		super();
	}
	public Long getid_gouv() {
		return id_gouv;
	}
	public void setid_gouv(Long id_gouv) {
		this.id_gouv = id_gouv;
	}
	public String getNomGouvFR() {
		return nom_gouvfr;
	}
	public void setNomGouvFR(String nom_gouvfr) {
		this.nom_gouvfr = nom_gouvfr;
	}
	
	public String getNomGouvAR() {
		return nom_gouvar;
	}
	public void setNomGouvAR(String nom_gouvar) {
		this.nom_gouvar = nom_gouvar;
	}	
	public void setSites(List<Site> sites) {
		Sites = sites;
	}
	@Override
	public String toString() {
		return "Gouvernorat [id_gouv=" + id_gouv + ", nom_gouvfr=" + nom_gouvfr + ", Sites=" + Sites + "]";
	}
	

}
