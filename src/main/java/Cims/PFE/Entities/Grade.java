package Cims.PFE.Entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Transactional
@Entity  
@Table(name="grade") 
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name="id_grade")
	private Long id_grade;
	
	@Column(name="nom_grade_fr")
	private String nom_grade_fr;
	
	@Column(name="nom_grade_ar")
	private String nom_grade_ar;
	
	@Column(name="categorie_grade_fr")
	private String categorie_grade_fr;
	
	@Column(name="categorie_grade_ar")
	private String categorie_grade_ar;
	
	@OneToMany(mappedBy="grade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personnel> personnels ;
	
	

   public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
public Grade(Long id_grade, String nom_grade_fr, String nom_grade_ar,String categorie_grade_fr, String categorie_grade_ar) {
	super();
	this.id_grade = id_grade;
	this.nom_grade_fr = nom_grade_fr;
	this.nom_grade_ar = nom_grade_ar;
	this.categorie_grade_fr = categorie_grade_fr;
	this.categorie_grade_ar = categorie_grade_ar;
	//this.personnels = personnels;
}

public Long getId_grade() {
	return id_grade;
}
public void setId_grade(Long id_grade) {
	this.id_grade = id_grade;
}
public String getNom_grade_fr() {
	return nom_grade_fr;
}
public void setNom_grade_fr(String nom_grade_fr) {
	this.nom_grade_fr = nom_grade_fr;
}
public String getNom_grade_ar() {
	return nom_grade_ar;
}
public void setNom_grade_ar(String nom_grade_ar) {
	this.nom_grade_ar = nom_grade_ar;
}
public String getCategorie_grade_fr() {
	return categorie_grade_fr;
}
public void setCategorie_grade_fr(String categorie_grade_fr) {
	this.categorie_grade_fr = categorie_grade_fr;
}
public String getCategorie_grade_ar() {
	return categorie_grade_ar;
}
public void setCategorie_grade_ar(String categorie_grade_ar) {
	this.categorie_grade_ar = categorie_grade_ar;
}
@Override
public String toString() {
	return "Grade [id_grade=" + id_grade + ", nom_grade_fr=" + nom_grade_fr + ", personnels="  + "]";
}



}


