package com.example.excelapp.repository;
import com.example.excelapp.model.DataRow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DataRowRepository extends JpaRepository<DataRow,Long>{
    List<DataRow> findByUploadId(Long uploadId);
}
