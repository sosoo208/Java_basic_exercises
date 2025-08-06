package mylab.student.entity;
import mylab.student.control.Student;

public class StudentTest {
	public static void main(String[] args) {
		Student student = new Student();
		
		student.setStudentId("c393005");
		student.setName("김철수");
		student.setMajor("컴퓨터공학과");
		student.setGrade(3);
		
		System.out.println(student.getName() + "/" + student.getMajor() + '/' + student.getGrade() + "학년");
		
		try{
			System.out.println("5학년으로 변경");
			student.withdraw(5);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
