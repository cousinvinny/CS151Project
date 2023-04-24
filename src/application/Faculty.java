package application;

import java.util.ArrayList;

public class Faculty {
    private String firstname, lastname, title, schoolName, department, email, phoneNumber, semester, programName;
    private ArrayList<String> coursesTaught;

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
    public String getTitle()
    {
        return title;
    }
    public String getSchoolName()
    {
        return schoolName;
    }
    public String getDepartment()
    {
        return department;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getSemester()
    {
        return semester;
    }
    public String getProgramName()
    {
        return programName;
    }
    public ArrayList<String> getCoursesTaught()
    {
        return coursesTaught;
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
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setSemester(String semester)
    {
        this.semester = semester;
    }
    public void setProgramName(String programName)
    {
        this.programName = programName;
    }
    public void setCoursesTaught(ArrayList<String> coursesTaught)
    {
        this.coursesTaught = coursesTaught;
    }
}
