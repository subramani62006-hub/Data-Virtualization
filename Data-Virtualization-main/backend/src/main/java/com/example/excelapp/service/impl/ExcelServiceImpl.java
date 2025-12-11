package com.example.excelapp.service.impl;
import com.example.excelapp.service.ExcelService;
import com.example.excelapp.model.DataRow;
import com.example.excelapp.model.ExcelUpload;
import com.example.excelapp.repository.DataRowRepository;
import com.example.excelapp.repository.ExcelUploadRepository;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class ExcelServiceImpl implements ExcelService {

    private final DataRowRepository dataRepo;
    private final ExcelUploadRepository uploadRepo;

    public ExcelServiceImpl(DataRowRepository dataRepo, ExcelUploadRepository uploadRepo){
        this.dataRepo = dataRepo;
        this.uploadRepo = uploadRepo;
    }

    @Override
    public Long importExcel(MultipartFile file, String username) throws Exception {
        // create upload metadata
        ExcelUpload up = new ExcelUpload();
        up.setUsername(username);
        up.setFilename(file.getOriginalFilename());
        up.setUploadedAt(LocalDateTime.now());
        ExcelUpload saved = uploadRepo.save(up);
        Long uploadId = saved.getId();

        try (InputStream in = file.getInputStream()) {
            Workbook wb = WorkbookFactory.create(in);
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            DataFormatter formatter = new DataFormatter();

            List<String> headers = new ArrayList<>();
            if (rows.hasNext()) {
                Row hr = rows.next();
                hr.forEach(c -> headers.add(formatter.formatCellValue(c)));
            }

            while (rows.hasNext()) {
                Row r = rows.next();
                Map<String, Object> map = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell c = r.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = formatter.formatCellValue(c);
                    map.put(headers.get(i), value);
                }
                dataRepo.save(new DataRow(new JSONObject(map).toString(), uploadId));
            }
        }

        return uploadId;
    }
}
