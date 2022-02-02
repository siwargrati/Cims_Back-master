package Cims.PFE.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.interfaces.PdfRunDirection;

import Cims.PFE.Controller.AffectationPartielleController;
import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.OrdreAffPAttRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.Deplacement;
import Cims.PFE.Entities.Hebergement;
import Cims.PFE.Entities.Mission;
import Cims.PFE.Entities.MoyenDeTransport;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPAtt;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
import Cims.PFE.Entities.OrdreMission;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Transport;
import net.bytebuddy.build.Plugin.Engine.Summary;

public class generatePdf {
	
    public static final String IMG1 = "src\\main\\resources\\images\\logo.png";
    public static final String IMG2 = "src\\main\\resources\\images\\deplacement1.jpg";
    public static final String IMG3 = "src\\main\\resources\\images\\deplacement2.jpg";
    public static final String IMG4 = "src\\main\\resources\\images\\deplacement3.jpg";

    public static final String check = "src\\main\\resources\\winding.ttf";


    
	@Autowired
	private static OrdreAffPAttRepository repo;
	
	@Autowired
	private static AffectationPartielleRepository repo1;
	
	@Autowired
	private static AffectationPartielleController aff;
	
	//creation de forme de  pdf par id d'affectation
	public static ByteArrayInputStream Report(OrdreAffectationP o) throws MalformedURLException, IOException {

		Document document = new Document();
		document.setMargins(60, 60, 60, 60);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			 Font f = new Font();
	            f.setStyle(Font.BOLD);
	            f.setSize(15);
	            
	            Font f2 = new Font();
	            f2.setSize(15);
	            
	            Font f3 = new Font();
	            f3.setStyle(Font.BOLD);
	            f3.setSize(13);
	            
	            Font f4 = new Font();
	            f4.setSize(13);
	            
	            Font f5 = new Font();
	            f5.setStyle(Font.BOLD);
	            f5.setSize(14);
	            
	            Image img1 = Image.getInstance(IMG1);
	            img1.scaleAbsoluteWidth(60);
	            img1.scaleAbsoluteHeight(30);
	            img1.setAlignment(Element.ALIGN_LEFT);
	            
	            PdfPTable table2 = new PdfPTable(1);
	            table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);        
	            PdfPCell cell0 = new PdfPCell();    

	            cell0.addElement(img1);
	            cell0.setBorder(Rectangle.NO_BORDER); 
	            cell0.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            cell0.setPaddingLeft(-50);
	            table2.addCell(cell0);

	            PdfPCell cell01 = new PdfPCell(new Paragraph("\n République tunisienne \n Ministère de la santé \n Centre de l'informatique \n",f));    
	            cell01.setBorder(Rectangle.NO_BORDER); 
	            cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell01.setPaddingTop(-70);
	            table2.addCell(cell01);
         
         
			
         Paragraph p4 = new Paragraph();
         p4.setFont(f2);
         p4.add("\n \n \n Au directeur régional de "+o.getAffectationPartielle().getSite().getGouvernorat().getNomGouvFR()+"  \n \n");
         p4.setAlignment(Element.ALIGN_CENTER);
         
         Paragraph p5 = new Paragraph();
         p5.setFont(f3);
         p5.add(" Sujet : ");
         p5.setFont(f4);
         p5.setLeading(35);
         p5.add(o.getAffectationPartielle().getSujet()+"\n"
         		+ " Nous avons l'honneur de vous informer que le Centre d'Informatique du Ministère de la Santé a chargé M./Mme ");
         p5.setFont(f3);
         p5.add(o.getAffectationPartielle().getPersonnel().getNom_AR().toUpperCase()+" "+o.getAffectationPartielle().getPersonnel().getPrenom_AR().toUpperCase());
         p5.setFont(f4);
         p5.add(" en affectation partielle à   ");
         p5.setFont(f3);
         p5.add(o.getAffectationPartielle().getSite().getNom_etablissement_fr());
         p5.setFont(f4);
         p5.add(" pour le suivi des applications informatiques de votre organisation chaque ");
         p5.setFont(f3);
         for(int i=0;i<o.getAffectationPartielle().getJour().size();i++) {
        	 p5.add(o.getAffectationPartielle().getJour().get(i)+", ");
         }
        
         p5.setFont(f4);
         p5.add(" à partir de  ");
         p5.setFont(f3);
         p5.add(o.getAffectationPartielle().getDateDebut()+"");
         p5.add(" au "+o.getAffectationPartielle().getDatefin()+".\n");
         p5.setAlignment(Element.ALIGN_LEFT);
         
         Paragraph p6 = new Paragraph();
         p6.setFont(f5);
         p6.add(" \n \n \n \n  Signature  ");
         p6.setAlignment(Element.ALIGN_CENTER);
               
         PdfPCell leftCell = new PdfPCell();
         leftCell.setBorder(Rectangle.NO_BORDER);

         PdfPTable headerTable = new PdfPTable(2);
         headerTable.setWidthPercentage(100f);
         headerTable.addCell(leftCell);

         PdfPCell rightCell = new PdfPCell();

         rightCell .setBorder(Rectangle.NO_BORDER);
         rightCell .setPaddingLeft(-390);
         headerTable.addCell(rightCell);
         PdfPCell cell = new PdfPCell();
         cell.setBorder(Rectangle.NO_BORDER);
         cell.addElement(headerTable);
         
         document.add(table2);   
         document.add(headerTable);	
			document.add(p4);
			document.add(p5);
			document.add(p6);
			
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	//pdf AFFPATT
	//creation de forme de  pdf par id d'affectation
	public static ByteArrayInputStream ReportAtt(OrdreAffectationPAtt o) throws MalformedURLException, IOException {

		Document document = new Document();
		document.setMargins(60, 60, 60, 60);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			
	        Font f = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f.setStyle(Font.BOLD);
	         f.setSize(35);
	        Font f11 = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f11.setSize(20); 
			
		        Font f1 = new Font(BaseFont.createFont("src\\main\\resources\\trado.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	            f1.setSize(25);
	            
	            Font f2 = new Font();
	            f2.setSize(15);
	            
	            Font f3 = new Font();
	            f3.setStyle(Font.BOLD);
	            f3.setSize(13);
	            
	            Font f4 = new Font();
	            f4.setSize(13);
	            
	            Font f5 = new Font();
	            f5.setStyle(Font.BOLD);
	            f5.setSize(14);
	     
	          
	     Image img1 = Image.getInstance(IMG1);
         img1.scaleAbsoluteWidth(60);
         img1.scaleAbsoluteHeight(30);
         img1.setAlignment(Element.ALIGN_LEFT);
         
         PdfPTable table2 = new PdfPTable(1);
         table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

         String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         
         PdfPCell cell0 = new PdfPCell();    

         cell0.addElement(img1);
         cell0.setBorder(Rectangle.NO_BORDER); 
         cell0.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell0.setPaddingLeft(-50);

         PdfPCell cell01 = new PdfPCell(new Paragraph("الجمهــوريـة التــونـســيـة \n وزارة الصــحـة \n مـركــز الإعــلامــية \n",f11));    
         cell01.setBorder(Rectangle.NO_BORDER); 
         cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell01.setPaddingTop(-70);
         
         PdfPCell cell1 = new PdfPCell(new Paragraph(" تونس في" + date+"\n \n",f11));
         cell1.setBorder(Rectangle.NO_BORDER); 
         cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell1.setPaddingLeft(-50);


                 
         PdfPCell cell2 = new PdfPCell(new Paragraph("  إلى السيد(ة) "+ o.getAffectationPartielle().getSite().getQualite_direction_ar()+"  "+o.getAffectationPartielle().getSite().getNom_etablissement_ar()+" بولاية "+ o.getAffectationPartielle().getSite().getGouvernorat().getNomGouvAR(), f1));
         cell2.setBorder(Rectangle.NO_BORDER); 
         cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         PdfPCell cell3 = new PdfPCell(new Paragraph("\n \n الموضوع  :  تكليف بمهمة ",f));
         cell3.setBorder(Rectangle.NO_BORDER); 
         cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell3.setPaddingLeft(-70);
         cell3.setPaddingRight(-20);
        
         Paragraph p5 = new Paragraph();
         p5.setFont(f3);        	 
         String s1="Lundi";String s2="Mardi";String s3="Mercredi";String s4="Jeudi";String s5="Vendredi";String s6="Samedi";
         for(int i=0;i<o.getAffectationPartielle().getJour().size();i++) {
        	 String day=o.getAffectationPartielle().getJour().get(i);
        	 if (day.equalsIgnoreCase(s1))
        		p5.add("الاثنين"+" ");
        	 else 
        		 if (day.equals(s2))
        			 p5.add("الثلاثاء"+" ");
        		 else
        			 if (day.equals(s3))
            			 p5.add("الأربعاء"+" ");
        			 else
        				 if (day.equals(s4))
                			 p5.add("الخميس"+" ");
        				 else
        					 if (day.equals(s5))
        	        			 p5.add("الجمعة"+" ");
        					 else 
        						 if (day.equals(s6))
        						 p5.add("السبت"+" ");
        				 
         }

        	 PdfPCell cell4 = new PdfPCell(new Paragraph(" و بعد \n يشرفني أن أعلمكم بأن مركز الإعلامية لوزارة الصحة كلف السيد(ة) " + o.getAffectationPartielle().getPersonnel().getNom_AR()+" "+o.getAffectationPartielle().getPersonnel().getPrenom_AR()+"،  "+o.getAffectationPartielle().getPersonnel().getGrade().getNom_grade_ar()+"لمتابعة التطبيقات الإعلامية بمؤسستكم وذلك إبتداء من تاريخ " + o.getAffectationPartielle().getDateDebut().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+ " وذلك كل يوم \n"+ p5 +" \n \n \n",f1));
      
         cell4.setBorder(Rectangle.NO_BORDER); 
         cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell4.setPaddingLeft(-70);
         cell4.setPaddingRight(-20);
         cell4.setLeading(1.5f, 1.5f);
         
         
         PdfPCell cell5 = new PdfPCell(new Paragraph(" المديــــــــــــر العــــــــام \n \n لطـفــي العـلانـــي  ",f));
         cell5.setBorder(Rectangle.NO_BORDER); 
         cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
         table2.addCell(cell0); 
         table2.addCell(cell01);

         table2.addCell(cell1);    
         table2.addCell(cell2);          
         table2.addCell(cell3);
         table2.addCell(cell4);
         table2.addCell(cell5);

         document.add(table2);
             		
			
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	//creation de forme de  pdf par id d'affectation
		public static ByteArrayInputStream ReportAttInf(OrdreAffectationPAtt o ) throws MalformedURLException, IOException {
		

			
			Document document = new Document();
			document.setMargins(60, 60, 60, 60);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			try {

				PdfWriter.getInstance(document, out);
				document.open();
				
				
		        Font f = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		         f.setStyle(Font.BOLD);
		         f.setSize(35);
		        Font f11 = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		         f11.setSize(20); 
				
			        Font f1 = new Font(BaseFont.createFont("src\\main\\resources\\trado.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		            f1.setSize(25);
		            
		            Font f2 = new Font();
		            f2.setSize(15);
		            
		            Font f3 = new Font();
		            f3.setStyle(Font.BOLD);
		            f3.setSize(13);
		            
		            Font f4 = new Font();
		            f4.setSize(13);
		            
		            Font f5 = new Font();
		            f5.setStyle(Font.BOLD);
		            f5.setSize(14);
		     
		          
		     Image img1 = Image.getInstance(IMG1);
	         img1.scaleAbsoluteWidth(60);
	         img1.scaleAbsoluteHeight(30);
	         img1.setAlignment(Element.ALIGN_LEFT);
	         
	         PdfPTable table2 = new PdfPTable(1);
	         table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

	         String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	         
	         PdfPCell cell0 = new PdfPCell();    

	         cell0.addElement(img1);
	         cell0.setBorder(Rectangle.NO_BORDER); 
	         cell0.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         cell0.setPaddingLeft(-50);

	         PdfPCell cell01 = new PdfPCell(new Paragraph("الجمهــوريـة التــونـســيـة \n وزارة الصــحـة \n مـركــز الإعــلامــية \n",f11));    
	         cell01.setBorder(Rectangle.NO_BORDER); 
	         cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
	         cell01.setPaddingTop(-70);
	         
	         PdfPCell cell1 = new PdfPCell(new Paragraph(" تونس في" + date+"\n \n",f11));
	         cell1.setBorder(Rectangle.NO_BORDER); 
	         cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         cell1.setPaddingLeft(-50);

	         Paragraph p5 = new Paragraph();
	         p5.setFont(f3);
	      	 
	         String s1="Lundi";String s2="Mardi";String s3="Mercredi";String s4="Jeudi";String s5="Vendredi";String s6="Samedi";
	         for(int i=0;i<o.getAffectationPartielle().getJour().size();i++) {
	        	 String day=o.getAffectationPartielle().getJour().get(i);
	        	 if (day.equalsIgnoreCase(s1))
	        		p5.add("الاثنين"+" ");
	        	 else 
	        		 if (day.equals(s2))
	        			 p5.add("الثلاثاء"+" ");
	        		 else
	        			 if (day.equals(s3))
	            			 p5.add("الأربعاء"+" ");
	        			 else
	        				 if (day.equals(s4))
	                			 p5.add("الخميس"+" ");
	        				 else
	        					 if (day.equals(s5))
	        	        			 p5.add("الجمعة"+" ");
	        					 else 
	        						 if (day.equals(s6))
	        						 p5.add("السبت"+" ");
	        				 
	         }
	         
	         
	             
	         PdfPCell cell2 = new PdfPCell(new Paragraph("  إلى السيد(ة) "+ o.getAffectationPartielle().getPersonnel().getSite().getQualite_direction_ar()+"  "+o.getAffectationPartielle().getPersonnel().getSite().getNom_etablissement_ar()+" بولاية "+ o.getAffectationPartielle().getPersonnel().getSite().getGouvernorat().getNomGouvAR(), f1));         
	         cell2.setBorder(Rectangle.NO_BORDER); 
	         cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	         
	         PdfPCell cell3 = new PdfPCell(new Paragraph("\n \n الموضوع  :  تكليف بمهمة ",f));
	         cell3.setBorder(Rectangle.NO_BORDER); 
	         cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell3.setPaddingLeft(-70);
	         cell3.setPaddingRight(-20);
	         
	         PdfPCell cell4 = new PdfPCell(new Paragraph(" و بعد \n يشرفني أن أعلمكم بأن مركز الإعلامية لوزارة الصحة كلف السيد(ة)  " + o.getAffectationPartielle().getPersonnel().getNom_AR()+" "+o.getAffectationPartielle().getPersonnel().getPrenom_AR()+"،  "+o.getAffectationPartielle().getPersonnel().getGrade().getNom_grade_ar()+"ممثل المركز بمؤسستكم،  بمتابعة التطبيقات الإعلامية حسب الجدول الزمني التالي : \n ",f1));
	       
	         
	         cell4.setBorder(Rectangle.NO_BORDER); 
	         cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell4.setPaddingLeft(-70);
	         cell4.setPaddingRight(-20);
	         cell4.setLeading(1.5f, 1.5f);	         
	         
				
	         
	         
	         PdfPCell cell5 = new PdfPCell(new Paragraph(o.getAffectationPartielle().getSite().getNom_etablissement_ar()+ " وذلك  يوم"  + p5 +" \n \n",f1));
			  

	       
	         		
		       		cell5.setBorder(Rectangle.NO_BORDER); 
					cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell5.setPaddingLeft(-70);
					cell5.setPaddingRight(-20);
					cell5.setLeading(1.5f, 1.5f);

	          
	         

	         PdfPCell cell6 = new PdfPCell(new Paragraph(" المديــــــــــــر العــــــــام \n \n لطـفــي العـلانـــي  ",f));
	         cell6.setBorder(Rectangle.NO_BORDER); 
	         cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         table2.addCell(cell0); 
	         table2.addCell(cell01);

	         table2.addCell(cell1);    
	         table2.addCell(cell2);          
	         table2.addCell(cell3);
	         table2.addCell(cell4);	 	
	         table2.addCell(cell5);
	         table2.addCell(cell6);
	         document.add(table2);
	         //document.add(p5);

	         
	       
	        
	         
	         
	      /*   Image img = Image.getInstance("C:\\Users\\siwar\\Pictures\\stage\\Cims-angular-master\\v3\\spring-boot-spring-security-jwt-authentication-master\\cims-logo.png");
	         img.scaleAbsoluteWidth(60);
	         img.scaleAbsoluteHeight(30);
	         img.setAlignment(Element.ALIGN_LEFT);*/
	         PdfPCell leftCell = new PdfPCell();
	         //leftCell.addElement(img);
	         leftCell.setBorder(Rectangle.NO_BORDER);

	         PdfPTable headerTable = new PdfPTable(2);
	         headerTable.setWidthPercentage(100f);
	         headerTable.addCell(leftCell);

	         PdfPCell rightCell = new PdfPCell();

	         rightCell .setBorder(Rectangle.NO_BORDER);
	         rightCell .setPaddingLeft(-390);
	         headerTable.addCell(rightCell);
	         PdfPCell cell = new PdfPCell();
	         cell.setBorder(Rectangle.NO_BORDER);
	         cell.addElement(headerTable);
	         
	        document.add(headerTable);	
			
				
				document.close();

			} catch (DocumentException ex) {

				Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
			}

			return new ByteArrayInputStream(out.toByteArray());
		}
		
	// pdf mission
	public static ByteArrayInputStream ReportMission(OrdreMission m) throws MalformedURLException, IOException {

		Document document = new Document();
		document.setMargins(60, 60, 60, 60);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

		    PdfWriter writer = PdfWriter.getInstance(document, out);

			document.open();
			 Font f = new Font();
	            f.setStyle(Font.BOLD);
	            f.setSize(15);
	            
	            Font f2 = new Font();
	            f2.setSize(15);
	            
	            Font f3 = new Font();
	            f3.setStyle(Font.BOLD);
	            f3.setSize(12);
	            
	            Font f4 = new Font();
	            f4.setSize(13);
	            
	            Font f5 = new Font();
	            f5.setStyle(Font.BOLD);
	            f5.setSize(14);
	            
			Paragraph p = new Paragraph();
			 p.setFont(f);
      p.add("République tunisienne \n Ministère de la santé \n Centre de l'informatique \n");
      p.setAlignment(Element.ALIGN_CENTER);
      
  
			
      Paragraph p4 = new Paragraph();
      p4.setFont(f2);
      p4.add("\n \n ORDRE DE MISSION \n ");
      p4.setAlignment(Element.ALIGN_CENTER);
      
      
      String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      Paragraph p5 = new Paragraph();
     
      p5.setFont(f3);
      p5.setLeading(25);

      p5.add(" Le directeur Général du Centre informatique du ministère de la santé a confié à:");
      p5.setFont(f4);
      p5.add("Mr / Mme :"+m.getMission().getAffectationPartielle().getPersonnel().getNom()+" "+m.getMission().getAffectationPartielle().getPersonnel().getPrenom()+"  , Grade :"+m.getMission().getAffectationPartielle().getPersonnel().getGrade().getNom_grade_fr()+"\n");
      p5.setFont(f4);
      p5.add("La mission suivante : Assistance Technique à : " +m.getMission().getAffectationPartielle().getSite().getNom_etablissement_fr()+"\n");
      p5.add("Itinéraire de :  "+m.getMission().getAffectationPartielle().getPersonnel().getSite().getNom_etablissement_fr()+ " à "+m.getMission().getAffectationPartielle().getSite().getNom_etablissement_fr()+"\n");
      p5.add("Date de départ : "+m.getMission().getDate()+ " à "+m.getMission().getHeureDepart()+"\n");
      p5.add("Date de Retour : "+m.getMission().getDate()+ " à "+m.getMission().getHeureArrivee()+"\n");

      Paragraph p7 = new Paragraph();
      p7.setFont(f4);

      PdfPCell cell1 = new PdfPCell();
      
      p7.add(" Frais de missions :\n");
     
      PdfPTable table2 = new PdfPTable(1);
	  table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	  
      p7.setFont(f3);
      
      ParagraphBorder border = new ParagraphBorder();
      writer.setPageEvent(border);
      
      //DEPLACEMENT
      p7.setFont(f4);    	 

      p7.add("\n Frais de déplacement (repas) à la charge de: \n");
      p7.setFont(f3);
      if(m.getDeplacement()==Deplacement.CIMS) {
    	  Image image1 = Image.getInstance(IMG2);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
      }else if(m.getDeplacement()==Deplacement.MISSIONNAIRE) {
    	  Image image1 = Image.getInstance(IMG3);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);

      }
      else if (m.getDeplacement()==Deplacement.ORAGANISME_DACCUEIL) {
    	  Image image1 = Image.getInstance(IMG4);
    	  image1.scaleAbsolute(450, 20);
  	      p7.add (image1);

      }
      

      
      // HEBERGEMENT
      p7.setFont(f4);
      p7.add("\n Frais d’hébergement à la charge de: \n");
      p7.setFont(f3);
      if(m.getHebergement()==Hebergement.CIMS) {
    	  Image image1 = Image.getInstance(IMG2);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
      }else if(m.getHebergement()==Hebergement.MISSIONNAIRE) {
    	  Image image1 = Image.getInstance(IMG3);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
      }
      else if(m.getHebergement()==Hebergement.NON) {
    	  p7.add("PAS D'HEBERGEMENT");
      }else if(m.getHebergement()==Hebergement.ORAGANISME_DACCUEIL) {
    	  Image image1 = Image.getInstance(IMG4);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
      }
      
      //transport 
      p7.setFont(f4);
      p7.add("\n Frais de transport à la charge de: \n");
      p7.setFont(f3);
      if(m.getTransport()==Transport.CIMS) {    	
    	  Image image1 = Image.getInstance(IMG2);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
    	  p7.add("\n Voiture CIMS n°"+"........................................... , Chauffeur ............................................");
    	  p7.add("\n Observation: .......................................................................................................\n");
      }else if(m.getTransport()==Transport.MISSIONNAIRE) {
    	  Image image1 = Image.getInstance(IMG3);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
          p7.add("\n Le moyen de transport : ");
          p7.setFont(f4);
    	  if(m.getMoyenDeTransport()==MoyenDeTransport.PERSONNELLE) {
        	  p7.add("\n Voiture personnelle de :"+ m.getMission().getProprietaire()+" ,Km parcourus(Aller/Retour) : "+m.getMission().getNbkm()+"\n");

    	  }else if(m.getMoyenDeTransport()==MoyenDeTransport.PUBLIC){
    		  p7.add(" PUBLIC");
    	  }
      }
      else if(m.getTransport()==Transport.ORAGANISME_DACCUEIL) {
    	  Image image1 = Image.getInstance(IMG4);
    	  image1.scaleAbsolute(450, 20);
    	  p7.add (image1);
      }
      p7.setLeading(20);

      p7.add("                    Visa et date SMC                            Visa et date DAAF  \n\n\n");
      
      Paragraph p6 = new Paragraph();
      p6.setFont(f5);
      p6.add(" \n \n Cachet du bureau d'ordre central              Visa DIRECTEUR GENERAL");
      p6.setAlignment(Element.ALIGN_CENTER);
      

      PdfPTable headerTable = new PdfPTable(2);
      headerTable.setWidthPercentage(100f);
     // headerTable.addCell(leftCell);

      PdfPCell rightCell = new PdfPCell();
      rightCell .addElement(p);

      rightCell .setBorder(Rectangle.NO_BORDER);
      rightCell .setPaddingLeft(-390);
      headerTable.addCell(rightCell);
      PdfPCell cell = new PdfPCell();
      cell.setBorder(Rectangle.NO_BORDER);
      cell.addElement(headerTable);
      
     document.add(headerTable);
     		document.add(p);
			document.add(p4);
			document.add(p5);
		    border.setActive(true);
			document.add(p7);
		    border.setActive(false);

			document.add(p6);
			
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}


	
	public static ByteArrayInputStream ReportPers(OrdreAffectationPers o) throws MalformedURLException, IOException {

		Document document = new Document();
		document.setMargins(60, 60, 60, 60);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			
	        Font f = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f.setStyle(Font.BOLD);
	         f.setSize(35);
	        Font f11 = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f11.setSize(20); 
			
		        Font f1 = new Font(BaseFont.createFont("src\\main\\resources\\trado.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	            f1.setSize(25);
	            
	            Font f2 = new Font();
	            f2.setSize(15);
	            
	            Font f3 = new Font();
	            f3.setStyle(Font.BOLD);
	            f3.setSize(13);
	            
	            Font f4 = new Font();
	            f4.setSize(13);
	            
	            Font f5 = new Font();
	            f5.setStyle(Font.BOLD);
	            f5.setSize(14);
	     
	          
	     Image img1 = Image.getInstance(IMG1);
         img1.scaleAbsoluteWidth(60);
         img1.scaleAbsoluteHeight(30);
         img1.setAlignment(Element.ALIGN_LEFT);
         
         PdfPTable table2 = new PdfPTable(1);
         table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

         String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         
         PdfPCell cell0 = new PdfPCell();    

         cell0.addElement(img1);
         cell0.setBorder(Rectangle.NO_BORDER); 
         cell0.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell0.setPaddingLeft(-50);

         PdfPCell cell01 = new PdfPCell(new Paragraph("الجمهــوريـة التــونـســيـة \n وزارة الصــحـة \n مـركــز الإعــلامــية \n",f11));    
         cell01.setBorder(Rectangle.NO_BORDER); 
         cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell01.setPaddingTop(-70);
         
         PdfPCell cell1 = new PdfPCell(new Paragraph(" تونس في" + date+"\n \n",f11));
         cell1.setBorder(Rectangle.NO_BORDER); 
         cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell1.setPaddingLeft(-50);


                 
         PdfPCell cell2 = new PdfPCell(new Paragraph(" ادارة الشؤون الادارية و  المالية \n ", f11));
         cell2.setBorder(Rectangle.NO_BORDER); 
         cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
         
         PdfPCell cell3 = new PdfPCell(new Paragraph(" مذكرة تعيين \n \n ",f));
         cell3.setBorder(Rectangle.NO_BORDER); 
         cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         PdfPCell cell4 = new PdfPCell(new Paragraph("إبتداء من " + o.getPersonnel().getDate_recrutement().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"، يقع تعيين السيد(ة) "+ o.getPersonnel().getNom_AR()+" "+o.getPersonnel().getPrenom_AR()+"،  "+o.getPersonnel().getGrade().getNom_grade_ar()+" بمركز الإعلامية لوزارة الصحة  بـ " + o.getPersonnel().getStructure().getDirection_a(),f1));
         cell4.setBorder(Rectangle.NO_BORDER); 
         cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell4.setPaddingLeft(-70);
         cell4.setPaddingRight(-20);
         cell4.setLeading(1.5f, 1.5f);
         PdfPCell cell5 = new PdfPCell(); 
        
        	if (o.getPersonnel().getSite().getIdSite()!=2 && o.getPersonnel().getStructure().getService() != null)
        		{   cell5 = new PdfPCell(new Paragraph(" بــ "+o.getPersonnel().getStructure().getService_a(),f1));
        		}
         cell5.setBorder(Rectangle.NO_BORDER); 
         cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell5.setPaddingLeft(-70);
         cell5.setPaddingRight(-20);
         cell5.setLeading(1.5f, 1.5f);
         
         PdfPCell cell6 = new PdfPCell(new Paragraph("\n \n المديــــــــــــر العــــــــام \n \n لطـفــي العـلانـــي  ",f));
         cell6.setBorder(Rectangle.NO_BORDER); 
         cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
         table2.addCell(cell0); 
         table2.addCell(cell01);

         table2.addCell(cell1);    
         table2.addCell(cell2);          
         table2.addCell(cell3);
         table2.addCell(cell4);
         table2.addCell(cell5);
         table2.addCell(cell6);

         document.add(table2);

         
         
       
        
         
         
      /*   Image img = Image.getInstance("C:\\Users\\siwar\\Pictures\\stage\\Cims-angular-master\\v3\\spring-boot-spring-security-jwt-authentication-master\\cims-logo.png");
         img.scaleAbsoluteWidth(60);
         img.scaleAbsoluteHeight(30);
         img.setAlignment(Element.ALIGN_LEFT);*/
         PdfPCell leftCell = new PdfPCell();
         //leftCell.addElement(img);
         leftCell.setBorder(Rectangle.NO_BORDER);

         PdfPTable headerTable = new PdfPTable(2);
         headerTable.setWidthPercentage(100f);
         headerTable.addCell(leftCell);

         PdfPCell rightCell = new PdfPCell();

         rightCell .setBorder(Rectangle.NO_BORDER);
         rightCell .setPaddingLeft(-390);
         headerTable.addCell(rightCell);
         PdfPCell cell = new PdfPCell();
         cell.setBorder(Rectangle.NO_BORDER);
         cell.addElement(headerTable);
         
        document.add(headerTable);	
		
			
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	
	public static ByteArrayInputStream ReportTot(OrdreAffectationTot o) throws MalformedURLException, IOException {

		Document document = new Document();
		document.setMargins(60, 60, 60, 60);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			
	        Font f = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f.setStyle(Font.BOLD);
	         f.setSize(35);
	        Font f11 = new Font(BaseFont.createFont("src\\main\\resources\\arabtype.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	         f11.setSize(20); 
			
		        Font f1 = new Font(BaseFont.createFont("src\\main\\resources\\trado.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	            f1.setSize(25);
	            
	            Font f2 = new Font();
	            f2.setSize(15);
	            
	            Font f3 = new Font();
	            f3.setStyle(Font.BOLD);
	            f3.setSize(13);
	            
	            Font f4 = new Font();
	            f4.setSize(13);
	            
	            Font f5 = new Font();
	            f5.setStyle(Font.BOLD);
	            f5.setSize(14);
	     
	          
	     Image img1 = Image.getInstance(IMG1);
         img1.scaleAbsoluteWidth(60);
         img1.scaleAbsoluteHeight(30);
         img1.setAlignment(Element.ALIGN_LEFT);
         
         PdfPTable table2 = new PdfPTable(1);
         table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

         String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         
         PdfPCell cell0 = new PdfPCell();    

         cell0.addElement(img1);
         cell0.setBorder(Rectangle.NO_BORDER); 
         cell0.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell0.setPaddingLeft(-50);

         PdfPCell cell01 = new PdfPCell(new Paragraph("الجمهــوريـة التــونـســيـة \n وزارة الصــحـة \n مـركــز الإعــلامــية \n",f11));    
         cell01.setBorder(Rectangle.NO_BORDER); 
         cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell01.setPaddingTop(-70);
         
         PdfPCell cell1 = new PdfPCell(new Paragraph(" تونس في" + date+"\n \n",f11));
         cell1.setBorder(Rectangle.NO_BORDER); 
         cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell1.setPaddingLeft(-50);
                 
         PdfPCell cell2 = new PdfPCell(new Paragraph("  إلى السيد(ة) "+ o.getAffectationTotale().getPersonnel().getSite().getQualite_direction_ar()+"  "+o.getAffectationTotale().getPersonnel().getSite().getNom_etablissement_ar()+" بولاية "+ o.getAffectationTotale().getPersonnel().getSite().getGouvernorat().getNomGouvAR()+"\n", f1));
         cell2.setBorder(Rectangle.NO_BORDER); 
         cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
         
         PdfPCell cell3 = new PdfPCell(new Paragraph("\n الموضوع  :  تكليف بمهمة \n ",f));
         cell3.setBorder(Rectangle.NO_BORDER); 
         cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell3.setPaddingLeft(-70);
         cell3.setPaddingRight(-20);
         
         PdfPCell cell4 = new PdfPCell(new Paragraph("و بعد \n يشرفني أن أعلمكم بأن مركز الإعلامية لوزارة الصحة كلف السيد(ة) " + o.getAffectationTotale().getPersonnel().getNom_AR()+" "+o.getAffectationTotale().getPersonnel().getPrenom_AR()+"   "+o.getAffectationTotale().getPersonnel().getGrade().getNom_grade_ar()+" لمتابعة التطبيقات الإعلامية بمؤسستكم وذلك إبتداء من تاريخ " + o.getAffectationTotale().getPersonnel().getDate_recrutement().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +"\n"+"وتفضلوا سيد(ت)ي، فائق عبارات الشكر والتقدير \n \n \n",f1));
         cell4.setBorder(Rectangle.NO_BORDER); 
         cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell4.setPaddingLeft(-70);
         cell4.setPaddingRight(-20);
         cell4.setLeading(1.5f, 1.5f);
         
         PdfPCell cell5 = new PdfPCell(new Paragraph(" المديــــــــــــر العــــــــام \n \n لطـفــي العـلانـــي  ",f));
         cell5.setBorder(Rectangle.NO_BORDER); 
         cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
         table2.addCell(cell0); 
         table2.addCell(cell01);

         table2.addCell(cell1);    
         table2.addCell(cell2);          
         table2.addCell(cell3);
         table2.addCell(cell4);
         table2.addCell(cell5);

         document.add(table2);

         
         
       
        
         
         
      /*   Image img = Image.getInstance("C:\\Users\\siwar\\Pictures\\stage\\Cims-angular-master\\v3\\spring-boot-spring-security-jwt-authentication-master\\cims-logo.png");
         img.scaleAbsoluteWidth(60);
         img.scaleAbsoluteHeight(30);
         img.setAlignment(Element.ALIGN_LEFT);*/
         PdfPCell leftCell = new PdfPCell();
         //leftCell.addElement(img);
         leftCell.setBorder(Rectangle.NO_BORDER);

         PdfPTable headerTable = new PdfPTable(2);
         headerTable.setWidthPercentage(100f);
         headerTable.addCell(leftCell);

         PdfPCell rightCell = new PdfPCell();

         rightCell .setBorder(Rectangle.NO_BORDER);
         rightCell .setPaddingLeft(-390);
         headerTable.addCell(rightCell);
         PdfPCell cell = new PdfPCell();
         cell.setBorder(Rectangle.NO_BORDER);
         cell.addElement(headerTable);
         
        document.add(headerTable);	
		
			
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(generatePdf.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
}


