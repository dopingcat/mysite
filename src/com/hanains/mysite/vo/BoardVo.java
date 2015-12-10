package com.hanains.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String memberName;
	private Long viewCnt;
	private String regDate;
	
	public BoardVo() {
		super();
	}

	public BoardVo(Long no, String title, String content, String memberName, Long viewCnt, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.memberName = memberName;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Long viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVo [no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", memberNo=");
		builder.append(memberName);
		builder.append(", viewCnt=");
		builder.append(viewCnt);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
