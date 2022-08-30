package toy.project.exchange.batch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import toy.project.exchange.Service.CurrencyExchangeService;
import toy.project.exchange.batch.Dto.ResponseCurrencyExchangeDto;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class CurrencyExchangeOpenApi {

    private final String url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
    private final String authkey = "05MlE4EoXGgKkHvnAzXGDVx8nTxGJgiH";

    private CurrencyExchangeService currencyExchangeService;
    public CurrencyExchangeOpenApi(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    // 월-금오전 9시부터 17시까지 5분 간격으로
    @Scheduled(cron = "0 0/5 9-17 * * MON-FRI", zone = "Asia/Seoul")
//    @Deprecated
    public void getCurrencyExchangeData() {
        RestTemplate restTemplate = new RestTemplate();
        String searchBaseDate = getSearchDate();

        URI targetUrl = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("authkey", authkey)
                .queryParam("searchdate", searchBaseDate)
                .queryParam("data", "AP01")
                .build()
                .toUri();

        String responseCurrencyExchangeStr = restTemplate.getForObject(targetUrl, String.class);

        List<ResponseCurrencyExchangeDto> responseCurrencyExchangeDtos = new Gson().fromJson(
                responseCurrencyExchangeStr
                ,new TypeToken<List<ResponseCurrencyExchangeDto>>(){}
                .getType());

        responseCurrencyExchangeDtos.stream()
                .forEach(System.out::println);

        currencyExchangeService.insertBatchResponseData(responseCurrencyExchangeDtos);
    }

    // TODO
    // LocalDateTime 이용해서 가져올건데 주말은 제외
    private String getSearchDate() {
        LocalDateTime now = LocalDateTime.now();
        String week = String.valueOf(now.getDayOfWeek());
        if ("SATURDAY".equals(week)) now = now.minusDays(1);
        if ("SUNDAY".equals(week)) now = now.minusDays(2);
        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
