package first.step.convertcoin.mock;

import first.step.convertcoin.dto.CurrencyResponseDto;
import first.step.convertcoin.mapper.CurrencyMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class CurrencyMockData {
    private final Map<String, Double> currency = Map.of(
            "USD", 5.10,
            "EUR", 5.55,
            "GBP", 6.45,
            "JPY", 0.034,
            "ARS", 0.0060,
            "CAD", 3.75,
            "AUD", 3.40,
            "CHF", 5.70,
            "CNY", 0.70
    );


    public Map<String, Double> getCurrency() {
        return currency;
    }

    public Optional<CurrencyResponseDto> getPriceCurrency(String key) {
        key  = key.toUpperCase();
        if (currency.containsKey(key)) {
            Map<String, Double> newCurrency = Map.of(key, currency.get(key));
            return Optional.of(CurrencyMapper.currencyToDto(newCurrency));
        } else return Optional.empty();
    }

    public Optional<CurrencyResponseDto> convertCurrency(String key, Double value) {
        double total;
        key = key.toUpperCase();
        if (currency.containsKey(key)) {
           total = value * currency.get(key);
            return Optional.of(CurrencyMapper.currencyToDto(Map.of("BRL", total)));
        } else return Optional.empty();
    }
}
