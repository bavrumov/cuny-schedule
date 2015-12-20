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
		Schedule mySched=new Schedule();
		int courseNums=readFiles(mySched);
		if (courseNums==0) {
			System.out.println("You have not submitted any files.  Goodbye.");
			return;
		}
		System.out.println("Program has accepted this many files succesfully: "+courseNums+"\n");
		ScheduleList allSched=mySched.generateAllScheduleCombinations();
		System.out.println("Out of the "+allSched.size()+" generated schedules, "+allSched.removeAbsurdities()+" were absurd.");
		System.out.println(" Here are the remaining "+allSched.size()+" schedules to choose from, no classes overlap:");
		for (MixedCourseList mx: allSched.getPossibleSchedules())
			mx.sort();
		System.out.println(allSched);
		
	}

	private static int readFiles(Schedule ms) {
		int courseNums=0;
		Scanner ui=new Scanner(System.in);
		Scanner fileScan=new Scanner("dummytext"); //initial value incase user is done before dropping any files.
		String fileName="";
		File courseFile;
		while (courseNums<6) {	//You can submit 6 files max
			System.out.println("Drag course file #"+(courseNums+1)+" here now:");
			fileName=ui.nextLine();
			if (fileName.equalsIgnoreCase("done")) { //User typed "done", beak out of while loop.
				fileScan.close();
				break;
			}
			fileName=fileName.replace("\\", "").trim(); //Removes backslash and trailing space.
			courseFile=new File(fileName);
			try {	//Checks if file exists, catches nonexistent. If file exists, makes sure size is valid.
				fileScan=new Scanner(courseFile);
				int count = 0;
				while (fileScan.hasNextLine()) {
					count++;
					fileScan.nextLine();
				}
				if (count<=90 && count%9==0) { //10 classes at most, 9-line class formatting from cunyfirst
					String courseName=ms.addCourseList(fileName);
					System.out.println("Class \""+courseName+"\" has been added to your schedule.");
					courseNums++;
				}
				else
					System.out.println("That file is invalid.  Either it contains more than 10 classes, or you pasted in the classes incorrectly.");
			} catch (FileNotFoundException e) {
				System.out.println("That file doesn't seem to exist. Try Again.");
			}
			System.out.println();
		}
		ui.close();
		return courseNums;
	}
	
	private static void printHelp() {
		System.out.println("1. Drag a course file into this window and hit enter.\n");
		System.out.println("2. Repeat step 1 until you have entered all your courses.\n");
		System.out.println("3. Type 'done' without quotes (and press enter) so that the program can process your files.\n");
		System.out.println(" >It is HIGHLY recommended to name your files based on the class sections in it (e.g. Physics.txt, English.txt).");
		System.out.println(" >The program will accept 6 files max, if you've entered 6 then processing will start automatically.\n");
		System.out.println("Classes pasted from CUNYfirst have the following format: (anything in quotes is the exact word found in all pasted courses)");
		System.out.println(	"CLASS_NUMBER\nSECTION\n\"Regular\"\nDAY(S)&TIME\nLOCATION\nPROFESSOR\nTERM_DATES\n\" Open\"\n\"select\"\n");
	}
}