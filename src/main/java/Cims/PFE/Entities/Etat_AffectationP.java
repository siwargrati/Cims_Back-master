package Cims.PFE.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "etat_affectationp")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Etat_AffectationP {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id_etat;

		@Enumerated(EnumType.STRING)
		@Column(name="nom_etat")
		private Type_Etat_AffP nomEtat;

		@OneToMany(mappedBy="etat", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
		    private List<AffectationPartielle> affectationPartielles ;
		
		
		
		public Etat_AffectationP() {
		}

		@Override
		public String toString() {
			return "Etat_AffectationP [id=" + id_etat + ", nom=" + nomEtat + "]";
		}


		public Etat_AffectationP(int id_etat, Type_Etat_AffP nom_etat,
				List<AffectationPartielle> affectationPartielles) {
			super();
			this.id_etat = id_etat;
			this.nomEtat = nom_etat;
			this.affectationPartielles = affectationPartielles;
		}


		public int getId_etat() {
			return id_etat;
		}


		public void setId_etat(int id_etat) {
			this.id_etat = id_etat;
		}


		public Type_Etat_AffP getNom_etat() {
			return nomEtat;
		}


		public void setNom_etat(Type_Etat_AffP nom_etat) {
			this.nomEtat = nom_etat;
		}

		@JsonIgnore
		public List<AffectationPartielle> getAffectationPartielles() {
			return affectationPartielles;
		}


		public void setAffectationPartielles(List<AffectationPartielle> affectationPartielles) {
			this.affectationPartielles = affectationPartielles;
		}
		
		
}
