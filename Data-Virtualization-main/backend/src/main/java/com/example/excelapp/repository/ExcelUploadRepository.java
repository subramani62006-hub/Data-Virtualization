package com.example.excelapp.repository;
import com.example.excelapp.model.ExcelUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ExcelUploadRepository extends JpaRepository<ExcelUpload,Long>{
    List<ExcelUpload> findByUsername(String username);
}
