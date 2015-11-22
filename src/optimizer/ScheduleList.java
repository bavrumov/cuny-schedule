package optimizer;
import java.util.ArrayList;

public class ScheduleList {

	private ArrayList<CourseList> result;
	private static CourseList current = new CourseList("");
	
	public ScheduleList(ArrayList<CourseList> Lists) {
		result=new ArrayList<CourseList>();
		GeneratePermutations(Lists,result,0);
		for (CourseList c : result)
			c.setMixed();
	}
	
	private static void GeneratePermutations(ArrayList<CourseList> Lists, ArrayList<CourseList> result, int depth)
	{
	    if(depth == Lists.size())
	    {
	       CourseList temp = new CourseList(current);
	       result.add(temp);	       
	       return;
	     }
	    for(int i = 0; i < Lists.get(depth).size(); ++i)
	    {
		  current.addCourse(Lists.get(depth).getCourse(i));
	        GeneratePermutations(Lists, result, depth + 1);
	        current.remove(current.size()-1);
	    }
	}
	
	public int removeAbsurdities() {
		int removed=0;
		for (int i=result.size(); i>0; i--)
			if (result.get(i-1).containsConflictingCourses()) {
				result.remove(i-1);
				removed++;
			}
		return removed;
	}
	
	public ArrayList<CourseList> getPossibleSchedules() {
		return result;
	}
	
	public String toString() {
		String o="";
		for (int i=0; i<result.size(); i++)
			o+=("Schedule "+(i+1)+": {\n "+result.get(i)+"}\n\n");
		return o;
	}
}
