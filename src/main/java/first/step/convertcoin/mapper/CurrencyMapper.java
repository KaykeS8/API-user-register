package first.step.convertcoin.mapper;

import first.step.convertcoin.dto.CurrencyResponseDto;
import java.util.Map;

public class CurrencyMapper {
    public static CurrencyResponseDto currencyToDto(Map<String, Double> currency) {
        return new CurrencyResponseDto(
                currency
        );
    }
}
