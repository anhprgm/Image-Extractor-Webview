# Image-Extractor-Webview
how to extract images from webview in android
```
## Usage
```java, kotlin
```
```kotlin
Activity : ImageAttributeListener
val url = "example.com"
ImageAttributeFetcher(this).execute(url)
override fun onImageAttributesFetched(imageInfoList: List<ImageInfo>) {
        // do something with imageInfoList
}
```
```java
Activity extend ImageAttributeListener
String url = "example.com";
ImageAttributeFetcher attributeFetcher = new ImageAttributeFetcher(this);
attributeFetcher.execute(url);
@Override
public void onImageAttributesFetched(List<ImageInfo> imageInfoList) {
        // do something with imageInfoList
}
```
// Example Detail ImageInfo
```kotlin
class DetailImage (var sourceLink: String = "", var className: String = "", var alt : String = "")

val detailImageList = ArrayList<DetailImage>()
for (imageInfo: ImageInfo in imageInfoList) {
    val detailImage = DetailImage()
    val imageUrl = imageInfo.imageUrl
    detailImage.sourceLink = imageUrl
    for (attribute: String in imageInfo.attributes) {
        if (attribute.contains("alt")) {
            val alt = attribute.split("=")[1]
            detailImage.alt = alt
        } else if (attribute.contains("class")) {
            val className = attribute.split("=")[1]
            detailImage.className = className
        }
    }
    detailImageList.add(detailImage)
}
```
```java
class DetailImage {
    private String sourceLink;
    private String className;
    private String alt;
    public DetailImage(String sourceLink, String className, String alt) {
        this.sourceLink = sourceLink;
        this.className = className;
        this.alt = alt;
    }
    public String getSourceLink() {
        return sourceLink;
    }
    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getAlt() {
        return alt;
    }
    public void setAlt(String alt) {
        this.alt = alt;
    }
}

List<DetailImage> detailImageList = new ArrayList<>();
for (ImageInfo imageInfo : imageInfoList) {
    DetailImage detailImage = new DetailImage();
    String imageUrl = imageInfo.getImageUrl();
    detailImage.setSourceLink(imageUrl);
    for (String attribute : imageInfo.getAttributes()) {
        if (attribute.contains("alt")) {
            String alt = attribute.split("=")[1];
            detailImage.setAlt(alt);
        } else if (attribute.contains("class")) {
            String className = attribute.split("=")[1];
            detailImage.setClassName(className);
        }
    }
    detailImageList.add(detailImage);
}
```
## License
By theanhvippromax


