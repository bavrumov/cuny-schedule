package optimizer;

public class Course implements Comparable<Course>{

	private int classNumber; //UID
	private String section;
	private String days; //contains a subset of {MoTuWeThFrSaSu}
	private boolean[] dotw=new boolean[7]; //days of the week
	private String room;
	private String professor;
	private String humanStart;  //Times used for display, 9:30AM, 10:00PM
	private String humanEnd;
	private double startTime; //Time 9:30=9.5. 12:45=12.75. For mathematical operations.
	private double endTime;  //Time 4:55 PM = 16.91666.
	private String name;
	
	public Course(int cNum, String sec, String d, String start, String end, String loc, String prof ) {
		classNumber=cNum;
		section=sec;
		days=d;
		humanStart=start;
		humanEnd=end;
		startTime=parseTime(start);
		endTime=parseTime(end);
		room=loc;
		professor=prof;
		parseDays();
	}

	public boolean conflictsWith(Course c) { //true if courses overlap, false if courses touch and/or don't overlap
		boolean timeConflict=(c.startTime>=startTime && c.startTime<endTime) || (c.endTime>startTime && c.endTime<=endTime);
		boolean dateConflict=false;
		for (int i=0; i<7; i++)
			if (dotw[i]==true && dotw[i]==c.dotw[i]) {
				dateConflict=true;
				break;
			}
		return timeConflict && dateConflict;
	}
	
	public String toString() {
		return (" "+classNumber+" "+section+" "+days+" From "+humanStart+" to "+humanEnd+"\n   With "+professor+" at "+room);
	}
	
	private void parseDays() {	//each true in the array means class is held on that index's day of the week. [0]=Sunday, [6]=Saturday.
		String[] d={"Su","Mo","Tu","We","Th","Fr","Sa"};
		for (int i=0; i<7; i++)
			if (days.indexOf(d[i])!=-1)
				dotw[i]=true;
	}
	
	private static double parseTime(String t) {
		String hour=t.split(":")[0];
		String minute=t.split(":")[1].substring(0, 2);
		boolean isAm=t.substring(t.length() - 2).equals("AM"); 	//true if string ends in AM
		boolean is12=hour.equals("12");
		if (isAm && is12)					// 12:30AM becomes 0.5
			return Double.parseDouble(minute)/60;
		if (!(isAm || is12))		//if its neither AM or 12, ie. 2:15PM, return 14.25
			return 12+Double.parseDouble(hour)+Double.parseDouble(minute)/60;
		return Double.parseDouble(hour)+Double.parseDouble(minute)/60; //else 10:20AM becomes 10.3333...
	}
	
	public int compareTo(Course o) {		//if this is earlier than other, returns -1. if this is later than other, returns 1.
		for (int i=0; i<7; i++) {
			if (dotw[i] && !o.getDOTW()[i])	//This Day1 comes earlier
				return -1;
			if (!dotw[i] && o.getDOTW()[i])	//Other Day1 comes earlier
				return 1;
			if (dotw[i] && o.getDOTW()[i])	//Day1 on the same day
				break;
		}
		if (startTime<o.getStartTime()) //Starts before Other
			return -1;
		if (startTime>o.getStartTime()) //Starts after Other
			return 1;
		return 0;	//Only happens if courses start at the same time on the same day (conflicting courses)
	}
	
	public double duration() {
		return endTime-startTime;
	}
	
	public int getClassNumber() {
		return classNumber;
	}

	public String getSection() {
		return section;
	}

	public String getDays() {
		return days;
	}

	public String getRoom() {
		return room;
	}

	public String getProfessor() {
		return professor;
	}

	public String getHumanStart() {
		return humanStart;
	}
	
	public String getHumanEnd() {
		return humanEnd;
	}
	
	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}
	
	public boolean[] getDOTW() {
		return dotw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name=n;
	}
}