package unstudio.chinacraft.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.Level;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import unstudio.chinacraft.common.ChinaCraft;

public class VersionChecker extends Thread {
    private String latestVersion = null;
    private String downloadUrl = null;
    private String instruction = null;
    private String changelog = null;
    private int date = 0;
    private boolean checked = false;
    protected boolean lastest = false;

    @Override
    public void run() {
        super.run();
        getRemoteVersionModel();
        lastest = _isLastestVersion();
        checked = true;
    }

    protected synchronized void getRemoteVersionModel() {
        try {
            String serverReturnStringJsonData = getJsonString("http://mccraft.cn/api/project/" + ChinaCraft.PROJECT_ID);
            JsonObject json = (JsonObject)new JsonParser().parse(serverReturnStringJsonData);
            latestVersion = json.get("lastest_version.version").getAsString();
            downloadUrl = json.get("lastest_version.download_url").getAsString();
            instruction = json.get("lastest_version.instruction").getAsString();
            changelog = json.get("lastest_version.changelog").getAsString();
            date = json.get("lastest_version.date").getAsInt();
        } catch (NullPointerException e){
            lastest = true;
        } catch (Exception e) {
            ChinaCraft.log.log(Level.WARN, "获取服务器最新版本信息失败，请检查网络!", e);
        }
    }

    public synchronized boolean _isLastestVersion() {
        if (lastest) return true;
        if (ChinaCraft.VERSION.equals(latestVersion)) return true;
        String[] currect = ChinaCraft.VERSION.split("-")[1].split(".");
        String[] newest = latestVersion.split(" ")[1].split(".");
        int size = currect.length > newest.length ? currect.length : newest.length;
        try {
            for (int i = 0; i < (size - 1); i++) {
                int c = Integer.parseInt(currect[i]);
                int n = Integer.parseInt(newest[i]);
                if (n > c)
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public synchronized boolean isLatestVersion() {
        return lastest;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getChangelog() {
        return changelog;
    }

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
            ChinaCraft.log.log(Level.WARN, e.getMessage(), e);
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    ChinaCraft.log.log(Level.WARN, e.getMessage(), e);
                }
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    ChinaCraft.log.log(Level.WARN, e.getMessage(), e);
                }
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    ChinaCraft.log.log(Level.WARN, e.getMessage(), e);
                }
        }
        return "";
    }

}
