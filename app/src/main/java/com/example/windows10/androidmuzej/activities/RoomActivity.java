package com.example.windows10.androidmuzej.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.windows10.androidmuzej.audioPlayer.AudioPlayerAdapter;
import com.example.windows10.androidmuzej.audioPlayer.AudioPlayerItem;
import com.example.windows10.androidmuzej.audioPlayer.RecyclerItemClickListener;
import com.example.windows10.androidmuzej.room.Page;
import com.example.windows10.androidmuzej.room.PageImage;
import com.example.windows10.androidmuzej.room.PageImageAdapter;
import com.example.windows10.androidmuzej.R;
import com.example.windows10.androidmuzej.room.Room;

import java.io.File;
import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private int currentPage = 1;

    private MediaPlayer mediaPlayer;

    Handler handler;
    Runnable runnable;

    String currentLocal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        fullscreen();

        Intent intent = getIntent();

        String rNumber = intent.getStringExtra("roomNumber");
        int roomNumber = Integer.parseInt(rNumber.trim());

        //Get locale code
        currentLocal = getResources().getConfiguration().getLocales().get(0).getLanguage();

        handler = new Handler();

        Room room = new Room(roomNumber);

        getRoomLogo(room);
        setRoomLogo(room);

        getRoomTitle(room);
        setRoomTitle(room);

        getAudioItems(room);
        setAudioItems(room);

        getRoomPages(room);

        setPageCounter(room, currentPage);

        fillPage(room, currentPage);

        setPageNavigationButtons(room);

        setImagesControls();

        setAudioPlayerControl(room);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            mediaPlayer = null;
        }

        handler.removeCallbacks(runnable);

    }

    private void getRoomLogo(Room room)
    {
        try {
            //Get logo id from strings.xml
            String logoLocation = "room"+room.getRoomNumber()+"Logo";
            int logoStringId = getResources().getIdentifier(logoLocation, "string", getPackageName());

            //Get drawable with name from strings.xml
            String logoName = getString(logoStringId);
            int drawableId = getResources().getIdentifier(logoName, "drawable", getPackageName());

            room.setLogo(getDrawable(drawableId));

        } catch (Exception e)
        {
            Log.e("getRoomLogo", "Logo not found.");
        }
    }

    private void setRoomLogo(Room room)
    {
        ImageView ivRoomLogo = findViewById(R.id.ivRoomLogo);
        ivRoomLogo.setImageDrawable(room.getLogo());
    }

    private void getRoomTitle(Room room)
    {
        try {
            //Get title id from strings.xml
            String titleLocation = "room"+room.getRoomNumber()+"Title";
            int titleStringId = getResources().getIdentifier(titleLocation, "string", getPackageName());

            //Get title value
            room.setTitle(getString(titleStringId));
        } catch (Exception e) {
            Log.e("getRoomTitle", "Room title not found.");
        }
    }

    private void setRoomTitle(Room room)
    {
        TextView tvRoomTitle = findViewById(R.id.tvRoomTitle);
        tvRoomTitle.setText(room.getTitle());
    }

    private void getAudioItems(Room room)
    {
        int roomNumber = room.getRoomNumber();

        try {
            //Get audio folder path
            String path = Environment.DIRECTORY_DOWNLOADS + "/Museum/Audio/" + currentLocal + "/room" + roomNumber;

            //Get all files from folder
            File[] mp3Files = Environment.getExternalStoragePublicDirectory(path).listFiles();

            //Add mp3 files paths to room
            for (File file : mp3Files)
            {
                //Cut .mp3 from file name
                String fileName = file.getName().substring(0, file.getName().length()-4);

                AudioPlayerItem item = new AudioPlayerItem(file.getAbsolutePath(), fileName);

                room.getAudioItems().add(item);
            }
        } catch (Exception e) {
            Log.e("getAudioItems", "Audio items not found.\n"+e.getMessage());
        }

    }

    private void setAudioItems(final Room room)
    {
        final RecyclerView lvAudioPlayer = findViewById(R.id.lvAudioItems);

        //Setting layout manager for images
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lvAudioPlayer.setLayoutManager(layoutManager);

        //Setting adapter for images
        final AudioPlayerAdapter audioPlayerAdapter = new AudioPlayerAdapter(this, room.getAudioItems());
        lvAudioPlayer.setAdapter(audioPlayerAdapter);

        //Custom itemClickListener for playing selected audio item
        lvAudioPlayer.addOnItemTouchListener(new RecyclerItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                resetPlaylist(lvAudioPlayer);

                ConstraintLayout unselectedItemLayout = v.findViewById(R.id.unselectedAudioLayout);
                ConstraintLayout selectedItemLayout = v.findViewById(R.id.selectedAudioLayout);

                selectedItemLayout.setVisibility(View.VISIBLE);
                unselectedItemLayout.setVisibility(View.GONE);

                AudioPlayerItem item = room.getAudioItems().get(position);

                File mp3File = new File(item.getFilePath());

                playAudio(mp3File);
            }
        }));

        audioPlayerAdapter.notifyDataSetChanged();
    }

    private void getRoomPages(Room room)
    {
        ArrayList<Page> pages = new ArrayList<>();

        int roomNumber = room.getRoomNumber();

        try {
            //Get text array id
            String pageTextLocation = "room"+roomNumber+"Text";
            int pagesTextId = getResources().getIdentifier(pageTextLocation, "array", getPackageName());

            //Get text array values
            String[] pagesText = getResources().getStringArray(pagesTextId);

            for(int i = 0; i < pagesText.length; i++)
            {
                int pageNumber = i + 1;

                Page page = new Page(pageNumber);

                getPageTitle(room, page);

                page.setText(pagesText[i]);

                try {
                    getPageImages(room, page);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), e.getMessage());
                }

                pages.add(page);
            }
        } catch (Resources.NotFoundException e) {
            Log.e("getRoomPages", "Pages not found.");
        }

        room.setPages(pages);
    }

    private void getPageTitle(Room room, Page page)
    {
        try {
            int roomNumber = room.getRoomNumber();
            int pageNumber = page.getPageNumber();

            //Get page title id
            String titleLocation = "room"+roomNumber+"Page"+pageNumber+"Title";
            int pageTitleId = getResources().getIdentifier(titleLocation, "string", getPackageName());

            //Get title
            String title = getString(pageTitleId);

            page.setTitle(title);

        } catch (Exception e) {
            Log.e("getPageTitle", "Page title not found.");
        }
    }

    private void getPageImages(Room room, Page page)
    {
        int roomNumber = room.getRoomNumber();
        int pageNumber = page.getPageNumber();

        try {
            //Get images array id
            String imageLocation = "room"+roomNumber+"Page"+pageNumber+"Images";
            int pageImagesId = getResources().getIdentifier(imageLocation, "array", getPackageName());
            //Get images array
            String[] pageImagesLocation = getResources().getStringArray(pageImagesId);

            //Get images title array id
            String imageTitleLocation = "room"+roomNumber+"Page"+pageNumber+"ImagesTitles";
            int pageImagesTitleId = getResources().getIdentifier(imageTitleLocation, "array", getPackageName());
            //Get images title array
            String[] pageImagesTitle = getResources().getStringArray(pageImagesTitleId);

            //Get images detail array id
            String imageDetailLocation = "room"+roomNumber+"Page"+pageNumber+"ImagesDetail";
            int pageImagesDetailId = getResources().getIdentifier(imageDetailLocation, "array", getPackageName());
            //Get images title array
            String[] pageImagesDetail = getResources().getStringArray(pageImagesDetailId);

            //Adding images to page
            for(int i = 0; i < pageImagesLocation.length; i++)
            {
                //Get image drawable id
                String location = pageImagesLocation[i];
                int drawableId = getResources().getIdentifier(location, "drawable", getPackageName());
                //Get image
                Drawable imageDrawable = getDrawable(drawableId);

                //Get image title
                String title = "";

                if(pageImagesTitle.length != 0 && pageImagesTitle[i] != null)
                    title = pageImagesTitle[i];

                String detail = "";

                if(pageImagesDetail.length != 0 && pageImagesDetail[i] != null)
                    detail = pageImagesDetail[i];

                PageImage pageImage = new PageImage(imageDrawable, title, detail);

                page.getPageImages().add(pageImage);
            }
        } catch (Resources.NotFoundException e) {
            Log.e("getPageImages", "Images not found.");
        }
    }

    private void setPageCounter(Room room, int currentPage)
    {
        TextView tvPageCounter = findViewById(R.id.tvRoomPageCount);

        int totalPages = room.getPages().size();
        String pagesCounter = currentPage+"/"+totalPages;

        tvPageCounter.setText(pagesCounter);
    }

    private void fillPage(Room room, int pageId)
    {
        //argument pageId start at 1
        //we need it to start from 0
        pageId--;

        Page page = room.getPages().get(pageId);

        //Setting page title
        TextView tvPageTitle = findViewById(R.id.pageTitle);
        tvPageTitle.setText(page.getTitle());

        //if page has no title
        //Hide title layout
        ConstraintLayout titleLayout = findViewById(R.id.titleLayout);
        if(page.getTitle() == null || page.getTitle().isEmpty())
        {
            titleLayout.setVisibility(View.GONE);
        }
        else
        {
            titleLayout.setVisibility(View.VISIBLE);
        }

        //Setting page text
        TextView textView = findViewById(R.id.tvPageText);
        textView.setText(page.getText());

        //if page has no images
        //Hide imagesListView
        ConstraintLayout imagesLayout = findViewById(R.id.imagesLayout);
        if(page.getPageImages().isEmpty())
        {
            imagesLayout.setVisibility(View.GONE);
        }
        //Else show imageListView and add adapter
        else
        {
            imagesLayout.setVisibility(View.VISIBLE);

            RecyclerView imagesListView = findViewById(R.id.lvPageImages);

            //Setting layout manager for images
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            imagesListView.setLayoutManager(layoutManager);

            //Setting adapter for images
            PageImageAdapter imageAdapter = new PageImageAdapter(this, room.getPages().get(pageId).getPageImages());
            imagesListView.setAdapter(imageAdapter);

            imageAdapter.notifyDataSetChanged();
        }
    }

    private void setPageNavigationButtons(final Room room)
    {
        final ImageButton btnBack = findViewById(R.id.btnRoomBack);
        final ImageButton btnForward = findViewById(R.id.btnRoomForward);
        ImageButton btnBackHall = findViewById(R.id.btnBackHall);

        btnBackHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeekBar seekBar = findViewById(R.id.audioSeekbar);
                seekBar.setProgress(0);

                finish();
            }
        });

        //Hide page navigation button if 1 or less pages
        if(room.getPages().size() <= 1)
        {
            btnBack.setVisibility(View.GONE);
            btnForward.setVisibility(View.GONE);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnForward.setVisibility(View.VISIBLE);

                currentPage --;

                if(currentPage == 1)
                    btnBack.setVisibility(View.GONE);

                fillPage(room, currentPage);

                setPageCounter(room, currentPage);
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBack.setVisibility(View.VISIBLE);

                currentPage ++;

                if(currentPage == room.getPages().size())
                    btnForward.setVisibility(View.GONE);

                fillPage(room, currentPage);

                setPageCounter(room, currentPage);
            }
        });
    }

    private void setImagesControls()
    {
        final RecyclerView imagesListView = findViewById(R.id.lvPageImages);

        final ImageButton btnPreviousImage = findViewById(R.id.btnPreviousImage);
        btnPreviousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageWidth = imagesListView.getChildAt(0).getWidth();
                imagesListView.scrollBy(-imageWidth, 0);
            }
        });

        final ImageButton btnNextImage = findViewById(R.id.btnNextImage);
        btnNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imageWidth = imagesListView.getChildAt(0).getWidth();
                imagesListView.scrollBy(imageWidth, 0);
            }
        });

        //Scroll listener
        //If end is reached turn off corresponding button
        imagesListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                //Show or hide Next Image Button if last image
                if(!recyclerView.canScrollHorizontally(1))
                    btnNextImage.setVisibility(View.INVISIBLE);
                else
                    btnNextImage.setVisibility(View.VISIBLE);

                //Show or hide Previous Image Button if first image
                if (!recyclerView.canScrollHorizontally(-1))
                    btnPreviousImage.setVisibility(View.INVISIBLE);
                else
                    btnPreviousImage.setVisibility(View.VISIBLE);

            }
        });

        //On image click listener
        imagesListView.addOnItemTouchListener(new RecyclerItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            int oldPos = -1;

            @Override
            public void onItemClick(View v, int position) {
                if(oldPos != position)
                {
                    Log.i("Item", position+"");
                    oldPos = position;

                }

            }
        }));
    }

    private void setAudioPlayerControl(final Room room)
    {
        final ConstraintLayout audioPlayerLayout = findViewById(R.id.audioPlayerLayout);

        ToggleButton audioPlayer = findViewById(R.id.btnAudioPlayer);

        //Hide audio player button if room has no audio items
        if(room.getAudioItems().size() <= 0)
        {
            ConstraintLayout audioPlayerButton = findViewById(R.id.audioButtonLayout);
            audioPlayerButton.setVisibility(View.GONE);
            audioPlayer.setVisibility(View.GONE);
            return;
        }

        if(audioPlayer.isChecked())
            audioPlayerLayout.setVisibility(View.VISIBLE);
        else
            audioPlayerLayout.setVisibility(View.GONE);

        audioPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = audioPlayerLayout.getVisibility();

                if(visibility == View.VISIBLE)
                {
                    audioPlayerLayout.setVisibility(View.GONE);
                }
                else if(visibility == View.GONE)
                {
                    audioPlayerLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        final ToggleButton audioPlay = findViewById(R.id.btnAudioPlay);
        audioPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If room has no audio
                //audioPlay click wont do anything
                if(room.getAudioItems().size() <= 0)
                    return;

                //Play pause control
                if(audioPlay.isChecked())
                {
                    mediaPlayer.pause();
                }
                else
                {
                    //If no audio is selected
                    //Play first audio
                    if(mediaPlayer == null)
                    {
                        RecyclerView lvAudioPlayer = findViewById(R.id.lvAudioItems);
                        lvAudioPlayer.getChildAt(0).findViewById(R.id.selectedAudioLayout).setVisibility(View.VISIBLE);
                        lvAudioPlayer.getChildAt(0).findViewById(R.id.unselectedAudioLayout).setVisibility(View.GONE);

                        File mp3File = new File(room.getAudioItems().get(0).getFilePath());

                        playAudio(mp3File);
                    }
                    //Otherwise play current audio
                    else
                    {
                        mediaPlayer.start();
                    }
                }
            }
        });

        //10 seconds
        final int seekMillisecond = 10000;

        ImageButton btnAudioForward = findViewById(R.id.btnAudioForward);
        btnAudioForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + seekMillisecond);
            }
        });

        ImageButton btnAudioBackward = findViewById(R.id.btnAudioBackward);
        btnAudioBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - seekMillisecond);
            }
        });
    }

    private void playAudio(File mp3file)
    {
        //Stop previous audio
        if(mediaPlayer != null)
            mediaPlayer.reset();

        //Create and play audio
        mediaPlayer = MediaPlayer.create(RoomActivity.this, Uri.fromFile(mp3file));

        int duration = 0;
        if(mediaPlayer != null)
        {
            mediaPlayer.start();
            duration = mediaPlayer.getDuration();
        }

        //change play button
        final ToggleButton audioPlay = findViewById(R.id.btnAudioPlay);
        audioPlay.setChecked(false);

        final SeekBar seekBar = findViewById(R.id.audioSeekbar);
        seekBar.setMax(duration);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser && mediaPlayer != null)
                {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Mapping seekbar progress to media player audio
        runnable = new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null)
                {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                    if(seekBar.getProgress() >= seekBar.getMax())
                    {
                        audioPlay.setChecked(true);

                        seekBar.setProgress(0);

                        RecyclerView lvAudioPlaylist = findViewById(R.id.lvAudioItems);
                        resetPlaylist(lvAudioPlaylist);

                        mediaPlayer = null;
                    }
                }
                handler.postDelayed(this, 50);
            }
        };
        runnable.run();
    }

    private void resetPlaylist(RecyclerView lvAudioPlaylist)
    {
        for(int i = 0; i < lvAudioPlaylist.getChildCount(); i++)
        {
            ConstraintLayout selectedItemLayout = lvAudioPlaylist.getChildAt(i).findViewById(R.id.selectedAudioLayout);
            selectedItemLayout.setVisibility(View.GONE);

            ConstraintLayout unselectedItemLayout = lvAudioPlaylist.getChildAt(i).findViewById(R.id.unselectedAudioLayout);
            unselectedItemLayout.setVisibility(View.VISIBLE);
        }
    }

    public void fullscreen()
    {
        final View view = getWindow().getDecorView();

        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        //Hide navigation bar when visibility has changed
        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                view.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN
                );

            }
        });
    }
}
