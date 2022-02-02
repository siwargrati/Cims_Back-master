package Cims.PFE.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Notification") 
public class Notification {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="id_notification")
	private Long id_notification;
	
	@Column(name="message")
	private String message;
	
	@Column(name="etat")
	private boolean etat;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
	private Compte comptes;

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Notification(Long id_notification, String message, boolean etat, Compte compte) {
		super();
		this.id_notification = id_notification;
		this.message = message;
		this.etat = etat;
		this.comptes = compte;
	}



	public Long getId_notification() {
		return id_notification;
	}

	public void setId_notification(Long id_notification) {
		this.id_notification = id_notification;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}



	public Compte getCompte() {
		return comptes;
	}



	public void setCompte(Compte compte) {
		this.comptes = compte;
	}



	
	
}
