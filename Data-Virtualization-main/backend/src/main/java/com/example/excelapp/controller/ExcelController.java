package com.example.excelapp.controller;
import com.example.excelapp.service.ExcelService;
import com.example.excelapp.repository.ExcelUploadRepository;
import com.example.excelapp.model.ExcelUpload;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService service;
    private final ExcelUploadRepository uploadRepo;

    public ExcelController(ExcelService service, ExcelUploadRepository uploadRepo){
        this.service = service;
        this.uploadRepo = uploadRepo;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> upload(@RequestPart("file") MultipartFile file, @RequestParam String username){
        try {
            Long uploadId = service.importExcel(file, username);
            return Map.of("status","ok","uploadId", uploadId);
        } catch (Exception e) {
            return Map.of("status","error","msg", e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<ExcelUpload> list(@RequestParam String username){
        return uploadRepo.findByUsername(username);
    }
}
