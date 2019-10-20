package com.mungziapp.traveltogether;

class NoticeItem {
    private String title;
    private String contents;

    NoticeItem(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    String getTitle() { return title; }
    String getContents() { return contents; }
}
