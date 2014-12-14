package com.epam.jmp.model;

public class Message {

	private Long mId;
	private String mContent;

	public Message(Long id, String content) {
		mId = id;
		mContent = content;
	}

	public Long getId() {
		return mId;
	}

	public void setId(Long id) {
		mId = id;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		mContent = content;
	}

}
