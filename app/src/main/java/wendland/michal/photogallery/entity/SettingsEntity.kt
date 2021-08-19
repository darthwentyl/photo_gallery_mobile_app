package wendland.michal.photogallery.entity

class SettingsEntity() {
    private var language: String = "Polish"

    fun setLanguage(language: String) {
        this.language = language
    }

    fun getLanguage() : String {
        return this.language
    }
}
