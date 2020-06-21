package lv.venta.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//Contains main information for the person
@Entity
@Table(name="CVtable")
public class CV {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "C_ID")
	private int c_id;
	

	@Column(name = "Name")
	@Pattern(regexp = "^[a-zA-Z-āĀčČēĒgĢīĪķĶļĻņŅšŠžŽ]+$")
	@Size(min = 3, max = 60)
	private String name;
	
	@Column(name = "Surname")
	@Pattern(regexp = "^[a-zA-Z-āĀčČēĒgĢīĪķĶļĻņŅšŠžŽ]+$")
	@Size(min = 3, max = 60)
	private String surname;
	
	@Column(name = "Background_information")
	private String background_information;
	
	@Column(name = "Other_skills")
	private String other_skills;
	
	@Column(name = "Phone_number")
	@Pattern(regexp = "^[0-9]*$")
	@Size(min = 8, max = 8)
	private String phone_number;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "Province")
	private String province;
	
	@Column(name = "Zip_code")
	private String zip_code;
	
	@Column(name = "Email")
	private String email;
	
	
	@OneToMany(mappedBy = "cv")
	private Collection<JobExperience> AllJobExperiences;
	
	@OneToMany(mappedBy = "cv")
	private Collection<Education> AllEducations;
	
	@OneToMany(mappedBy = "cv")
	private Collection<Languages> AllLanguages;
	
	public CV() {
	}

	public CV(@Pattern(regexp = "^[a-zA-Z-āĀčČēĒgĢīĪķĶļĻņŅšŠžŽ]+$") @Size(min = 3, max = 60) String name,
			@Pattern(regexp = "^[a-zA-Z-āĀčČēĒgĢīĪķĶļĻņŅšŠžŽ]+$") @Size(min = 3, max = 60) String surname,
			String background_information, String other_skills,
			@Pattern(regexp = "^[0-9]*$") @Size(min = 8, max = 8) String phone_number, String address, String city,
			String province, String zip_code, String email, Collection<JobExperience> allJobExperiences,
			Collection<Education> allEducations, Collection<Languages> allLanguages) {
		super();
		this.name = name;
		this.surname = surname;
		this.background_information = background_information;
		this.other_skills = other_skills;
		this.phone_number = phone_number;
		this.address = address;
		this.city = city;
		this.province = province;
		this.zip_code = zip_code;
		this.email = email;
		this.AllJobExperiences = allJobExperiences;
		this.AllEducations = allEducations;
		this.AllLanguages = allLanguages;
	}

	

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public Collection<JobExperience> getAllJobExperiences() {
		return AllJobExperiences;
	}

	public void setAllJobExperiences(Collection<JobExperience> allJobExperiences) {
		this.AllJobExperiences = allJobExperiences;
	}

	public Collection<Education> getAllEducations() {
		return AllEducations;
	}

	public void setAllEducations(Collection<Education> allEducations) {
		this.AllEducations = allEducations;
	}

	public Collection<Languages> getAllLanguages() {
		return AllLanguages;
	}

	public void setAllLanguages(Collection<Languages> allLanguages) {
		this.AllLanguages = allLanguages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBackground_information() {
		return background_information;
	}

	public void setBackground_information(String background_information) {
		this.background_information = background_information;
	}

	public String getOther_skills() {
		return other_skills;
	}

	public void setOther_skills(String other_skills) {
		this.other_skills = other_skills;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CV [name=" + name + ", surname=" + surname + ", background_information=" + background_information
				+ ", other_skills=" + other_skills + ", phone_number=" + phone_number + ", address=" + address
				+ ", city=" + city + ", province=" + province + ", zip_code=" + zip_code + ", email=" + email
				+ ", allJobExperiences=" + AllJobExperiences + ", allEducations=" + AllEducations + ", allLanguages="
				+ AllLanguages + "]";
	}
}
