package com.example.manage.controllers.api;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApiService {
    private final PsRepository psRepository;

    public Map<String, Object> getDict() {
        Instant currentDateTime = Instant.now().plus(9, ChronoUnit.HOURS); 
        Instant oneDayAgo = currentDateTime.minus(1, ChronoUnit.DAYS);
        List<Ps> loadList = this.psRepository.findByTimestampBetweenOrderByTimestampDesc(oneDayAgo, currentDateTime);
        Map<String, Object> loadDict = makeMap(loadList);
        return loadDict;
    }

    public Map<String, Object> makeMap(List<Ps> loadList) {
        int[] timestampList = new int[loadList.size()];
        float[] cpuList = new float[loadList.size()];
        float[] memList = new float[loadList.size()];
        Map<String, Object> loadDict = new HashMap<>();

        for (int i = 0; i < loadList.size(); i++) {
            timestampList[i] = i;
            cpuList[i] = loadList.get(i).cpu;
            memList[i] = loadList.get(i).mem;
        }

        loadDict.put("timestamp", timestampList);
        loadDict.put("cpu", cpuList);
        loadDict.put("mem", memList);
        return loadDict;
    }
}
