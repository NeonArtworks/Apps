package at.neonartworks.ytdownloader;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.github.axet.vget.vhs.YouTubeInfo;
import com.github.axet.vget.vhs.YouTubeParser;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by NeonArtworks on 24.11.2016.
 */

public class Downloader extends AsyncTask<String, Integer, String> {
    String TAG = getClass().getSimpleName();
    private final String sound = "MP4 AAC k128";
    private String link;
    private String path;
    private final String video_320_AAC = "MP4 H264(p360) AAC";
    private String dlType;
    private String download;
    private String format;
    private String title;
    Context mContext;

    View view;


    public Downloader(Context c, String dlType) {
        this.mContext = c;
        this.dlType = dlType;
    }


    protected void onPreExecute() {
        Log.d(TAG + " PreExceute", "On pre Exceute......");
    }

    protected String doInBackground(String... urls) {
        Log.d(TAG + " DoINBackGround", "On doInBackground...");

        this.link = urls[0];

        if(dlType.equals("SOUND")){
            download = sound;
        }else if(dlType.equals("VIDEO (360p)")){
            download = video_320_AAC;
        }else{
            download = sound;
        }

        for (YouTubeParser.VideoDownload d : fetchVideoLink(link)) {
            if (d.stream.toString().startsWith((download))) {
                download(d.url.toString());

            }
        }

        for (int i = 0; i < 10; i++) {
            Integer in = new Integer(i);
            publishProgress(i);
        }
        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer... a) {
        Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
    }

    protected void onPostExecute(String result) {
        Log.d(TAG + " onPostExecute", "" + result);

    }

    private void download(String url) {
        Log.i("Downloader", url);
        if(dlType.equals("SOUND")){
            format = ".m4a";
        }else if(dlType.equals("VIDEO (360p)")){
            format = ".mp4";
        }else{
            format = ".m4a";
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title+format);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
        request.allowScanningByMediaScanner();// if you want to be available from media players
        DownloadManager manager = (DownloadManager) mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    private List<YouTubeParser.VideoDownload> fetchVideoLink(String url) {
        YouTubeInfo info;
        List<YouTubeParser.VideoDownload> list = null;
        try {
            info = new YouTubeInfo(new URL(url));
            YouTubeParser parser = new YouTubeParser();
            list = parser.extractLinks(info);
            title = info.getTitle();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        return list;
    }

}