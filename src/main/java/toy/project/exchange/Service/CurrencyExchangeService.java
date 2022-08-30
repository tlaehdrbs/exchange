package toy.project.exchange.Service;

import org.springframework.stereotype.Service;
import toy.project.exchange.Repository.CurrencyExchangeRepository;
import toy.project.exchange.batch.Dto.ResponseCurrencyExchangeDto;
import toy.project.exchange.domain.Exchange;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CurrencyExchangeService {

    private CurrencyExchangeRepository currencyExchangeRepository;
    public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public void insertBatchResponseData(List<ResponseCurrencyExchangeDto>responseCurrencyExchangeDtos) {
        responseCurrencyExchangeDtos.stream()
                .forEach(n -> {
                    Exchange exchange = new Exchange();
                    exchange.setBasDate(getSearchDate());
                    exchange.setCurUnit(n.getCur_unit());
                    exchange.setDealBasR(n.getDeal_bas_r());
                    exchange.setTtb(n.getTtb());
                    currencyExchangeRepository.save(exchange);
                });
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
