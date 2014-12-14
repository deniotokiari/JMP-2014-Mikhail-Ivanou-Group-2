package com.epam.jmp.model;

public class Book {
	
    private Long mId;
    private String mTitle;
    private String mUrl;

    public Book(Long id, String title, String url) {
        mId = id;
        mTitle = title;
        mUrl = url;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

    
}
