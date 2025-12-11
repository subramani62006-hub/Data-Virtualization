package com.example.excelapp.service;
import org.springframework.web.multipart.MultipartFile;
public interface ExcelService {
    Long importExcel(MultipartFile file, String username) throws Exception;
}
