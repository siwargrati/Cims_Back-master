/*package Cims.PFE.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Cims.PFE.Entities.Personnel;

@Service
public class ExportPersonnelService {
	
	public static ByteArrayInputStream personnelsPDFReport(List<Personnel> personnels) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try
		{ PdfWriter.getInstance(document, out);
		  document.open();
		  com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER,14, BaseColor.BLACK);
		  Paragraph para = new Paragraph("Personnels", font);
		  para.setAlignment(Element.ALIGN_CENTER);
		  document.add(para);
		  document.add(Chunk.NEWLINE);
		  
		  PdfPTable table = new PdfPTable(2);
		  
		  Stream.of("Title", "Description").forEach(headerTitle -> {
			  PdfPCell header = new PdfPCell();
			  com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  header.setHorizontalAlignment(Element.ALIGN_CENTER);
			  header.setBorderWidth(1);
			  header.setPhrase(new Phrase(headerTitle, headFont));
			  table.addCell(header);
			  });
		  for (Personnel pers: personnels) {
			  PdfPCell titleCell = new PdfPCell(new Phrase(pers.getAdresse()));
			  titleCell.setPaddingLeft(1);
			  titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			  titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  table.addCell(titleCell);
			  
			  PdfPCell descCell = new PdfPCell(new Phrase(pers.getNom()));
			  descCell.setPaddingLeft(1);
			  descCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			  descCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  table.addCell(descCell);
			  
		  }
		  document.add(table);
		  document.close();
		}catch (DocumentException e) {
			e.printStackTrace();  
		  
		}
        return new ByteArrayInputStream(out.toByteArray());
		
	}

}
*/