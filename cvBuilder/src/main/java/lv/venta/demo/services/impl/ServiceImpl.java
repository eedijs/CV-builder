package lv.venta.demo.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import lv.venta.demo.models.CV;
import lv.venta.demo.models.Education;
import lv.venta.demo.models.JobExperience;
import lv.venta.demo.models.Languages;
import lv.venta.demo.repo.ICVRepo;
import lv.venta.demo.repo.IEducationRepo;
import lv.venta.demo.repo.IJobExperienceRepo;
import lv.venta.demo.repo.ILanguagesRepo;
import lv.venta.demo.services.IService;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ServiceImpl implements IService{

	
	@Autowired
	ICVRepo cvRepo;
	
	@Autowired
	IEducationRepo educationRepo;
	
	@Autowired
	IJobExperienceRepo jobExperienceRepo;
	
	@Autowired
	ILanguagesRepo languagesRepo;

	
	@Override
	public ArrayList<JobExperience> selectAllJobExperiences() {
		return (ArrayList<JobExperience>) jobExperienceRepo.findAll();
	}
	 
	@Override
	public ArrayList<Education> selectAllEducations() {
		return (ArrayList<Education>) educationRepo.findAll();
	}
	
	@Override
	public ArrayList<Languages> selectAllLanguages() {
		return (ArrayList<Languages>) languagesRepo.findAll();
	}

	@Override
	public ArrayList<CV> selectAllCVs() {
		return (ArrayList<CV>) cvRepo.findAll();
 	}

	@Override
	public void insertCV(CV cv){
		cvRepo.save(cv);
	}
	
	@Override 
	public void insertEducation(Education edu) {
		educationRepo.save(edu);
	}

	@Override
	public void insertJobExperience(JobExperience jExp) {
		jobExperienceRepo.save(jExp);
		
	}

	@Override
	public void insertLanguage(Languages lang) {
		languagesRepo.save(lang);
		
	}
	
	//Method to create a pdf file
	@Override
	public void createPDF() throws IOException {
		
		ArrayList<CV> cv = (ArrayList<CV>) cvRepo.findAll();
		ArrayList<Education> allEducations = (ArrayList<Education>) educationRepo.findAll();
		ArrayList<JobExperience> allJobExperiences = (ArrayList<JobExperience>) jobExperienceRepo.findAll();
		ArrayList<Languages> allLanguages = (ArrayList<Languages>) languagesRepo.findAll();
		
		
		if(!Files.isDirectory(Paths.get("C:\\PDF"))) {
			System.out.println("didn't find");
			new File("C:\\PDF").mkdirs();
		}
		
		  Document document = new Document();
		  try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\PDF\\MyCV.pdf"));
			document.open();
			
			//NAME block
			Font nameFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Paragraph nameAndSurname = new Paragraph(cv.get(0).getName() + " " + cv.get(0).getSurname(), nameFont);
			nameAndSurname.setAlignment(Element.ALIGN_CENTER);
			document.add(nameAndSurname);
			document.add(Chunk.NEWLINE);
			
			//PhoneNR block
			Font phoneNrFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Paragraph phoneNumber = new Paragraph("Phone number: " + cv.get(0).getPhone_number(), phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(phoneNumber);
			
			//Email block
			Paragraph email = new Paragraph("Email: " + cv.get(0).getEmail(), phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(email);
			
			//Address block
			if(cv.get(0).getAddress() != null) {
				Paragraph address = new Paragraph("Address: " + cv.get(0).getAddress(), phoneNrFont);
				nameAndSurname.setAlignment(Element.ALIGN_LEFT);
				document.add(address);
			}
			
			//City block
			if(cv.get(0).getCity() != null) {
				Paragraph city = new Paragraph("City: " + cv.get(0).getCity(), phoneNrFont);
				nameAndSurname.setAlignment(Element.ALIGN_LEFT);
				document.add(city);
			}
			
			//Province block
			if(cv.get(0).getProvince() != null) {
			Paragraph province = new Paragraph("Province: " + cv.get(0).getProvince(), phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(province);
			}
			
			//Zip Code block
			if(cv.get(0).getZip_code() != null) {
			Paragraph zipCode = new Paragraph("ZipCode: " + cv.get(0).getZip_code(), phoneNrFont);
			nameAndSurname.setAlignment(Element.ALIGN_LEFT);
			document.add(zipCode);
			}
			
				
			
			
			float[] colWidth = {2f};
			Font bgFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			Font bgTextFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			
			//Background Info block
			if(cv.get(0).getBackground_information().length() > 0) {
				PdfPTable tableBg = new PdfPTable(1);
				tableBg.setWidthPercentage(105);
				tableBg.setSpacingBefore(25f);
				tableBg.setSpacingAfter(5f);
				
				
				tableBg.setWidths(colWidth);
		
				Paragraph backgroundInfo = new Paragraph("Background information", bgFont);
				PdfPCell c1 = new PdfPCell(backgroundInfo);
				tableBg.addCell(c1);
				document.add(tableBg);
				
				Paragraph backgroundInfoText = new Paragraph(cv.get(0).getBackground_information(),
						bgTextFont);
				document.add(backgroundInfoText);
			}
			
			//Other skills block
			if(cv.get(0).getOther_skills().length() > 0) {
				PdfPTable tableOs = new PdfPTable(1);
				tableOs.setWidthPercentage(105);
				tableOs.setSpacingBefore(25f);
				tableOs.setSpacingAfter(5f);
			
				tableOs.setWidths(colWidth);
				Paragraph otherSkills = new Paragraph("Other skills", bgFont);
				PdfPCell c2 = new PdfPCell(otherSkills);
				tableOs.addCell(c2);
				document.add(tableOs);
				Paragraph osInfoText = new Paragraph(cv.get(0).getOther_skills(),
						bgTextFont);
				document.add(osInfoText);
			}
			
			//Education block
			for(int i = 0; i < educationRepo.count(); i++) {
				PdfPTable tableEdu = new PdfPTable(1);
				tableEdu.setWidthPercentage(105);
				tableEdu.setSpacingBefore(25f);
				tableEdu.setSpacingAfter(5f);
			
				tableEdu.setWidths(colWidth);
				Paragraph education = new Paragraph("Education", bgFont);
				PdfPCell c3 = new PdfPCell(education);
				tableEdu.addCell(c3);
				document.add(tableEdu);
				Paragraph educationText = new Paragraph(allEducations.get(i).toString(),
						bgTextFont);
				document.add(educationText);
			}
			
			//Job Experience block
			if(!allJobExperiences.isEmpty()) {
				for(int i = 0; i < jobExperienceRepo.count(); i++) {
					PdfPTable tableJobExp = new PdfPTable(1);
					tableJobExp.setWidthPercentage(105);
					tableJobExp.setSpacingBefore(25f);
					tableJobExp.setSpacingAfter(5f);
				
					tableJobExp.setWidths(colWidth);
					Paragraph jobExp = new Paragraph("Job Experience", bgFont);
					PdfPCell c4 = new PdfPCell(jobExp);
					tableJobExp.addCell(c4);
					document.add(tableJobExp);
					Paragraph jobExpText = new Paragraph(allJobExperiences.get(i).toString(),
							bgTextFont);
					document.add(jobExpText);
				}
			}
			
			//Language block
			for(int i = 0; i < languagesRepo.count(); i++) {
				PdfPTable tableLang = new PdfPTable(1);
				tableLang.setWidthPercentage(105);
				tableLang.setSpacingBefore(25f);
				tableLang.setSpacingAfter(5f);
			
				tableLang.setWidths(colWidth);
				Paragraph lang = new Paragraph("Languages", bgFont);
				PdfPCell c5 = new PdfPCell(lang);
				tableLang.addCell(c5);
				document.add(tableLang);
				Paragraph languagesText = new Paragraph(allLanguages.get(i).toString(),
						bgTextFont);
				document.add(languagesText);
			}
			
			/*
			--Set of commands to create an ordered list (for future use)--
			List orderList = new List(List.ORDERED);
			orderList.add(new ListItem("Fun"));
			orderList.add(new ListItem("That"));
			orderList.add(new ListItem("Ends"));
			document.add(orderList);
			
			--Set of commands to create a unordered list (for future use)--
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("That"));
			unorderList.add(new ListItem("is"));
			unorderList.add(new ListItem("nice"));
			document.add(unorderList);
			
		 	--Set of commands to create a canvas with color (for future use)--
			PdfContentByte canvas = writer.getDirectContent();
			canvas.rectangle(22, 774, 550, 20);
			canvas.setColorFill(BaseColor.LIGHT_GRAY);
		    canvas.fill();
			*/
			
			document.close();
			writer.close();
		
		} catch(DocumentException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clearEducation() {
		educationRepo.deleteAll();
	}

	@Override
	public void clearJobs() {
		jobExperienceRepo.deleteAll();
	}

	@Override
	public void clearLanguages() {
		languagesRepo.deleteAll();
	}

	@Override
	public void clearCV() {
		cvRepo.deleteAll();
	}

	
	
	
}
