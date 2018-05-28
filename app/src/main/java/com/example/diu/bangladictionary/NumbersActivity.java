package com.example.diu.bangladictionary;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ListView listView;
    private AudioManager mAudioManager;

    /**
     * show massage when mediplayer finish the playing the audio
     */

    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
            Toast.makeText(NumbersActivity.this, "I am done", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListner=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        addWordsInArrayList();


    }

    //arraylist practise
    public void addWordsInArrayList(){
        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager=(AudioManager)getSystemService(NumbersActivity.AUDIO_SERVICE);
        //create object for ArraylIist
        //ArrayList<String> words=new ArrayList<String>();
        //add words in ArrayList
        /*words.add("One");
        words.add("Two");
        words.add("Three");
        words.add("Four");
        words.add("Five");
        words.add("Six");
        words.add("Seven");
        words.add("Eight");
        words.add("Nine");
        words.add("Ten");*/

        //create arraylist with custom Word object
       // ArrayList<Word> words=new ArrayList<Word>();
        //add word in arraylist
        //words.add(new Word("One","এক"));

        //using list view and ArrayAdapter
        //ArrayAdapter<Word> itemsAdapter=new ArrayAdapter<Word>(this,android.R.layout.simple_list_item_1,words);
        //create WordAdapter object
        //WordAdapter wordAdapter=new WordAdapter(this,words);
        //ListView listView=(ListView)findViewById(R.id.list);
        //GridView gridView=(GridView)findViewById(R.id.list);
        //listView.setAdapter(wordAdapter);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "এক",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two", "দুই",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "তিন",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "চার" ,R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "পাঁচ",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "ছয়",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "সাত",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "আট",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "নয়",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "দশ",R.drawable.number_ten,R.raw.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
         listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        //verify the contents of the arrayList by printing out each ArrayList element to the logs
        /*Log.v("NumbersActivity","Words at index 0: "+words.get(0));
        Log.v("NumbersActivity","Words at index 1: "+words.get(1));
        Log.v("NumbersActivity","Words at index 2: "+words.get(2));
        Log.v("NumbersActivity","Words at index 3: "+words.get(3));
        Log.v("NumbersActivity","Words at index 4: "+words.get(4));
        Log.v("NumbersActivity","Words at index 5: "+words.get(5));
        Log.v("NumbersActivity","Words at index 6: "+words.get(6));
        Log.v("NumbersActivity","Words at index 7: "+words.get(7));
        Log.v("NumbersActivity","Words at index 8: "+words.get(8));
        Log.v("NumbersActivity","Words at index 9: "+words.get(9));*/
        //create LinearLayout  object

        /*LinearLayout rootView=(LinearLayout)findViewById(R.id.rootView);
        //show all words using loops
        for(int index=0;index<words.size();index++){
            TextView wordView=new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);
        }*/

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Release the media player if it currently exists because we are about to
                        // play a different sound file
                        releaseMediaPlayer();
                        Word word=words.get(position);
                        //print msg in log
                        //Log.v("NumbersActivity","Current word"+word);
                        // Request audio focus so in order to play the audio file. The app needs to play a
                        // short audio file, so we will request audio focus with a short amount of time
                        // with AUDIOFOCUS_GAIN_TRANSIENT.

                        int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListner,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                            // We have audio focus now.

                            // Create and setup the {@link MediaPlayer} for the audio resource associated
                            // with the current word
                            mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioresourceId());

                            // Start the audio file
                            mediaPlayer.start();

                            // Setup a listener on the media player, so that we can stop and release the
                            // media player once the sound has finished playing.
                            mediaPlayer.setOnCompletionListener(onCompletionListener);

                        }
                    }
                }
        );


    }

    @Override
    protected void onStop() {
        super.onStop();
        //when the activity is stopped, release the media player resource because we won't be playing any more sound
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if(mediaPlayer!=null){
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer=null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListner);
        }
    }
}
