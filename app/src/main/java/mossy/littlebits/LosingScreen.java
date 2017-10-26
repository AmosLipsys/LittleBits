package mossy.littlebits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Locale;

public class LosingScreen extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private static final String API_KEY = "YOUR API KEY";
    private static String video_id = "UV2RmC4gDMQ";
    private SharedPreferences preferences;
    private SharedPreferences global_preferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing_screen);
        global_preferences = getSharedPreferences("global_preferences", MODE_PRIVATE);
        username = global_preferences.getString("username", "");
        preferences = getSharedPreferences(username, MODE_PRIVATE);
        final String current_difficultly = preferences.getString("difficulty", "");
        int level = preferences.getInt("level", 1);

        choose_url(current_difficultly, level);
        Button back_to_game_butt = (Button) findViewById(R.id.next_butt);
        Button  back_to_level_butt= (Button) findViewById(R.id.back_butt);
        TextView difficulty_level_text_view = (TextView) findViewById(R.id.difficulty_level_text_view);

        String difficulty_level_text = String.format(Locale.ENGLISH,"%s, Level %d",current_difficultly, level);
        difficulty_level_text_view.setText(difficulty_level_text);

        back_to_level_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_difficultly.matches("Beginner")){
                    startActivity(new Intent(LosingScreen.this,Beginner.class));
                }
                else if(current_difficultly.matches("Advanced")){
                    startActivity(new Intent(LosingScreen.this,Advanced.class));
                }
                else{
                    startActivity(new Intent(LosingScreen.this,Expert.class));
                }
            }
        });

        back_to_game_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LosingScreen.this,Game.class));
            }
        });
        // Initializing YouTube Player View
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "This does NOT work in the emulator because it cannot play YouTube videos, because YouTube have different formats (avi, mp4, mov, wmv etc)", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "It will work on real android devices. Sorry about the inconvenience", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        // add listeners to YouTubePlayer instance
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        // Start buffering
        if (!wasRestored) {
            player.cueVideo(video_id);
        }
    }
    private final YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
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
    private final YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };

    private void choose_url(String current_difficultly, int level){
        if (current_difficultly.matches("Beginner")) {
            switch (level) {
                case 1:
                    video_id = "Kkox7mn4itA";
                    break;
                case 2:
                    video_id = "Kkox7mn4itA";
                    break;
                case 3:
                    video_id = "Kkox7mn4itA";
                    break;
                case 4:
                    video_id = "Kkox7mn4itA";
                    break;
                case 5:
                    video_id = "Kkox7mn4itA";
                    break;
                case 6:
                    video_id = "Kkox7mn4itA";
                    break;
                default:
                    video_id = "Kkox7mn4itA";
            }
        }
        else if (current_difficultly.matches("Advanced")) {
            switch (level) {
                case 1:
                    video_id = "6B8jXsQ4IZE";
                    break;
                case 2:
                    video_id = "6B8jXsQ4IZE";
                    break;
                case 3:
                    video_id = "6B8jXsQ4IZE";
                    break;
                case 4:
                    video_id = "6B8jXsQ4IZE";
                    break;
                case 5:
                    video_id = "6B8jXsQ4IZE";
                    break;
                case 6:
                    video_id = "6B8jXsQ4IZE";
                    break;
                default:
                    video_id = "6B8jXsQ4IZE";
            }
        }
        else {
            switch (level) {
                case 1:
                    video_id = "nRewJSjU8mo";
                    break;
                case 2:
                    video_id = "nRewJSjU8mo";
                    break;
                case 3:
                    video_id = "nRewJSjU8mo";
                    break;
                case 4:
                    video_id = "nRewJSjU8mo";
                    break;
                case 5:
                    video_id = "nRewJSjU8mo";
                    break;
                case 6:
                    video_id = "nRewJSjU8mo";
                    break;
                default:
                    video_id = "nRewJSjU8mo";
            }
        }
    }




}
