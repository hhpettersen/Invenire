package no.app.invenire.ui.components

import androidx.annotation.DrawableRes
import no.app.invenire.R
import no.app.invenire.domain.AdType

sealed class AdFilter {
    object Favorite : AdFilter()
    data class Type(val type: AdType) : AdFilter()
}

data class AdFilterModel(
    val name: String,
    @DrawableRes
    val illustration: Int,
    val filter: AdFilter,
) {
    companion object {
        fun getFiltersByAdType(): List<AdFilterModel> =
            AdType.values().filterNot { it == AdType.UNKNOWN }.map { adType ->
                AdFilterModel(
                    name = adType.title(),
                    illustration = adType.icon(),
                    filter = AdFilter.Type(adType),
                )
            }
    }
}

@DrawableRes
private fun AdType.icon(): Int = when (this) {
    AdType.BAP -> R.drawable.outline_shopping_bag_24
    AdType.REALESTATE -> R.drawable.outline_house_24
    AdType.B2B -> R.drawable.outline_business_24
    AdType.UNKNOWN -> R.drawable.baseline_question_mark_24
}

private fun AdType.title(): String = when (this) {
    AdType.BAP -> "Torget"
    AdType.REALESTATE -> "Bolig"
    AdType.B2B -> "B2B"
    AdType.UNKNOWN -> "Ukjent"
}
