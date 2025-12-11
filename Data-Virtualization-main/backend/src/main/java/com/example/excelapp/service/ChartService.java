package com.example.excelapp.service;
import java.util.*;
public interface ChartService {
    List<String> getColumns(Long uploadId);
    List<Map<String,Object>> getAggregate(String column, Long uploadId);
}
