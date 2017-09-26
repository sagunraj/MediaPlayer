package np.com.sagunrajlage.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    MediaController mediaController;

    public void makeToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void onPlay(View view) {
        videoView.pause();
        mediaPlayer.start();
        makeToast("Audio playing started! \nNow Playing: WE WILL ROCK YOU - Queen.");

    }

    public void onPause(View view){
        mediaPlayer.pause();
        makeToast("Audio playing paused!");
    }

    public void onPlayVideo(View view){
        mediaPlayer.pause();
        videoView.start();
        makeToast("Video playing started! \nNow Playing: JUST THE WAY YOU ARE - Bruno Mars.");

    }

    public void onPauseVideo(View view){
        videoView.pause();
        makeToast("Video playing paused.");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.vid); //vid is the name of the video
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        mediaPlayer = MediaPlayer.create(this, R.raw.rock);
    }
}
