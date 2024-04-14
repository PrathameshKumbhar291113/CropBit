package com.cropbit.network_module.network_models.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherForecastApiResponse(
    @SerializedName("current")
    var current: Current?,
    @SerializedName("location")
    var location: Location?
) : Parcelable {
    @Parcelize
    data class Current(
        @SerializedName("air_quality")
        var airQuality: AirQuality?,
        @SerializedName("cloud")
        var cloud: Int?, // 0
        @SerializedName("condition")
        var condition: Condition?,
        @SerializedName("feelslike_c")
        var feelslikeC: Double?, // 37.6
        @SerializedName("feelslike_f")
        var feelslikeF: Double?, // 99.7
        @SerializedName("gust_kph")
        var gustKph: Double?, // 29.8
        @SerializedName("gust_mph")
        var gustMph: Double?, // 18.5
        @SerializedName("humidity")
        var humidity: Int?, // 59
        @SerializedName("is_day")
        var isDay: Int?, // 1
        @SerializedName("last_updated")
        var lastUpdated: String?, // 2024-04-14 14:15
        @SerializedName("last_updated_epoch")
        var lastUpdatedEpoch: Int?, // 1713084300
        @SerializedName("precip_in")
        var precipIn: Double?, // 0.0
        @SerializedName("precip_mm")
        var precipMm: Double?, // 0.0
        @SerializedName("pressure_in")
        var pressureIn: Double?, // 29.8
        @SerializedName("pressure_mb")
        var pressureMb: Double?, // 1009.0
        @SerializedName("temp_c")
        var tempC: Double?, // 32.0
        @SerializedName("temp_f")
        var tempF: Double?, // 89.6
        @SerializedName("uv")
        var uv: Double?, // 7.0
        @SerializedName("vis_km")
        var visKm: Double?, // 5.0
        @SerializedName("vis_miles")
        var visMiles: Double?, // 3.0
        @SerializedName("wind_degree")
        var windDegree: Int?, // 260
        @SerializedName("wind_dir")
        var windDir: String?, // W
        @SerializedName("wind_kph")
        var windKph: Double?, // 13.0
        @SerializedName("wind_mph")
        var windMph: Double? // 8.1
    ) : Parcelable {
        @Parcelize
        data class AirQuality(
            @SerializedName("co")
            var co: Double?, // 400.5
            @SerializedName("gb-defra-index")
            var gbDefraIndex: Int?, // 10
            @SerializedName("no2")
            var no2: Double?, // 10.0
            @SerializedName("o3")
            var o3: Double?, // 186.0
            @SerializedName("pm10")
            var pm10: Double?, // 113.6
            @SerializedName("pm2_5")
            var pm25: Double?, // 71.7
            @SerializedName("so2")
            var so2: Double?, // 22.9
            @SerializedName("us-epa-index")
            var usEpaIndex: Int? // 4
        ) : Parcelable

        @Parcelize
        data class Condition(
            @SerializedName("code")
            var code: Int?, // 1009
            @SerializedName("icon")
            var icon: String?, // //cdn.weatherapi.com/weather/64x64/day/122.png
            @SerializedName("text")
            var text: String? // Overcast
        ) : Parcelable
    }

    @Parcelize
    data class Location(
        @SerializedName("country")
        var country: String?, // India
        @SerializedName("lat")
        var lat: Double?, // 18.98
        @SerializedName("localtime")
        var localtime: String?, // 2024-04-14 14:23
        @SerializedName("localtime_epoch")
        var localtimeEpoch: Int?, // 1713084831
        @SerializedName("lon")
        var lon: Double?, // 72.83
        @SerializedName("name")
        var name: String?, // Mumbai
        @SerializedName("region")
        var region: String?, // Maharashtra
        @SerializedName("tz_id")
        var tzId: String? // Asia/Kolkata
    ) : Parcelable
}