package mylab.student.entity;
import mylab.student.control.Student;

public class StudentTest {
	public static void main(String[] args) {
		Student student = new Student();
		
		student.setStudentId("123456789");
		student.setName("��ö��");
		student.setMajor("��ǻ�Ͱ���");
		student.setGrade(3);
		
		System.out.println(student.getName() + "/" + student.getMajor() + '/' + student.getGrade() + "�г�");
		
		try{
			System.out.println("5�г����� ����");
			student.withdraw(5);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
