package trip.dk.event;


public class EventVo {
	
	
	public EventVo() {
		
	}
	
	public EventVo(int no, String startDate, String endDate, String title) {
		super();
		this.no = no;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}



	private int no;
	private String startDate;
	private String endDate;
	private String title;
	
	
	
	public String getTitle(int no) {
		String title =  "";
		if(no== 1) {
			title = " 가위 바위 보!";
		}
		if(no==2) {
			title = " UP & DOWN ";
		}
		return title;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

//	@Override
	public String toString() {
		return "EventVo [no=" + no + ", startDate=" + startDate + ", endDate=" + endDate + ", title=" + title + "]";
	}

	
	
	
	

}
