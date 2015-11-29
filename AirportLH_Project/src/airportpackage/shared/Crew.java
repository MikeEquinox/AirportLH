package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс летные бригады
 * Crew class
 */ 

/**	
 *  CrewId - ID летного состава
 *  picId - первый пилот, командир
 *  cpId - второй пилот
 *  soId - третий пилот
 *  purser1Id - первый старший стюард(есса)
 *  purserId2 - второй стариший стюард(есса)
 *  flightAt1Id - стюард(есса)
 */
public class Crew implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int crewId;
	private int picId;
	private int cpId;
	private int soId;
	private int purser1Id;
	private int purser2Id;
	private int flightAt1Id;
	private int flightAt2Id;
	private int flightAt3Id;
	private int flightAt4Id;
	private int flightAt5Id;
	private int flightAt6Id;
	private int flightAt7Id;
	private int flightAt8Id;
	private int flightAt9Id;
	private int flightAt10Id;
	
//	Default constructor
	
//	Getters and setters
	public int getCrewId() {
		return crewId;
	}
	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getCpId() {
		return cpId;
	}
	public void setCpId(int cpId) {
		this.cpId = cpId;
	}
	public int getSoId() {
		return soId;
	}
	public void setSoId(int soId) {
		this.soId = soId;
	}
	public int getPurser1Id() {
		return purser1Id;
	}
	public void setPurser1Id(int purser1Id) {
		this.purser1Id = purser1Id;
	}
	public int getPurser2Id() {
		return purser2Id;
	}
	public void setPurser2Id(int purser2Id) {
		this.purser2Id = purser2Id;
	}
	public int getFlightAt1Id() {
		return flightAt1Id;
	}
	public void setFlightAt1Id(int flightAt1Id) {
		this.flightAt1Id = flightAt1Id;
	}
	public int getFlightAt2Id() {
		return flightAt2Id;
	}
	public void setFlightAt2Id(int flightAt2Id) {
		this.flightAt2Id = flightAt2Id;
	}
	public int getFlightAt3Id() {
		return flightAt3Id;
	}
	public void setFlightAt3Id(int flightAt3Id) {
		this.flightAt3Id = flightAt3Id;
	}
	public int getFlightAt4Id() {
		return flightAt4Id;
	}
	public void setFlightAt4Id(int flightAt4Id) {
		this.flightAt4Id = flightAt4Id;
	}
	public int getFlightAt5Id() {
		return flightAt5Id;
	}
	public void setFlightAt5Id(int flightAt5Id) {
		this.flightAt5Id = flightAt5Id;
	}
	public int getFlightAt6Id() {
		return flightAt6Id;
	}
	public void setFlightAt6Id(int flightAt6Id) {
		this.flightAt6Id = flightAt6Id;
	}
	public int getFlightAt7Id() {
		return flightAt7Id;
	}
	public void setFlightAt7Id(int flightAt7Id) {
		this.flightAt7Id = flightAt7Id;
	}
	public int getFlightAt8Id() {
		return flightAt8Id;
	}
	public void setFlightAt8Id(int flightAt8Id) {
		this.flightAt8Id = flightAt8Id;
	}
	public int getFlightAt9Id() {
		return flightAt9Id;
	}
	public void setFlightAt9Id(int flightAt9Id) {
		this.flightAt9Id = flightAt9Id;
	}
	public int getFlightAt10Id() {
		return flightAt10Id;
	}
	public void setFlightAt10Id(int flightAt10Id) {
		this.flightAt10Id = flightAt10Id;
	}
	
}
