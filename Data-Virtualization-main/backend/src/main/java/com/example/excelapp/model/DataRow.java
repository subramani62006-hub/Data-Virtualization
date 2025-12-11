package com.example.excelapp.model;
import jakarta.persistence.*;

@Entity
public class DataRow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String jsonData;

    private Long uploadId; // link to ExcelUpload

    public DataRow() {}
    public DataRow(String jsonData, Long uploadId){this.jsonData=jsonData; this.uploadId=uploadId;}

    public Long getId(){return id;}
    public String getJsonData(){return jsonData;}
    public void setJsonData(String jsonData){this.jsonData = jsonData;}
    public Long getUploadId(){return uploadId;}
    public void setUploadId(Long uploadId){this.uploadId = uploadId;}
}
