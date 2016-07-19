package unstudio.chinacraft.util.checker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by trychen on 16/7/19.
 */
public class VersionChecker extends Thread{
    private String nowVersion = null;
    private int projectID = -1;
    private Logger log = null;
    private String latestVersion = null;
    private String downloadUrl = null;
    private String instruction = null;
    private String changelog = null;
    private int date = 0;
    private boolean checked = false;
    protected boolean latest = false;

    public VersionChecker(String nowVersion, int projectID, Logger log) {
        this.nowVersion = nowVersion;
        this.projectID = projectID;
        this.log = log;
    }

    @Override
    public void run() {
        super.run();
        if (!getRemoteVersionModel()) return;
        latest = judgeIfLatest();
        checked = true;
        if (latest) findNewVersion();
        else isNewestVersion();
    }

    public void findNewVersion(){

    }

    public void isNewestVersion(){

    }

    protected boolean getRemoteVersionModel() {
        try {
            String serverReturnStringJsonData = getJsonString("http://mccraft.cn/api/project/" + projectID);
            JsonObject json = new JsonParser().parse(serverReturnStringJsonData).getAsJsonObject().get("latest_version").getAsJsonObject();
            latestVersion = json.get("version").getAsString();
            System.out.println(latestVersion);
            downloadUrl = json.get("download_url").getAsString();
            instruction = json.get("instruction").getAsString();
            changelog = json.get("changelog").getAsString();
            date = json.get("date").getAsInt();
            return true;
        }catch (NullPointerException e){
            latest = true;
            return true;
        } catch (Exception e) {
            log.log(Level.WARN, "Get Update Info Failed!", e);
            return false;
        }
    }

    protected boolean judgeIfLatest() {
        if (latest) return true;
        if (nowVersion.equals(latestVersion)) return true;
        if (nowVersion.startsWith("Beta")){
            if (latestVersion.startsWith("Beta")){
                return isNew(nowVersion,latestVersion);
            } else {
                String[] a = nowVersion.split("-")[1].split("\\.");
                return Integer.valueOf(a[a.length-1]) >= Integer.valueOf(latestVersion.split(" ")[1]);
            }
        } else {
            if (latestVersion.startsWith("Beta")){
                String[] a = latestVersion.split(" ")[1].split("\\.");
                return Integer.valueOf(nowVersion.split("-")[1]) >= Integer.valueOf(a[a.length-1]);
            } else {
                return Integer.valueOf(nowVersion.split("-")[1]) >= Integer.valueOf(latestVersion.split(" ")[1]);
            }
        }
    }

    public synchronized boolean isLatestVersion() {
        return latest;
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

    public Logger getLog() {
        return log;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getChangelog() {
        return changelog;
    }

    public boolean isNew(String now,String svr){
        String[] currect = now.split("-")[1].split("\\.");
        String[] newest = svr.split(" ")[1].split("\\.");
        int size = currect.length > newest.length ? newest.length:currect.length;
        try {
            for (int i = 0; i < (size - 1); i++) {
                int c = Integer.parseInt(currect[i]);
                int n = Integer.parseInt(newest[i]);
                if (n > c)
                    return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return true;
    }
    /**
     * 向URL发送GET请求
     *
     * @return 服务器返回内容
     * @throws MalformedURLException
     */
    public String getJsonString(String urlPath) throws MalformedURLException {
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
