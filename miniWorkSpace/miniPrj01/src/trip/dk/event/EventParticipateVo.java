package trip.dk.event;

public class EventParticipateVo {



	public EventParticipateVo() {

	}


	public EventParticipateVo(String participateNo, String memberNo, String eventNo, String eventYn) {

		this.participateNo = participateNo;
		this.memberNo = memberNo;
		this.eventNo = eventNo;
		this.eventYn = eventYn;
	}

	private String participateNo;
	private String memberNo;
	private String eventNo;
	private String eventYn;


	public String getParticipateNo() {
		return participateNo;
	}
	public void setParticipateNo(String participateNo) {
		this.participateNo = participateNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getEventNo() {
		return eventNo;
	}
	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventYn() {
		return eventYn;
	}
	public void setEventYn(String eventYn) {
		this.eventYn = eventYn;
	}

	@Override
	public String toString() {
		return "EventParticipateVo [participateNo=" + participateNo + ", memberNo=" + memberNo + ", eventNo=" + eventNo
				+ ", eventYn=" + eventYn + "]";
	}



}
