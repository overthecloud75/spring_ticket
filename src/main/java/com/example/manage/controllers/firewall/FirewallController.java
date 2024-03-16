package com.example.manage.controllers.firewall;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import com.example.manage.utils.GetAPI;
import com.example.manage.utils.Paging;

@RequiredArgsConstructor
@Controller
public class FirewallController {

    @Value("${fpms.url}")  // set config 
    private String fpmsUrl;

    GetAPI getApi = new GetAPI();
    ObjectMapper objectMapper = new ObjectMapper();

    private String nonce = UUID.randomUUID().toString();
    private List<String> headerList = new ArrayList<>(List.of("ip", "Class", "Protocol", "Port", "BLOCK", "Message"));
    private List<String> accessorList = new ArrayList<>(List.of("ip", "ip_class", "protocol", "port", "block", "messsage"));

    @GetMapping("/firewall/")
    public String list(HttpServletRequest request, Model model, @RequestParam(value="page", defaultValue="0") int page) throws IOException, InterruptedException {
        Map<String, Object> jsonObject = getApi.retrieveJsonDataFromApi(fpmsUrl + "?page=" + Integer.toString(page)); 

        String jsonData = objectMapper.writeValueAsString(jsonObject.get("data"));      // object to String json 
        String jsonPaging = objectMapper.writeValueAsString(jsonObject.get("paging"));  // object to String json 
        List<Object> firewallPolicies= objectMapper.readValue(jsonData, new TypeReference<List<Object>>() {});   
        Map<String, Object> dictPaging = objectMapper.readValue(jsonPaging, new TypeReference<Map<String, Object>>() {});
        Paging paging = makePaging(dictPaging);

        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("firewallPolicies", firewallPolicies);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/firewall_list";
    }    

    public Paging makePaging(Map<String, Object> dictPaging) {
        Paging paging = new Paging();
        paging.setNumber(Integer.parseInt(dictPaging.get("page").toString()));
        paging.setSize(20);
        paging.setCount(Integer.parseInt(dictPaging.get("count").toString()));
        paging.setTotalPages(Integer.parseInt(dictPaging.get("total_pages").toString()));
        if (Integer.parseInt(dictPaging.get("page").toString()) <= 0) {
            paging.setHasPrevious(false);
        } else {
            paging.setHasPrevious(true);
        }
        if (Integer.parseInt(dictPaging.get("page").toString()) >= Integer.parseInt(dictPaging.get("total_pages").toString()) - 1)
            paging.setHasNext(false);
        else {
            paging.setHasNext(true);
        }
        return paging;
    }
}
