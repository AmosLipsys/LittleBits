package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
public class IfAndElseTutorial extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String API_KEY = "YOUR API KEY";
    private static final String VIDEO_ID = "Nt2_H5jN6Hg";
    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if_and_else_tutorial);
        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        Button next_button = (Button) findViewById(R.id.next_butt);
        Button back_button = (Button) findViewById(R.id.back_butt);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IfAndElseTutorial.this,ForLoopTutorial.class));
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IfAndElseTutorial.this,Tutorial.class));
            }
        });
        // Initializing YouTube Player View
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "This does NOT work in the emulator because it cannot play YouTube videos, because YouTube have different formats (avi, mp4, mov, wmv etc)", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "It will work on real android devices. Sorry about the inconvenience", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
       // add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        // Start buffering
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
    private final PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };
    private final PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
            preferences.edit()
                    .putInt("watched_if_and_else", 1)
                    .apply();
        }
        @Override
        public void onVideoStarted() {
        }
    };
}