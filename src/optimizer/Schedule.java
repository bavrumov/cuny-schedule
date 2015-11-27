package optimizer;
import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<String> classNames;
	private ArrayList<CourseList> sched;
	
	public Schedule() {
		sched=new ArrayList<CourseList>();
		classNames=new ArrayList<String>();
	}
	
	public String addCourseList(String fname) {
		classNames.add((fname.split("/")[fname.split("/").length-1]).replace(".txt",""));
		CourseList myCourse=new CourseList(fname);
		sched.add(myCourse);
		myCourse.setAllNames(classNames.get(classNames.size()-1));
		return classNames.get(classNames.size()-1);
	}
	
	public void addCourseList (CourseList cl) {
		sched.add(cl);
	}
	
	public CourseList getCourseList(int idx) {
		if (idx<0 || idx>=sched.size())
			return null;
		return sched.get(idx);
	}
	
	public CourseList getCourseList(String name) {
		int idx=-1;
		for (int i=0; i<classNames.size(); i++)
			if (classNames.get(i).equals(name))
				idx=i;
		return getCourseList(idx);
	}
	
	public String toString() {
		String o="";
		if (sched.size()<=4096) //4^6 or 8^4
			for (int i=0; i<sched.size(); i++)
				o+=classNames.get(i)+":\n"+sched.get(i).toString()+"\n";
		else
			o="There are over 4096 schedules, your computer will take too long time to display them.  So it won't.";
		return o;
	}
	
	public ScheduleList generateAllScheduleCombinations() {
		ScheduleList s=new ScheduleList(sched);
		return s;
	}
}