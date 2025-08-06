package mylab.student.control;
import mylab.student.exception.InvalidGradeException;

public class Student {
	private String studentId;
	private String name;
	private String major;
	private int grade;
	
	public Student() {
		
	}
	
	public Student(String studentId, String name, String major, int grade) {
		setStudentId(studentId);
		setName(name);
		setMajor(major);
		setGrade(grade);
	}
	
	public int getGrade() { 
		return grade;
	}
	
	public String getStudenId() {
		return studentId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void withdraw(int grade) throws InvalidGradeException{
		if(4 > this.grade) {
			String errMessage = String.format("학년은 1~4 사이여야 합니다.");
			throw new InvalidGradeException(errMessage);
		}
		
	}

}
