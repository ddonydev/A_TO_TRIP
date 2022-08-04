package trip.dk.event;


public class EventVo {


	public EventVo() {

	}

	public EventVo(String no, String startDate, String endDate, String title) {
		super();
		this.no = no;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}



	private String no;
	private String startDate;
	private String endDate;
	private String title;



	public String getTitle(String no) {
		String title =  null;
		if(no.equals("1")) {
			title = " 가위 바위 보!";
		}
		if(no.equals("2")) {
			title = " UP & DOWN ";
		}
		return title;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
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

	@Override
	public String toString() {
		return "EventVo [no=" + no + ", startDate=" + startDate + ", endDate=" + endDate + ", title=" + title + "]";
	}






}
