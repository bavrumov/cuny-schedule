package optimizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		boolean help;
		try {help=args[0].equals("-help");} //if no args, help is false
		catch (ArrayIndexOutOfBoundsException e) {help=false;}
		if (help) //prints instructions if user asked for help
			printHelp();
		int courseNums=0;
		Scanner ui=new Scanner(System.in);
		Scanner fileScan=new Scanner("init");
		String fileName="";
		File courseFile;
		Schedule mySched=new Schedule();
		while (courseNums<6) {
			System.out.println("Drag course file #"+(courseNums+1)+" here now:");
			fileName=ui.nextLine();
			if (fileName.equalsIgnoreCase("done")) {
				fileScan.close();
				break;
			}
			fileName=fileName.replace("\\", "").trim(); //Removes backslash and trailing space.
			courseFile=new File(fileName);
			try {
				fileScan=new Scanner(courseFile);
				String courseName=mySched.addCourseList(fileName);
				System.out.println("Class \""+courseName+"\" has been added to your schedule.");
			} catch (FileNotFoundException e) {
				System.out.println("That file doesn't seem to exist. Try Again.");
				courseNums--;
			}
			System.out.println();
			courseNums++;
		}
		ui.close();
		System.out.println("Program has accepted "+courseNums+" files succesfully.\n");
		//System.out.println(mySched);
		ScheduleList allSched=mySched.generateAllScheduleCombinations();
		//System.out.println(allSched);
		int k=allSched.removeAbsurdities();
		System.out.println(k+" schedules were absurd. \n"+allSched);
		//System.out.println(mySched.getCourseList(0));
		//System.out.println("This courseList contains conflicting courses. "+mySched.getCourseList(0).containsConflictingCourses());
		//for (int i=0; i<7; i++)
		//	System.out.println(mySched.getCourseList(0).getCourse(2).getDOTW()[i]);
	}

	private static void printHelp() {
		System.out.println("1. Drag a course file into this window and hit enter.\n");
		System.out.println("2. Repeat step 1 until you have entered all your courses.\n");
		System.out.println("3. Type 'done' without quotes (and press enter) so that the program can process your files.\n");
		System.out.println(" >It is HIGHLY recommended to name your files based on the class sections in it (e.g. Physics.txt, English.txt).");
		System.out.println(" >The program will accept 6 files max, if you've entered 6 then processing will start automatically.\n");
	}
}