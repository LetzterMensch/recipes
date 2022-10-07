package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.ContentType;
//import org.springframework.data.relational.core.mapping.Table;
import org.springframework.http.MediaType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PHOTOS")
public class Photo {

    @Id
    private Integer id;
    public String contentType;

    @NotEmpty
    private String fileName;
    @JsonIgnore
    private byte[] data;

    public Photo() {

    }

    public Photo(Integer ID, String fileName) {
        this.id = ID;
        this.fileName = fileName;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
