package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Long> sum = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();

        for (Publication p : publications) {
            String type = getPublicationType(p);
            sum.put(type, sum.getOrDefault(type, 0L) + p.getPrice());
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avg = new HashMap<>();
        for (String type : sum.keySet()) {
            avg.put(type, sum.get(type) / (double) cnt.get(type));
        }
        return avg;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> cnt = new HashMap<>();
        for (Publication p : publications) {
            String type = getPublicationType(p);
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }
        int total = publications.length;
        Map<String, Double> ratio = new HashMap<>();
        for (String type : cnt.keySet()) {
            ratio.put(type, (cnt.get(type) * 100.0) / total);
        }
        return ratio;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int total = publications.length;
        int match = 0;
        for (Publication p : publications) {
            String y = p.getPublishDate().substring(0, 4);
            if (y.equals(year)) match++;
        }
        return (match * 100.0) / total;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat money = new DecimalFormat("#,###");
        DecimalFormat percent = new DecimalFormat("#,##0.##");

        System.out.println("===== 출판물 통계 분석 =====");

        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
        for (String type : avg.keySet()) {
            System.out.println("   - " + type + ": " + money.format(Math.round(avg.get(type))) + "원");
        }
        System.out.println();

        System.out.println("2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (String type : dist.keySet()) {
            System.out.println("   - " + type + ": " + percent.format(dist.get(type)) + "%");
        }
        System.out.println();

        double r2007 = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("3. 2007년에 출판된 출판물 비율: " + percent.format(r2007) + "%");
    }
}
