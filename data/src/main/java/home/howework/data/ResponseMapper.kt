package home.howework.data

import home.howework.data.model.CurrencyInfoResponse
import home.howework.domain.entity.CurrencyInfoDto
import home.howework.domain.entity.FromCurrencyDto
import home.howework.domain.entity.LastUpdateDto
import home.howework.domain.entity.PayLoadDto
import home.howework.domain.entity.RatesDto
import home.howework.domain.entity.ToCurrencyDto

fun CurrencyInfoResponse.mapToCurrencyInfoResponse(): CurrencyInfoDto {
    return with(this) {
        CurrencyInfoDto(
            trackingId = trackingId,
            resultCode = resultCode,
            PayLoadDto(LastUpdateDto(payload.lastUpdate?.milliseconds),
                payload.rates.map { it ->
                    RatesDto(
                        it.category, FromCurrencyDto(
                            it.fromCurrency?.code,
                            it.fromCurrency?.name, it.fromCurrency?.strCode
                        ), ToCurrencyDto(
                            it.toCurrency?.code, it.toCurrency?.name,
                            it.toCurrency?.strCode
                        ), it.buy, it.sell
                    )
                })
        )

    }
}