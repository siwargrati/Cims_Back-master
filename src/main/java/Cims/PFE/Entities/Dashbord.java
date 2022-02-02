package Cims.PFE.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dashbord {
	@Id   
	@Column(name="axeX")
	private int axeX;
	@Column(name="axeY")
	private String axeY;
	
	
	public int getAxeX() {
		return axeX;
	}


	public void setAxeX(int axeX) {
		this.axeX = axeX;
	}


	public String getAxeY() {
		return axeY;
	}


	public void setAxeY(String axeY) {
		this.axeY = axeY;
	}


	public Dashbord() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	 public Dashbord(int axeX, String axeY) {
		super();
		this.axeX = axeX;
		this.axeY = axeY;
		
	}


	@Override
	public String toString() {
		return "Dashbord [axeX=" + axeX + ", axeY=" + axeY + "]";
	}
	
	
	
	
}
