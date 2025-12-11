package com.example.excelapp.service.impl;
import com.example.excelapp.service.ChartService;
import com.example.excelapp.repository.DataRowRepository;
import com.example.excelapp.model.DataRow;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ChartServiceImpl implements ChartService {

    private final DataRowRepository repo;

    public ChartServiceImpl(DataRowRepository repo){this.repo=repo;}

    @Override
    public List<String> getColumns(Long uploadId){
        List<DataRow> all = (uploadId==null) ? repo.findAll() : repo.findByUploadId(uploadId);
        if(all.isEmpty()) return List.of();
        JSONObject o = new JSONObject(all.get(0).getJsonData());
        return new ArrayList<>(o.keySet());
    }

    @Override
    public List<Map<String,Object>> getAggregate(String column, Long uploadId){
        Map<Object,Long> m = new LinkedHashMap<>();
        List<DataRow> rows = (uploadId==null) ? repo.findAll() : repo.findByUploadId(uploadId);
        for(DataRow dr: rows){
            JSONObject o = new JSONObject(dr.getJsonData());
            Object v = o.has(column) ? o.get(column) : null;
            m.put(v, m.getOrDefault(v,0L)+1);
        }
        List<Map<String,Object>> out = new ArrayList<>();
        m.forEach((k,v)-> out.add(Map.of("key", k, "value", v)));
        return out;
    }
}
