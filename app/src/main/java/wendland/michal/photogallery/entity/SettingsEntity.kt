package wendland.michal.photogallery.entity

class SettingsEntity() {
    private var language: String = "Polski"

    fun setLanguage(language: String) {
        this.language = language
    }

    fun getLanguage() : String {
        return this.language
    }
}
