package Cims.PFE.Entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity  
@Table(name="fonction") 
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Fonction {
	@Id
	@SequenceGenerator(name="seqf", initialValue=2, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqf")
    @Column(name="id_fonction",updatable=false)
	private Long id_fonction;
	
	@Column(name="nom_fonction",updatable=false)
	private String nom_fonction;
	
	@Column(name="type_fonction",updatable=false)
	private String type_fonction;
	
	
	
	@OneToMany(mappedBy="fonction", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Personnel> personnels ;

   public Fonction() {
		super();
		// TODO Auto-generated constructor stub
	}
public Fonction(Long id_fonction, String nom_fonction, String type_fonction) {
	super();
	this.id_fonction = id_fonction;
	this.nom_fonction = nom_fonction;
	this.type_fonction = type_fonction;
	//this.personnels = personnels;
}

public Long getId_fonction() {
	return id_fonction;
}
public void setId_fonction(Long id_fonction) {
	this.id_fonction = id_fonction;
}
public String getNom_fonction() {
	return nom_fonction;
}
public void setNom_fonction(String nom_fonction) {
	this.nom_fonction = nom_fonction;
}
public String getType_fonction() {
	return type_fonction;
}
public void setType_fonction(String type_fonction) {
	this.type_fonction = type_fonction;
}


@Override
public String toString() {
	return "fonction [id_fonction=" + id_fonction + ", nom_fonction=" + nom_fonction + ", personnels="  + "]";
}



}


