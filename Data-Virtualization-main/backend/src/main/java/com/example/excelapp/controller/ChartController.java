package com.example.excelapp.controller;
import com.example.excelapp.service.ChartService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/chart")
public class ChartController {
    private final ChartService service;
    public ChartController(ChartService service){this.service=service;}
    @GetMapping("/columns")
    public List<String> columns(@RequestParam(required=false) Long uploadId){ return service.getColumns(uploadId); }
    @GetMapping("/aggregate")
    public List<Map<String,Object>> agg(@RequestParam String column, @RequestParam(required=false) Long uploadId){ return service.getAggregate(column, uploadId); }
}
