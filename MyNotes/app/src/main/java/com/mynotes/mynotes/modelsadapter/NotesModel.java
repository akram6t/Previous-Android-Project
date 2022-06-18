package com.mynotes.mynotes.modelsadapter;

public class NotesModel {
    private String title , notes , image , id;

    public NotesModel(String title, String notes, String image) {
        this.title = title;
        this.notes = notes;
        this.image = image;
    }

    public NotesModel(String title, String notes) {
        this.title = title;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
