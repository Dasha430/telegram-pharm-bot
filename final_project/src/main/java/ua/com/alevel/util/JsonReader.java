package ua.com.alevel.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class JsonReader {
    private static String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject read(final String url) throws IOException, JSONException {
        final InputStream is = new URL(url).openStream();
        try(BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))) {
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }


}
