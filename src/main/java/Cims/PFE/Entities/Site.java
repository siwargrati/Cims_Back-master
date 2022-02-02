package Cims.PFE.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "site")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Site implements Serializable{
	@Id
	@SequenceGenerator(name="seq", initialValue=3, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(name="id_site",updatable=false)
	private Long idSite;
	
	@Column(name="nom_etablissement_fr",updatable=false)
	private String nom_etablissement_fr;
	
	@Column(name="nom_etablissement_ar",updatable=false)
	private String nom_etablissement_ar;
	
	@Column(name="nature_etablissement_fr",updatable=false)
	private String nature_etablissement_fr;
	
	@Column(name="nature_etablissement_ar",updatable=false)
	private String nature_etablissement_ar;
	
	@Column(name="qualite_direction_fr",updatable=false)
	private String qualite_direction_fr;
	
	@Column(name="qualite_direction_ar",updatable=false)
	private String qualite_direction_ar;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gouv",updatable=false)
	private Gouvernorat gouvernorat;

	@OneToMany(mappedBy = "site")
	 private List<AffectationPartielle> affectationp; 
	
	@OneToMany(mappedBy = "site",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<AffectationTotale> affectationt; 
	
	@OneToMany(mappedBy="site",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personnel> personnels ;


public Site() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getIdSite() {
	return idSite;
}

public void setIdSite(Long idSite) {
	this.idSite = idSite;
}

public Gouvernorat getGouvernorat() {
	return gouvernorat;
}


public void setGouvernorat(Gouvernorat gouvernorat) {
	this.gouvernorat = gouvernorat;
}


public void setAffectationp(List<AffectationPartielle> affectationp) {
	this.affectationp = affectationp;
}

public void setPersonnels(List<Personnel> personnels) {
	this.personnels = personnels;
}

public void setAffectationt(List<AffectationTotale> affectationt) {
	this.affectationt = affectationt;
}

public String getNom_etablissement_fr() {
	return nom_etablissement_fr;
}



public void setNom_etablissement_fr(String nom_etablissement_fr) {
	this.nom_etablissement_fr = nom_etablissement_fr;
}



public String getNom_etablissement_ar() {
	return nom_etablissement_ar;
}



public void setNom_etablissement_ar(String nom_etablissement_ar) {
	this.nom_etablissement_ar = nom_etablissement_ar;
}



public String getNature_etablissement_fr() {
	return nature_etablissement_fr;
}



public void setNature_etablissement_fr(String nature_etablissement_fr) {
	this.nature_etablissement_fr = nature_etablissement_fr;
}



public String getNature_etablissement_ar() {
	return nature_etablissement_ar;
}



public void setNature_etablissement_ar(String nature_etablissement_ar) {
	this.nature_etablissement_ar = nature_etablissement_ar;
}



public String getQualite_direction_fr() {
	return qualite_direction_fr;
}



public void setQualite_direction_fr(String qualite_direction_fr) {
	this.qualite_direction_fr = qualite_direction_fr;
}



public String getQualite_direction_ar() {
	return qualite_direction_ar;
}



public void setQualite_direction_ar(String qualite_direction_ar) {
	this.qualite_direction_ar = qualite_direction_ar;
}

public Site(Long idSite, Gouvernorat gouvernorat, List<AffectationPartielle> affectationp,
		List<AffectationTotale> affectationt, String nature_etablissement_fr, String nature_etablissement_ar, String nom_etablissement_fr, String nom_etablissement_ar, String qualite_direction_fr, String qualite_direction_ar, List<Personnel> personnels) {
	super();
	this.idSite = idSite;
	this.gouvernorat = gouvernorat;
	this.nature_etablissement_fr = nature_etablissement_fr;
	this.nature_etablissement_ar = nature_etablissement_ar;
	this.nom_etablissement_fr = nom_etablissement_fr;
	this.nom_etablissement_ar = nom_etablissement_ar;
	this.qualite_direction_fr = qualite_direction_fr;
	this.qualite_direction_ar = qualite_direction_ar;
	this.affectationp = affectationp;
	this.affectationt = affectationt;

	//this.personnels = personnels;
}

@Override
public String toString() {
	return "Site [idSite=" + idSite + ", nom_etablissement_fr=" + nom_etablissement_fr + ", gouvernorat=" + gouvernorat + ", affectationt=" + affectationt + ", affectationp="
			+ affectationp + ", personnels="  + "]";
}



}
