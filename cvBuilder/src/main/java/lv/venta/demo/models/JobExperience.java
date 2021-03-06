package lv.venta.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

//Job experience for person
@Entity
@Table(name="JobExperienceTable")
public class JobExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "J_ID")
	private int j_id;
	
	@Column(name = "Job_title")
	private String job_title;
	
	@Column(name = "Job_info")
	private String job_info;

	@Column(name = "Company_name")
	private String company_name;
	
	@Column(name = "Location")
	private String location;
	
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "StartDate")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EndDate")
	private Date endDate;

	public JobExperience() {
	}
	
	public JobExperience(String job_title, String job_info, String company_name, String location, Date startDate,
			Date endDate) {
		super();
		this.job_title = job_title;
		this.job_info = job_info;
		this.company_name = company_name;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getJ_id() {
		return j_id;
	}

	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_info() {
		return job_info;
	}

	public void setJob_info(String job_info) {
		this.job_info = job_info;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Job title: " + job_title + "\n" + "Job info: " + job_info + "\n" + "Company name: " + company_name + 
				"\n" + "Location: " + location + "\n" + "Start date: " + startDate + "\n" + "End date: " + endDate;
	}
}
