package Cims.PFE.Utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateChecker {
	
	
	
	public static boolean isHoliday(LocalDate dateDebut, LocalDate dateFin) {
		if ( dateDebut.getDayOfWeek().equals(DayOfWeek.SUNDAY)|| dateFin.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				return true;
			}
		
		return false;
	}
	
	public static boolean isToday(LocalDate dateDebut) {
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		LocalDate days=LocalDate.now().plusDays(3);
		if ((dateDebut.isEqual(today))|| (dateDebut.isBefore(days))) {
			return true;
		}
		return false;
	}	
	
	

	//donne le nombre de jours entre deux date
	public  static int  nbrDay(LocalDate date1, LocalDate date2,List<String> day) {
		
		List<Integer> jours=new ArrayList<Integer>();
		jours=getDayOfWeekAsInt(day);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date dateDebut = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
		
		Date dateFin = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
		Calendar calendar3= Calendar.getInstance();
		Calendar calendar1= Calendar.getInstance();
		calendar1.setTime(dateDebut);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(dateFin);
		long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
		long numberOfDay = (long)diff/86400000;
		int l=0;
		if(numberOfDay != 0){
			for(int i=0;i<=numberOfDay;i++){
				for(int j=0;j<jours.size();j++) {
			if(calendar1.get(Calendar.DAY_OF_WEEK) == jours.get(j)) {
			l++;
			calendar3.setTime( calendar1.getTime());
			Date d=calendar3.getTime();
			j++;
			}
			}
			calendar1.add(Calendar.DAY_OF_MONTH, 1);
			}}
			return l;
	}
	
	//donne les dates des jours entre deux date
	public  static List<Date>  Days(LocalDate date1, LocalDate date2,List<String> day) {
		List<Date> days=new ArrayList<Date>();
		List<Integer> jours=new ArrayList<Integer>();
		jours=getDayOfWeekAsInt(day);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date dateDebut = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
		
		Date dateFin = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
		Calendar calendar3= Calendar.getInstance();
		Calendar calendar1= Calendar.getInstance();
		calendar1.setTime(dateDebut);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(dateFin);
		long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
		long numberOfDay = (long)diff/86400000;
		int l=0;
		if(numberOfDay != 0){
			for(int i=0;i<numberOfDay;i++){
				for(int j=0;j<jours.size();j++) {
			if(calendar1.get(Calendar.DAY_OF_WEEK) == jours.get(j)) {
			l++;
			calendar3.setTime( calendar1.getTime());
			 
			days.add(calendar1.getTime());
			j++;
			}
				}
			calendar1.add(Calendar.DAY_OF_MONTH, 1);
			}}
		
			return days;
	}
	
	public static List<Integer> getDayOfWeekAsInt(List<String> day) {
		List<Integer> nbr=new ArrayList<Integer>();
	    if (day == null) {
	        return null;
	    }
	 int i=0;
	    for(i=0;i<day.size();i++) {
	    	switch (day.get(i).toLowerCase()) {
	    	
	        case "lundi":
	        	nbr.add(Calendar.MONDAY);
	        	 break;
	        case "mardi":
	            nbr.add(Calendar.TUESDAY);
	          break;
	        case "mercredi":
	        	nbr.add(Calendar.WEDNESDAY);
	        	 break;
	        case "jeudi":
	        	nbr.add(Calendar.THURSDAY);
	        	 break;
	        case "vendredi":
	        	nbr.add(Calendar.FRIDAY);
	        	 break;
	        case "samedi":
	        	nbr.add(Calendar.SATURDAY);
	        	 break;
	        default: 
	        	 break;
	        	
	    }
	    	
	    }
	    return nbr;
	}
	
	
	
}
