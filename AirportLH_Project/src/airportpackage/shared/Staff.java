package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс сотрудники
 * Staff class
 */ 

/**	
 *  staffId - ID сотрудника
 *  firstName - имя сотрудника
 *  lastName - фамилия сотрудника
 *  gender - обращение
 *  country - страна приписки
 *  city - город приписки
 *  position - должность
 *  crewIdRef - принадлежность к летной бригаде
 */
public class Staff implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int staffId;
	private String firstName;
	private String lastName;
	private String gender;
	private String country;
	private String city;
	private String position;
	private int crewIdRef;
	
//	Default constructor
	
	//	Getters and setters
	public int getCrewIdRef() {
		return crewIdRef;
	}
	public void setCrewIdRef(int crewIdRef) {
		this.crewIdRef = crewIdRef;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
