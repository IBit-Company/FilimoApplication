package ir.mahdi.filimoapplication.feature_search.data.dto

import ir.mahdi.filimoapplication.feature_search.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListDto(
    @SerialName("data")
    val info: List<Data>
){
    fun toMovieList(): List<Movie> {
        val list = ArrayList<Movie>()
        info.forEach {item ->
            list.add(Movie(
                movieTitle = item.movie_title,
                description = item.descr,
                cover = item.pic.movie_img_m,
                rate = item.imdb_rate,
                duration = if (item.duration != null) item.duration.text else "نامشخص"
            ))
        }

        return list
    }
}

@Serializable
data class Data(
    val HD: Boolean,
    val age_range: String,
    val audio: Audio,
    val avg_rate_label: String?,
    val badge: Badge,
    val categories: List<Category>,
    val commingsoon_txt: String,
    val countries: List<Country>,
    val cover: String?,
    val descr: String,
    val director: String?,
    val dubbed: Dubbed,
    val duration: Duration?,
    val freemium: Boolean,
    val id: String,
    val imdb_rate: String,
    val language_info: LanguageInfo,
    val last_watch: List<String?>,
    val link_key: String,
    val link_type: String,
    val movie_id: String,
    val movie_title: String,
    val movie_title_en: String,
    val output_type: String,
    val pic: Pic,
    val position: Int,
    val pro_year: String,
    val publish_date: String,
    val rate_avrage: String?,
    val rate_enable: Boolean,
    val rate_enable_by_cnt: Boolean,
    val rel_data: RelData,
    val serial: Serial,
    val sid: Int,
    val subtitle: Subtitle,
    val tag_id: String?,
    val theme: String,
    val uid: String,
    val uuid: String,
    val watch_list_action: String,
    val watermark: Boolean
)

@Serializable
data class Audio(
    val isMultiLanguage: Boolean,
    val languages: List<String>
)

@Serializable
data class Badge(
    val backstage: Boolean,
    val commingsoon: Boolean,
    val exclusive: Boolean,
    val free: Boolean,
    val hear: Boolean,
    val info: List<Info>,
    val online_release: Boolean,
    val vision: Boolean
)

@Serializable
data class Info(
    val background_color: String,
    val icon: String,
    val text: String,
    val text_color: String,
    val type: String,
    val ui_type: String
)

@Serializable
data class Category(
    val link_key: String,
    val link_type: String,
    val title: String,
    val title_en: String
)

@Serializable
data class Country(
    val country: String,
    val country_en: String
)

@Serializable
data class Dubbed(
    val enable: Boolean,
    val text: String
)

@Serializable
data class Duration(
    val text: String,
    val value: Int
)

@Serializable
data class LanguageInfo(
    val flag: String,
    val text: String
)

@Serializable
data class Pic(
    val movie_img_b: String,
    val movie_img_m: String,
    val movie_img_s: String
)

@Serializable
data class RelData(
    val rel_id: String?,
    val rel_title: String?,
    val rel_type: String?,
    val rel_type_txt: String,
    val rel_uid: String?
)

@Serializable
data class Serial(
    val enable: Boolean,
    val last_part: String,
    val parent_title: String,
    val part_text: String,
    val season_id: Int,
    val season_text: String,
    val serial_part: String
)

@Serializable
data class Subtitle(
    val enable: Boolean,
    val text: String
)