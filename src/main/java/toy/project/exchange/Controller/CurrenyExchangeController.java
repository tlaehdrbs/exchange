package toy.project.exchange.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.project.exchange.Service.CurrencyExchangeService;
import toy.project.exchange.batch.CurrencyExchangeOpenApi;
import toy.project.exchange.domain.Exchange;

import java.util.Map;

@RestController
public class CurrenyExchangeController {

    private CurrencyExchangeOpenApi currencyExchangeOpenApi;

    private CurrenyExchangeController(CurrencyExchangeOpenApi currencyExchangeOpenApi) {
        this.currencyExchangeOpenApi = currencyExchangeOpenApi;
    }

    @GetMapping("/getRecentCurrencyExchangeInfo")
    public String getRecentCurrencyExchangeInfo() {
        currencyExchangeOpenApi.getCurrencyExchangeData();
        return "getData Success!";
    }

    @PostMapping("/insCurrencyExchangeData")
    public String insCurrencyExchangeData(@RequestBody Map<String, Object> paramMap) {
        System.out.println("___ paramMap! => " + paramMap);
        return null;
    }
}
