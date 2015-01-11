package by.todd.web.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sergey on 22.11.2014.
 */
public class InputHelper {

    public static String getRaw(HttpServletRequest req) throws IOException {
        ServletInputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        StringBuilder sb = new StringBuilder();
        String line;
        try {
            inputStream = req.getInputStream();
            reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return sb.toString();
    }
}
