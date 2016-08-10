package unstudio.chinacraft.util.remote;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

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
    private boolean enableSanpShot = false;
    private int date = 0;
    private boolean checked = false;
    protected boolean latest = false;

    public VersionChecker(String nowVersion, int projectID, Logger log,boolean enableSanpShot) {
        this.nowVersion = nowVersion;
        this.projectID = projectID;
        this.log = log;
        this.enableSanpShot = enableSanpShot;
    }

    @Override
    public void run() {
        super.run();
        if (!getRemoteVersionModel()) return;
        latest = judgeIfLatest();
        checked = true;
        if (isLatestVersion()) isNewestVersion();
        else findNewVersion();
    }

    public void findNewVersion(){

    }

    public void isNewestVersion(){

    }

    protected boolean getRemoteVersionModel() {
        try {
            String serverReturnStringJsonData = Network.getJsonString("http://mccraft.cn/api/project/" + projectID);
            JsonObject json = new JsonParser().parse(serverReturnStringJsonData).getAsJsonObject().get("latest_version").getAsJsonObject();
            latestVersion = json.get("version").getAsString();
            downloadUrl = json.get("download_url").getAsString();
            instruction = json.get("instruction").getAsString();
            changelog = json.get("changelog").getAsString();
            date = json.get("date").getAsInt();
            return true;
        }catch (NullPointerException e){
            latest = true;
            return true;
        } catch (IllegalStateException e){
            return false;
        } catch (Exception e) {
            log.log(Level.WARN, "Get Update Info Failed!", e);
            return false;
        }
    }

    protected boolean judgeIfLatest() {
        if (latest) return true;
        if (!enableSanpShot&&latestVersion.startsWith("SanpShot")) return true;
        if (nowVersion.equals(latestVersion)) return true;
        if (nowVersion.startsWith("Beta")||nowVersion.startsWith("Release")){
            if (latestVersion.startsWith("Beta")||latestVersion.startsWith("Release")){
                return isNew(nowVersion,latestVersion);
            } else {
                String[] a = nowVersion.split("-")[1].split("\\.");
                return Integer.valueOf(a[a.length-1]) >= Integer.valueOf(latestVersion.split(" ")[1]);
            }
        } else {
            if (latestVersion.startsWith("Beta")||latestVersion.startsWith("Release")){
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
}
