package db.poorya.namavatest.domain.model.remote


import com.google.gson.annotations.SerializedName

data class VideoConfigResponse(
    @SerializedName("cdn_url")
    val cdnUrl: String? = null,
    @SerializedName("embed")
    val embed: Embed? = null,
    @SerializedName("player_url")
    val playerUrl: String? = null,
    @SerializedName("request")
    val request: Request? = null,
    @SerializedName("seo")
    val seo: Seo? = null,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("video")
    val video: Video? = null,
    @SerializedName("view")
    val view: Int? = null,
    @SerializedName("vimeo_api_url")
    val vimeoApiUrl: String? = null,
    @SerializedName("vimeo_url")
    val vimeoUrl: String? = null
) {
    data class Embed(
        @SerializedName("api")
        val api: Any? = null,
        @SerializedName("app_id")
        val appId: String? = null,
        @SerializedName("autopause")
        val autopause: Int? = null,
        @SerializedName("autoplay")
        val autoplay: Int? = null,
        @SerializedName("color")
        val color: String? = null,
        @SerializedName("context")
        val context: String? = null,
        @SerializedName("dnt")
        val dnt: Int? = null,
        @SerializedName("editor")
        val editor: Boolean? = null,
        @SerializedName("keyboard")
        val keyboard: Int? = null,
        @SerializedName("log_plays")
        val logPlays: Int? = null,
        @SerializedName("loop")
        val loop: Int? = null,
        @SerializedName("muted")
        val muted: Int? = null,
        @SerializedName("on_site")
        val onSite: Int? = null,
        @SerializedName("outro")
        val outro: String? = null,
        @SerializedName("player_id")
        val playerId: String? = null,
        @SerializedName("playsinline")
        val playsinline: Int? = null,
        @SerializedName("quality")
        val quality: Any? = null,
        @SerializedName("settings")
        val settings: Settings? = null,
        @SerializedName("texttrack")
        val texttrack: String? = null,
        @SerializedName("time")
        val time: Int? = null,
        @SerializedName("transparent")
        val transparent: Int? = null
    ) {
        data class Settings(
            @SerializedName("badge")
            val badge: Int? = null,
            @SerializedName("byline")
            val byline: Int? = null,
            @SerializedName("collections")
            val collections: Int? = null,
            @SerializedName("color")
            val color: Int? = null,
            @SerializedName("embed")
            val embed: Int? = null,
            @SerializedName("fullscreen")
            val fullscreen: Int? = null,
            @SerializedName("like")
            val like: Int? = null,
            @SerializedName("logo")
            val logo: Int? = null,
            @SerializedName("playbar")
            val playbar: Int? = null,
            @SerializedName("portrait")
            val portrait: Int? = null,
            @SerializedName("scaling")
            val scaling: Int? = null,
            @SerializedName("share")
            val share: Int? = null,
            @SerializedName("spatial_compass")
            val spatialCompass: Int? = null,
            @SerializedName("spatial_label")
            val spatialLabel: Int? = null,
            @SerializedName("speed")
            val speed: Int? = null,
            @SerializedName("title")
            val title: Int? = null,
            @SerializedName("volume")
            val volume: Int? = null,
            @SerializedName("watch_later")
            val watchLater: Int? = null
        )
    }

    data class Request(
        @SerializedName("ab_tests")
        val abTests: AbTests? = null,
        @SerializedName("build")
        val build: Build? = null,
        @SerializedName("client")
        val client: Client? = null,
        @SerializedName("cookie")
        val cookie: Cookie? = null,
        @SerializedName("cookie_domain")
        val cookieDomain: String? = null,
        @SerializedName("country")
        val country: String? = null,
        @SerializedName("currency")
        val currency: String? = null,
        @SerializedName("expires")
        val expires: Int? = null,
        @SerializedName("file_codecs")
        val fileCodecs: FileCodecs? = null,
        @SerializedName("files")
        val files: Files? = null,
        @SerializedName("flags")
        val flags: Flags? = null,
        @SerializedName("gc_debug")
        val gcDebug: GcDebug? = null,
        @SerializedName("lang")
        val lang: String? = null,
        @SerializedName("referrer")
        val referrer: Any? = null,
        @SerializedName("sentry")
        val sentry: Sentry? = null,
        @SerializedName("session")
        val session: String? = null,
        @SerializedName("signature")
        val signature: String? = null,
        @SerializedName("text_tracks")
        val textTracks: List<TextTrack?>? = null,
        @SerializedName("timestamp")
        val timestamp: Int? = null,
        @SerializedName("urls")
        val urls: Urls? = null
    ) {
        data class AbTests(
            @SerializedName("chromecast")
            val chromecast: Chromecast? = null,
            @SerializedName("cmcd")
            val cmcd: Cmcd? = null,
            @SerializedName("llhls_timeout")
            val llhlsTimeout: LlhlsTimeout? = null,
            @SerializedName("stats_fresnel")
            val statsFresnel: StatsFresnel? = null
        ) {
            data class Chromecast(
                @SerializedName("data")
                val `data`: Data? = null,
                @SerializedName("group")
                val group: Boolean? = null,
                @SerializedName("track")
                val track: Boolean? = null
            ) {
                class Data
            }

            data class Cmcd(
                @SerializedName("data")
                val `data`: Data? = null,
                @SerializedName("group")
                val group: Boolean? = null,
                @SerializedName("track")
                val track: Boolean? = null
            ) {
                class Data
            }

            data class LlhlsTimeout(
                @SerializedName("data")
                val `data`: Data? = null,
                @SerializedName("group")
                val group: Boolean? = null,
                @SerializedName("track")
                val track: Boolean? = null
            ) {
                class Data
            }

            data class StatsFresnel(
                @SerializedName("data")
                val `data`: Data? = null,
                @SerializedName("group")
                val group: Boolean? = null,
                @SerializedName("track")
                val track: Boolean? = null
            ) {
                class Data
            }
        }

        data class Build(
            @SerializedName("backend")
            val backend: String? = null,
            @SerializedName("js")
            val js: String? = null
        )

        data class Client(
            @SerializedName("ip")
            val ip: String? = null
        )

        data class Cookie(
            @SerializedName("captions")
            val captions: Any? = null,
            @SerializedName("captions_styles")
            val captionsStyles: CaptionsStyles? = null,
            @SerializedName("hd")
            val hd: Int? = null,
            @SerializedName("quality")
            val quality: Any? = null,
            @SerializedName("scaling")
            val scaling: Int? = null,
            @SerializedName("volume")
            val volume: Double? = null
        ) {
            data class CaptionsStyles(
                @SerializedName("bgColor")
                val bgColor: Any? = null,
                @SerializedName("bgOpacity")
                val bgOpacity: Any? = null,
                @SerializedName("color")
                val color: Any? = null,
                @SerializedName("edgeStyle")
                val edgeStyle: Any? = null,
                @SerializedName("fontFamily")
                val fontFamily: Any? = null,
                @SerializedName("fontOpacity")
                val fontOpacity: Any? = null,
                @SerializedName("fontSize")
                val fontSize: Any? = null,
                @SerializedName("windowColor")
                val windowColor: Any? = null,
                @SerializedName("windowOpacity")
                val windowOpacity: Any? = null
            )
        }

        data class FileCodecs(
            @SerializedName("av1")
            val av1: List<Any?>? = null,
            @SerializedName("avc")
            val avc: List<String?>? = null,
            @SerializedName("hevc")
            val hevc: Hevc? = null
        ) {
            data class Hevc(
                @SerializedName("dvh1")
                val dvh1: List<Any?>? = null,
                @SerializedName("hdr")
                val hdr: List<Any?>? = null,
                @SerializedName("sdr")
                val sdr: List<Any?>? = null
            )
        }

        data class Files(
            @SerializedName("dash")
            val dash: Dash? = null,
            @SerializedName("hls")
            val hls: Hls? = null,
            @SerializedName("progressive")
            val progressive: List<Progressive?>? = null
        ) {
            data class Dash(
                @SerializedName("cdns")
                val cdns: Cdns? = null,
                @SerializedName("default_cdn")
                val defaultCdn: String? = null,
                @SerializedName("separate_av")
                val separateAv: Boolean? = null,
                @SerializedName("streams")
                val streams: List<Stream?>? = null,
                @SerializedName("streams_avc")
                val streamsAvc: List<StreamsAvc?>? = null
            ) {
                data class Cdns(
                    @SerializedName("akfire_interconnect_quic")
                    val akfireInterconnectQuic: AkfireInterconnectQuic? = null,
                    @SerializedName("fastly_skyfire")
                    val fastlySkyfire: FastlySkyfire? = null
                ) {
                    data class AkfireInterconnectQuic(
                        @SerializedName("avc_url")
                        val avcUrl: String? = null,
                        @SerializedName("origin")
                        val origin: String? = null,
                        @SerializedName("url")
                        val url: String? = null
                    )

                    data class FastlySkyfire(
                        @SerializedName("avc_url")
                        val avcUrl: String? = null,
                        @SerializedName("origin")
                        val origin: String? = null,
                        @SerializedName("url")
                        val url: String? = null
                    )
                }

                data class Stream(
                    @SerializedName("fps")
                    val fps: Int? = null,
                    @SerializedName("id")
                    val id: String? = null,
                    @SerializedName("profile")
                    val profile: Int? = null,
                    @SerializedName("quality")
                    val quality: String? = null
                )

                data class StreamsAvc(
                    @SerializedName("fps")
                    val fps: Int? = null,
                    @SerializedName("id")
                    val id: String? = null,
                    @SerializedName("profile")
                    val profile: Int? = null,
                    @SerializedName("quality")
                    val quality: String? = null
                )
            }

            data class Hls(
                @SerializedName("captions")
                val captions: String? = null,
                @SerializedName("cdns")
                val cdns: Cdns? = null,
                @SerializedName("default_cdn")
                val defaultCdn: String? = null,
                @SerializedName("separate_av")
                val separateAv: Boolean? = null
            ) {
                data class Cdns(
                    @SerializedName("akfire_interconnect_quic")
                    val akfireInterconnectQuic: AkfireInterconnectQuic? = null,
                    @SerializedName("fastly_skyfire")
                    val fastlySkyfire: FastlySkyfire? = null
                ) {
                    data class AkfireInterconnectQuic(
                        @SerializedName("avc_url")
                        val avcUrl: String? = null,
                        @SerializedName("captions")
                        val captions: String? = null,
                        @SerializedName("origin")
                        val origin: String? = null,
                        @SerializedName("url")
                        val url: String? = null
                    )

                    data class FastlySkyfire(
                        @SerializedName("avc_url")
                        val avcUrl: String? = null,
                        @SerializedName("captions")
                        val captions: String? = null,
                        @SerializedName("origin")
                        val origin: String? = null,
                        @SerializedName("url")
                        val url: String? = null
                    )
                }
            }

            data class Progressive(
                @SerializedName("cdn")
                val cdn: String? = null,
                @SerializedName("fps")
                val fps: Int? = null,
                @SerializedName("height")
                val height: Int? = null,
                @SerializedName("id")
                val id: String? = null,
                @SerializedName("mime")
                val mime: String? = null,
                @SerializedName("origin")
                val origin: String? = null,
                @SerializedName("profile")
                val profile: String? = null,
                @SerializedName("quality")
                val quality: String? = null,
                @SerializedName("url")
                val url: String? = null,
                @SerializedName("width")
                val width: Int? = null
            )
        }

        data class Flags(
            @SerializedName("autohide_controls")
            val autohideControls: Int? = null,
            @SerializedName("dnt")
            val dnt: Int? = null,
            @SerializedName("partials")
            val partials: Int? = null,
            @SerializedName("plays")
            val plays: Int? = null,
            @SerializedName("preload_video")
            val preloadVideo: String? = null
        )

        data class GcDebug(
            @SerializedName("bucket")
            val bucket: String? = null
        )

        data class Sentry(
            @SerializedName("debug_enabled")
            val debugEnabled: Boolean? = null,
            @SerializedName("debug_intent")
            val debugIntent: Int? = null,
            @SerializedName("enabled")
            val enabled: Boolean? = null,
            @SerializedName("url")
            val url: String? = null
        )

        data class TextTrack(
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("kind")
            val kind: String? = null,
            @SerializedName("label")
            val label: String? = null,
            @SerializedName("lang")
            val lang: String? = null,
            @SerializedName("url")
            val url: String? = null
        )

        data class Urls(
            @SerializedName("barebone_js")
            val bareboneJs: String? = null,
            @SerializedName("chromeless_css")
            val chromelessCss: String? = null,
            @SerializedName("chromeless_js")
            val chromelessJs: String? = null,
            @SerializedName("css")
            val css: String? = null,
            @SerializedName("fresnel")
            val fresnel: String? = null,
            @SerializedName("fresnel_chunk_url")
            val fresnelChunkUrl: String? = null,
            @SerializedName("fresnel_manifest_url")
            val fresnelManifestUrl: String? = null,
            @SerializedName("fresnel_mimir_inputs_url")
            val fresnelMimirInputsUrl: String? = null,
            @SerializedName("js")
            val js: String? = null,
            @SerializedName("js_base")
            val jsBase: String? = null,
            @SerializedName("mux_url")
            val muxUrl: String? = null,
            @SerializedName("player_telemetry_url")
            val playerTelemetryUrl: String? = null,
            @SerializedName("proxy")
            val proxy: String? = null,
            @SerializedName("test_imp")
            val testImp: String? = null,
            @SerializedName("three_js")
            val threeJs: String? = null,
            @SerializedName("vuid_js")
            val vuidJs: String? = null
        )
    }

    data class Seo(
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("embed_url")
        val embedUrl: String? = null,
        @SerializedName("thumbnail")
        val thumbnail: String? = null,
        @SerializedName("upload_date")
        val uploadDate: String? = null
    )

    data class User(
        @SerializedName("account_type")
        val accountType: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("liked")
        val liked: Int? = null,
        @SerializedName("logged_in")
        val loggedIn: Int? = null,
        @SerializedName("mod")
        val mod: Int? = null,
        @SerializedName("owner")
        val owner: Int? = null,
        @SerializedName("private_mode_enabled")
        val privateModeEnabled: Int? = null,
        @SerializedName("team_id")
        val teamId: Int? = null,
        @SerializedName("team_origin_user_id")
        val teamOriginUserId: Int? = null,
        @SerializedName("vimeo_api_client_token")
        val vimeoApiClientToken: Any? = null,
        @SerializedName("vimeo_api_interaction_tokens")
        val vimeoApiInteractionTokens: Any? = null,
        @SerializedName("watch_later")
        val watchLater: Int? = null
    )

    data class Video(
        @SerializedName("allow_hd")
        val allowHd: Int? = null,
        @SerializedName("default_to_hd")
        val defaultToHd: Int? = null,
        @SerializedName("duration")
        val duration: Int? = null,
        @SerializedName("embed_code")
        val embedCode: String? = null,
        @SerializedName("embed_permission")
        val embedPermission: String? = null,
        @SerializedName("fps")
        val fps: Double? = null,
        @SerializedName("hd")
        val hd: Int? = null,
        @SerializedName("height")
        val height: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("lang")
        val lang: String? = null,
        @SerializedName("live_event")
        val liveEvent: Any? = null,
        @SerializedName("owner")
        val owner: Owner? = null,
        @SerializedName("privacy")
        val privacy: String? = null,
        @SerializedName("rating")
        val rating: Rating? = null,
        @SerializedName("share_url")
        val shareUrl: String? = null,
        @SerializedName("spatial")
        val spatial: Int? = null,
        @SerializedName("thumbs")
        val thumbs: Thumbs? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("unlisted_hash")
        val unlistedHash: Any? = null,
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("version")
        val version: Version? = null,
        @SerializedName("width")
        val width: Int? = null
    ) {
        data class Owner(
            @SerializedName("account_type")
            val accountType: String? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("img")
            val img: String? = null,
            @SerializedName("img_2x")
            val img2x: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("url")
            val url: String? = null
        )

        data class Rating(
            @SerializedName("id")
            val id: Int? = null
        )

        data class Thumbs(
            @SerializedName("base")
            val base: String? = null,
            @SerializedName("1280")
            val x1280: String? = null,
            @SerializedName("640")
            val x640: String? = null,
            @SerializedName("960")
            val x960: String? = null
        )

        data class Version(
            @SerializedName("available")
            val available: Any? = null,
            @SerializedName("current")
            val current: Any? = null
        )
    }
}