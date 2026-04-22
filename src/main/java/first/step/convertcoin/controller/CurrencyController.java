package first.step.convertcoin.controller;

import first.step.convertcoin.dto.CurrencyResponseDto;
import first.step.convertcoin.mock.CurrencyMockData;
import first.step.convertcoin.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<CurrencyResponseDto> getAll() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/price")
    public CurrencyResponseDto getCurrencyPrice(@RequestParam("key") String key) {
        return currencyService.getCurrencyPrice(key);
    }

    @GetMapping("/convert")
    public CurrencyResponseDto getConvertPrice(@RequestParam("key") String key, @RequestParam("value") Double value) {
        return currencyService.convertCurrency(key, value);
    }
}
