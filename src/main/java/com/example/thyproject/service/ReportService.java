package com.example.thyproject.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private List<String> reportEntries = new ArrayList<>();

    public void addToReport(String entry) {
        reportEntries.add(entry);
    }

    public List<String> getReportEntries() {
        return reportEntries;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void generateReport() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        if (reportEntries.isEmpty()) {
            System.out.println("Rapor oluşturuldu " + formattedDateTime + " : \n" + "No Process");
        } else {

            StringBuilder reportContent = new StringBuilder();
            for (String entry : reportEntries) {
                reportContent.append(entry).append("\n");
            }

            System.out.println("Rapor oluşturuldu " + formattedDateTime + " : \n" + reportContent);
        }

        // Yeni bir liste oluşturarak eski verileri temizle
        reportEntries = new ArrayList<>();

    }

}
