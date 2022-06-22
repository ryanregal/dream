package lab12_3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map;

public class Test
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException
	{
		File input = new File("src/lab12_3/studentInformation.txt");
		if(!input.exists()){
			System.err.println("No Such File!");
			return;
		}
		
		List<student> students = new ArrayList<student>();
		Scanner scan = new Scanner(input);
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			String[] informations = line.split("[ ]+");
			students.add(new student(informations[0], informations[1], informations[2], Double.parseDouble(informations[3])));
		}
		
		
		Map<String, List<student>> groupByClass = 
				students.stream().collect(Collectors.groupingBy(student::getsClass));
		
		groupByClass.forEach(
				(Class, studentInClass) ->
				{
					System.out.println("�༶�ţ�" + Class);
					studentInClass.forEach(System.out::println);
					System.out.println("�ð���" + studentInClass.stream().count() + "��ѧ��");
					System.out.printf("�ð���ѧƽ���֣�%.2f\n", studentInClass.stream().mapToDouble(student::getsGrade).average().getAsDouble());
					System.out.printf("�ð���ѧ��߷֣�%.2f\n", studentInClass.stream().max(Comparator.comparing(student::getsGrade)).get().getsGrade());
					System.out.printf("�ð���ѧ��ͷ֣�%.2f\n\n", studentInClass.stream().min(Comparator.comparing(student::getsGrade)).get().getsGrade());
				}
				);
		
		
	}
}
