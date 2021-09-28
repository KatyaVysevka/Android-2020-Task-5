package com.example.task5.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatPhoto(
    @SerializedName("breeds") var breeds : List<Breeds>,
    @SerializedName("height") var height : Int,
    @SerializedName("id") var id : String,
    @SerializedName("url") var url : String,
    @SerializedName("width") var width : Int
): Parcelable {
    @Parcelize
    data class Weight(
        @SerializedName("imperial") var imperial: String,
        @SerializedName("metric") var metric: String
    ): Parcelable

    @Parcelize
    data class Breeds(
        @SerializedName("adaptability") var adaptability: Int,
        @SerializedName("affection_level") var affectionLevel: Int,
        @SerializedName("alt_names") var altNames: String,
        @SerializedName("cfa_url") var cfaUrl: String,
        @SerializedName("child_friendly") var childFriendly: Int,
        @SerializedName("country_code") var countryCode: String,
        @SerializedName("country_codes") var countryCodes: String,
        @SerializedName("description") var description: String,
        @SerializedName("dog_friendly") var dogFriendly: Int,
        @SerializedName("energy_level") var energyLevel: Int,
        @SerializedName("experimental") var experimental: Int,
        @SerializedName("grooming") var grooming: Int,
        @SerializedName("hairless") var hairless: Int,
        @SerializedName("health_issues") var healthIssues: Int,
        @SerializedName("hypoallergenic") var hypoallergenic: Int,
        @SerializedName("id") var id: String,
        @SerializedName("indoor") var indoor: Int,
        @SerializedName("intelligence") var intelligence: Int,
        @SerializedName("lap") var lap: Int,
        @SerializedName("life_span") var lifeSpan: String,
        @SerializedName("name") var name: String,
        @SerializedName("natural") var natural: Int,
        @SerializedName("origin") var origin: String,
        @SerializedName("rare") var rare: Int,
        @SerializedName("reference_image_id") var referenceImageId: String,
        @SerializedName("rex") var rex: Int,
        @SerializedName("shedding_level") var sheddingLevel: Int,
        @SerializedName("short_legs") var shortLegs: Int,
        @SerializedName("social_needs") var socialNeeds: Int,
        @SerializedName("stranger_friendly") var strangerFriendly: Int,
        @SerializedName("suppressed_tail") var suppressedTail: Int,
        @SerializedName("temperament") var temperament: String,
        @SerializedName("vcahospitals_url") var vcahospitalsUrl: String,
        @SerializedName("vetstreet_url") var vetstreetUrl: String,
        @SerializedName("vocalisation") var vocalisation: Int,
        @SerializedName("weight") var weight: Weight,
        @SerializedName("wikipedia_url") var wikipediaUrl: String

    ): Parcelable
}
