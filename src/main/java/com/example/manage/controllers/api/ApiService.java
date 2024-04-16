package com.example.manage.controllers.api;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApiService {
    private final PsRepository psRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.of("Z"));

    public Map<String, Object> getDict() {
        Instant currentDateTime = Instant.now().plus(9, ChronoUnit.HOURS); 
        Instant oneDayAgo = currentDateTime.minus(1, ChronoUnit.DAYS);
        List<Ps> loadList = this.psRepository.findByTimestampBetweenOrderByTimestampAsc(oneDayAgo, currentDateTime);
        Map<String, Object> loadDict = makeMap(loadList);
        return loadDict;
    }

    public Map<String, Object> makeMap(List<Ps> loadList) {
        String[] timestampList = new String[loadList.size()];
        float[] cpuList = new float[loadList.size()];
        float[] memList = new float[loadList.size()];
        Map<String, Object> loadDict = new HashMap<>();

        for (int i = 0; i < loadList.size(); i++) {
            timestampList[i] = formatter.format(loadList.get(i).timestamp);
            cpuList[i] = loadList.get(i).cpu;
            memList[i] = loadList.get(i).mem;
        }

        loadDict.put("timestamp", timestampList);
        loadDict.put("cpu", cpuList);
        loadDict.put("mem", memList);
        return loadDict;
    }
}
