package com.example.manage.controllers.api;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoField;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.example.manage.controllers.access.Access;
import com.example.manage.controllers.access.AccessRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApiService {
    private final PsRepository psRepository;
    private final AccessRepository accessRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm").withZone(ZoneId.of("Z"));

    public Map<String, Object> getLoadDict() {
        Instant currentDateTime = Instant.now().plus(9, ChronoUnit.HOURS); 
        Instant oneDayAgo = currentDateTime.minus(1, ChronoUnit.DAYS);
        List<Ps> loadList = this.psRepository.findByTimestampBetweenOrderByTimestampAsc(oneDayAgo, currentDateTime);
        Map<String, Object> loadDict = makeLoadMap(loadList);
        return loadDict;
    }

    public Map<String, Object> getBarDict() {
        Instant currentDateTime = Instant.now().plus(9, ChronoUnit.HOURS); 
        Instant oneDayAgo = currentDateTime.minus(1, ChronoUnit.DAYS);
        List<Access> loadList = this.accessRepository.findByTimestampBetweenOrderByTimestampAsc(oneDayAgo, currentDateTime);
        Map<String, Object> loadDict = makeBarMap(loadList, oneDayAgo, 10);
        return loadDict;
    }

    public Map<String, Object> makeLoadMap(List<Ps> loadList) {
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

    public Map<String, Object> makeBarMap(List<Access> loadList, Instant oneDayAgo, int period) {
        Map<String, Object> loadDict = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(oneDayAgo, ZoneId.of("Z"));
        LocalDate date = LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        int minute =  localDateTime.getMinute() / period;
        LocalTime time = LocalTime.of(localDateTime.getHour(), minute * period);

        int timestampSize = 24 * 60 / period + 1;
        String[] timestampList = new String[timestampSize];
        int[] countList = new int[timestampSize];

        int breakPoint = 0;

        for (int i = 0; i < timestampSize; i++) {
            LocalDateTime normalizedLocalDateTime = LocalDateTime.of(date, time).plusMinutes(period * (i+1));
            String strTimestamp = formatter.format(normalizedLocalDateTime);
            timestampList[i] = strTimestamp; 
            for (int j = breakPoint; j < loadList.size(); j ++ ) {
                if (formatter.format(loadList.get(j).timestamp).compareTo(strTimestamp) <= 0) {
                    countList[i] = countList[i] + 1;
                } else {
                    breakPoint = j;
                    break;
                } 
            }
        }
        loadDict.put("timestamp", timestampList);
        loadDict.put("count", countList);
        return loadDict;
    }
}
