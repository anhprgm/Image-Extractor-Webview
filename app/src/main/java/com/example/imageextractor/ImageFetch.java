package com.example.imageextractor;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFetch extends AsyncTask<String, Void, List<Image>> {

    private static final String TAG = "ImageFetcher";
    private String targetAttribute;
    private String targetValue;

    public ImageFetch(String targetAttribute, String targetValue) {
        this.targetAttribute = targetAttribute;
        this.targetValue = targetValue;
    }

    @Override
    protected List<Image> doInBackground(String... urls) {
        String url = urls[0];
        List<Image> imageInfoList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements imgElements = doc.select("img." + targetValue);

            for (Element imgElement : imgElements) {
                String imageUrl = imgElement.attr("src");
                List<String> attributes = new ArrayList<>();

                for (org.jsoup.nodes.Attribute attribute : imgElement.attributes()) {
                    String attributeName = attribute.getKey();
                    String attributeValue = attribute.getValue();
                    String attributeString = attributeName + " = " + attributeValue;
                    attributes.add(attributeString);
                }

                Image imageInfo = new Image(imageUrl, attributes);
                imageInfoList.add(imageInfo);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error fetching images", e);
        }

        return imageInfoList;
    }

    @Override
    protected void onPostExecute(List<Image> imageInfoList) {
        for (Image imageInfo : imageInfoList) {
            String imageUrl = imageInfo.getUrl();
            List<String> attributes = imageInfo.getAttributes();

            Log.d(TAG, "Image URL: " + imageUrl);
            Log.d(TAG, "Attributes: ");
            for (String attribute : attributes) {
                Log.d(TAG, "- " + attribute);
            }
        }
    }
}
