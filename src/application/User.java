package application;

import java.util.ArrayList;

//a singleton class. only need one user object

public class User {
	/**
	 * Setting up user
	 */
	private static User user = new User();
	private String password;

	private User() {
	}

	public static User getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * END Setting up user
	 */

	/**
	 * User Data
	 */
	private String fullname, title, schoolName, department, email, phoneNumber, semester;

	private ArrayList<String> coursesTaught = new ArrayList<>();
	private ArrayList<String> semestersTaught = new ArrayList<>();
	private ArrayList<String> programs = new ArrayList<>();
	private ArrayList<String> personalChar = new ArrayList<>();
	private ArrayList<String> academicChar = new ArrayList<>();

	private ArrayList<Recommendation> completedRecs = new ArrayList<>();

	/**
	 * 
	 * Accessors
	 * 
	 */
	public String getFullname() {
		return fullname;
	}

	public String getTitle() {
		return title;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public String getDepartment() {
		return department;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSemester() {
		return semester;
	}

	public ArrayList<String> getCoursesTaught() {
		return coursesTaught;
	}

	public ArrayList<String> getSemestersTaught() {
		return semestersTaught;
	}

	public ArrayList<String> getPrograms() {
		return programs;
	}

	public ArrayList<String> getPersonalChar() {
		return personalChar;
	}

	public ArrayList<String> getAcademicChar() {
		return academicChar;
	}

	public ArrayList<Recommendation> getCompletedRecs() {
		return completedRecs;
	}

	/**
	 * 
	 * Mutators
	 * 
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setCoursesTaught(ArrayList<String> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}

	public void setSemestersTaught(ArrayList<String> semestersTaught) {
		this.semestersTaught = semestersTaught;
	}

	public void setPrograms(ArrayList<String> programs) {
		this.programs = programs;
	}

	public void setPersonalChar(ArrayList<String> personalChar) {
		this.personalChar = personalChar;
	}

	public void setAcademicChar(ArrayList<String> academicChar) {
		this.academicChar = academicChar;
	}

	public void setStudents(ArrayList<Recommendation> completedRecs) {
		this.completedRecs = completedRecs;
	}

	/**
	 * END User Data
	 */
}
