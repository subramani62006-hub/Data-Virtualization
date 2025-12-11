package com.example.excelapp.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ExcelUpload {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String filename;
    private LocalDateTime uploadedAt;

    public ExcelUpload(){}
    public Long getId(){return id;}
    public String getUsername(){return username;}
    public void setUsername(String u){this.username=u;}
    public String getFilename(){return filename;}
    public void setFilename(String f){this.filename=f;}
    public LocalDateTime getUploadedAt(){return uploadedAt;}
    public void setUploadedAt(LocalDateTime t){this.uploadedAt=t;}
}
