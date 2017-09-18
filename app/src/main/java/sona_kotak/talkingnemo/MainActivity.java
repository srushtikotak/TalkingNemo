package sona_kotak.talkingnemo;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;
    TextView t1;
    SpeechRecognizer spr;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1= (TextView) findViewById(R.id.textView2);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(new Locale("hin", "IND", "variant"));
                tts.setPitch(2.0f);
                tts.setSpeechRate(1.0f);
            }
        });
    }
    public void listen(View v) {
        Animation animation =
                AnimationUtils.loadAnimation(
                        this, R.anim.mixedanimation);
        animation.setRepeatCount(5);
        imageButton.startAnimation(animation);

        spr = SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
        spr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> msg = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                t1.setText(msg.get(0));
                tts.speak(t1.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        Intent recogniserIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, MainActivity.this.getPackageName());
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recogniserIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        spr.startListening(recogniserIntent);
    }
    public void stopListening(View v){
        if(spr != null){
            spr.stopListening();
        }
    }
}