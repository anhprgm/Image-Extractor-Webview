import android.os.AsyncTask
import android.util.Log
import com.example.imageextractor.ImageAttributeListener
import com.example.imageextractor.ImageInfo
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class ImageAttributeFetcher(private val listener: ImageAttributeListener) :
    AsyncTask<String, Void, List<ImageInfo>>() {
    private val TAG = "ImageAttributeFetcher"

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg urls: String): List<ImageInfo> {
        val url = urls[0]
        val imageInfoList = mutableListOf<ImageInfo>()

        try {
            val doc: Document = Jsoup.connect(url).get()
            val imgElements: Elements = doc.select("img")

            for (imgElement: Element in imgElements) {
                val imageUrl: String = imgElement.attr("src")
                val attributes = mutableListOf<String>()

                for (attribute: org.jsoup.nodes.Attribute in imgElement.attributes()) {
                    val attributeName: String = attribute.key
                    val attributeValue: String = attribute.value
                    val attributeString = "$attributeName = $attributeValue"
                    attributes.add(attributeString)
                }

                val imageInfo = ImageInfo(imageUrl, attributes)
                imageInfoList.add(imageInfo)
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error fetching image attributes", e)
        }

        return imageInfoList
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(imageInfoList: List<ImageInfo>) {
        listener.onImageAttributesFetched(imageInfoList)
    }
}
