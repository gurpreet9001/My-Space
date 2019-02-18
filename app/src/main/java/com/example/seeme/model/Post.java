package com.example.seeme.model;

import com.example.seeme.utils.FormatterUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post {

    private String title;
    private String description;
    private long createdDate;
    private String imagePath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public Map<String,Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("title", title);
        result.put("description", description);
        result.put("createdDate", createdDate);
        result.put("imagePath", imagePath);
        result.put("createdDateText", FormatterUtil.getFirebaseDateFormat().format(new Date(createdDate)));

        return result;
    }
}