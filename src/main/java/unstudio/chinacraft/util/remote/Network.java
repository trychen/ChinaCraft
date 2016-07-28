package unstudio.sinocraft.util.remote;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import unstudio.sinocraft.common.SinoCraft;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by trychen on 16/7/19.
 */
public class Network {
    private static Logger log = SinoCraft.log;

    /**
     * 向URL发送GET请求
     *
     * @return 服务器返回内容
     * @throws MalformedURLException
     */
    public static String getJsonString(String urlPath) throws MalformedURLException {
        URL url = new URL(urlPath);
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            reader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(reader);
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null)
                sb.append(str);
            reader.close();
            connection.disconnect();
            return sb.toString();
        } catch (IOException e) {
            log.log(Level.WARN, e.getMessage(), e);
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.log(Level.WARN, e.getMessage(), e);
                }
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    log.log(Level.WARN, e.getMessage(), e);
                }
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.log(Level.WARN, e.getMessage(), e);
                }
        }
        return "";
    }
}
