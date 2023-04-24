package application;

import java.util.ArrayList;
public class Recommendation {
    private String firstname, lastname, gender, targetSchool, targetProgram, currentDate
            , firstSemesterTaken, firstYearTaken;
    private ArrayList<String> coursesTaken;
    private ArrayList<String> personalCharacteristics;
    private ArrayList<String> academicCharacteristics;
    
    
    
    /**
     * 
     * Constructors
     * 
     * */
    Recommendation(){}
    
    

    /**
     * 
     * Accessors
     * 
     * */
    public String getFirstname()
    {
        return firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public String getGender()
    {
        return gender;
    }
    public String getTargetSchool()
    {
        return targetSchool;
    }
    public String getTargetProgram()
    {
        return targetProgram;
    }
    public String getCurrentDate()
    {
        return currentDate;
    }
    public String getFirstSemesterTaken()
    {
        return firstSemesterTaken;
    }
    public String getFirstYearTaken()
    {
        return firstYearTaken;
    }
    public ArrayList<String> getCoursesTaken()
    {
        return coursesTaken;
    }
    public ArrayList<String> getPersonalCharacteristics()
    {
        return personalCharacteristics;
    }
    public ArrayList<String> getAcademicCharacteristics()
    {
        return academicCharacteristics;
    }

    
    
    /**
     * 
     * Mutators
     * 
     * */
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public void setTargetSchool(String targetSchool)
    {
        this.targetSchool = targetSchool;
    }
    public void setTargetProgram(String targetProgram)
    {
        this.targetProgram = targetProgram;
    }
    public void setCurrentDate(String currentDate)
    {
        this.currentDate = currentDate;
    }
    public void setFirstSemesterTaken(String firstSemesterTaken)
    {
        this.firstSemesterTaken = firstSemesterTaken;
    }
    public void setFirstYearTaken(String firstYearTaken)
    {
        this.firstYearTaken = firstYearTaken;
    }
    public void setCoursesTaken(ArrayList<String> coursesTaken)
    {
        this.coursesTaken = coursesTaken;
    }
    public void setPersonalCharacteristics(ArrayList<String> personalCharacteristics)
    {
        this.personalCharacteristics = personalCharacteristics;
    }
    public void setAcademicCharacteristics(ArrayList<String> academicCharacteristics)
    {
        this.academicCharacteristics = academicCharacteristics;
    }

}
