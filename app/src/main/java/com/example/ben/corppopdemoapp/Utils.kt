package com.example.ben.corppopdemoapp

/**
 * General utils class to hold static methods and constants
 */
class Utils {

    companion object {
        const val DEFAULT_RESPONSE_FORMAT: String = "json"
        const val BASE_URL: String = "https://api.flickr.com/services/rest/"
        const val IMAGE_URL = "https://farm%s.staticflickr.com/%s/%s_%s.jpg"
        const val INTENT_EXTRA_IMAGE_ID = "imageId"
        //TODO make more secure
        const val API_KEY = "9f35619cbade323bf37fc0afa1a3b17a"
        const val METHOD_PARAM = "method"
        const val API_KEY_PARAM = "api_key"
        const val PHOTO_ID_PARAM = "photo_id"
        const val RESPONSE_TYPE_PARAM = "format"
        const val DEFAULT_RESULT_SIZE: Int = 20

        /**
         * Generates a url for a flikr image
         */
        fun generateImageUrl(farmId: String, serverId: String, imageId: String, seceret: String): String {
            return String.format(IMAGE_URL, farmId, serverId, imageId, seceret)
        }
    }
}