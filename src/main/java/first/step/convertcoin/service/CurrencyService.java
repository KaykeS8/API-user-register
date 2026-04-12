package first.step.convertcoin.service;

import first.step.convertcoin.dto.CurrencyResponseDto;
import first.step.convertcoin.exception.CurrencyException;
import first.step.convertcoin.mock.CurrencyMockData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    private final CurrencyMockData currencyMockData;

    public CurrencyService(CurrencyMockData currencyMockData) {
        this.currencyMockData = currencyMockData;
    }

    public List<Map<String, Double>> getAllCurrency() {
        return List.of(currencyMockData.getCurrency());
    }

    public CurrencyResponseDto getCurrencyPrice(String key) {
        return currencyMockData.getPriceCurrency(key)
                .orElseThrow(() -> new CurrencyException("Currency not found by key: " + key));
    }

    public CurrencyResponseDto convertCurrency(String key, Double value) {
        return currencyMockData.convertCurrency(key,value)
                .orElseThrow(() -> new CurrencyException("Invalid data to convert currency"));
    }

}