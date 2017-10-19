package np.com.sagunrajlage.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    VideoView videoView;
    MediaController mediaController;
    AudioManager audioManager;
    SeekBar scrubber;

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

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); //get information about volume from the device
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //maximum media volume accessed
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //current media volume

        SeekBar volumeControl = (SeekBar)findViewById(R.id.seekBar);

        volumeControl.setMax(maxVolume); //max seekbar value

        volumeControl.setProgress(curVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar value: ",Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }
        });


        scrubber = (SeekBar)findViewById(R.id.scrubber);
        scrubber.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mediaPlayer.getCurrentPosition());
            }

        },0,1000);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
