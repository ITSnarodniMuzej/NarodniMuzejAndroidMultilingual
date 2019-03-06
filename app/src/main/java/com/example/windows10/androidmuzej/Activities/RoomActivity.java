package com.example.windows10.androidmuzej.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;

import com.example.windows10.androidmuzej.Page;
import com.example.windows10.androidmuzej.PageImage;
import com.example.windows10.androidmuzej.PageImageAdapter;
import com.example.windows10.androidmuzej.R;
import com.example.windows10.androidmuzej.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    Room room;
    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();
        //int roomNumber = intent.getIntExtra("roomNumber",-1);
        String rNumber = intent.getStringExtra("roomNumber");
        int roomNumber = Integer.parseInt(rNumber.trim());

        room = setupRoom(roomNumber);

        setLogo(room);

        setTitle(room);

        setPageNavigationButtons();

        setPageCounter(currentPage);

        fillPage(currentPage);
    }

    private void setTitle(Room room)
    {
        TextView tvRoomTitle = findViewById(R.id.tvRoomTitle);
        tvRoomTitle.setText(room.getTitle());
    }

    private void setLogo(Room room)
    {
        ImageView ivRoomLogo = findViewById(R.id.ivRoomLogo);
        ivRoomLogo.setImageDrawable(room.getLogo());
    }

    void fillPage(int pageId)
    {
        //argument pageId start at 1
        //we need it to start from 0
        pageId--;

        //Setting page text
        TextView textView = findViewById(R.id.tvPageTitle);
        textView.setText(room.getPages().get(pageId).getText());

        //if page has no images
        //Hide imagesListView
        ConstraintLayout imagesLayout = findViewById(R.id.imagesLayout);
        if(room.getPages().get(pageId).getPageImages().isEmpty())
        {
            imagesLayout.setVisibility(View.GONE);
            return;
        }
        else
        {
            imagesLayout.setVisibility(View.VISIBLE);
        }

        final RecyclerView imagesListView = findViewById(R.id.lvPageImages);

        //Setting layout manager for images
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imagesListView.setLayoutManager(layoutManager);

        //Setting adapter for images
        PageImageAdapter imageAdapter = new PageImageAdapter(this, room.getPages().get(pageId).getPageImages());
        imagesListView.setAdapter(imageAdapter);

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
    }

    private void setPageNavigationButtons()
    {
        final ImageButton btnBack = findViewById(R.id.btnRoomBack);
        final ImageButton btnForward = findViewById(R.id.btnRoomForward);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnForward.setVisibility(View.VISIBLE);

                if(currentPage == 1)
                    return;

                currentPage --;

                if(currentPage == 1)
                    btnBack.setVisibility(View.GONE);

                fillPage(currentPage);

                setPageCounter(currentPage);
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBack.setVisibility(View.VISIBLE);

                if(currentPage == room.getPages().size())
                    return;

                currentPage ++;

                if(currentPage == room.getPages().size())
                    btnForward.setVisibility(View.GONE);

                fillPage(currentPage);

                setPageCounter(currentPage);
            }
        });
    }

    private void setPageCounter(int currentPage)
    {
        TextView tvPageCounter = findViewById(R.id.tvRoomPageCount);

        int totalPages = room.getPages().size();
        String pagesCounter = currentPage+"/"+totalPages;

        tvPageCounter.setText(pagesCounter);
    }

    private Room setupRoom(int roomNumber)
    {
        Room room;
        switch (roomNumber)
        {
            case 1:
                room = createRoom1(roomNumber);
                break;
            case 2:
                room = createRoom2(roomNumber);
                break;
            case 3:
                room = createRoom3(roomNumber);
                break;
            case 4:
                room = createRoom4(roomNumber);
                break;
            case 5:
                room = createRoom5(roomNumber);
                break;
            case 6:
                room = createRoom6(roomNumber);
                break;
            case 7:
                room = createRoom7(roomNumber);
                break;
            case 8:
                room = createRoom8(roomNumber);
                break;
            case 9:
                room = createRoom9(roomNumber);
                break;
            default:
                throw new IllegalArgumentException("Soba ne postoji " + roomNumber);
        }
       return room;
    }

    private Room createRoom1(int roomNumber)
    {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.room1logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room1Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }
    private Room createRoom2(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room2Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom3(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room3Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom4(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room4Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom5(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room5Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom6(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room6Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom7(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room7Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom8(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room8Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }

    private Room createRoom9(int roomNumber) {
        room = new Room(roomNumber);
        room.setLogo(getDrawable(R.drawable.logo));
        room.setTitle(getString(R.string.app_name));

        String[] pagesText = getResources().getStringArray(R.array.room9Text);
        ArrayList<Page> pages = createPages(roomNumber, pagesText);
        room.setPages(pages);

        return room;
    }


    private ArrayList<Page> createPages(int roomNumber, String[] pagesText)
    {
        ArrayList<Page> pages = new ArrayList<>();
        for(int i = 0; i < pagesText.length; i++)
        {
            Page page = new Page();

            page.setText(pagesText[i]);

            ArrayList<PageImage> pageImages = new ArrayList<>();

            getPageImages(roomNumber, i, pageImages);

            getPageImageTitles(roomNumber, i, pageImages);

            page.setPageImages(pageImages);

            pages.add(page);
        }

        return pages;
    }

    private ArrayList<PageImage> getPageImages(int roomNumber, int pageNumber, ArrayList<PageImage> pageImages)
    {
        pageNumber += 1;

        String imageLocation = "room"+roomNumber+"Page"+pageNumber+"Images";

        int pageImagesId = getResources().getIdentifier(imageLocation, "array", getPackageName());
        String[] pageImagesLocation = getResources().getStringArray(pageImagesId);

        //Adding images to page
        for (String location : pageImagesLocation) {
            //Get drawable id from string
            int drawableId = getResources().getIdentifier(location, "drawable", getPackageName());

            Drawable imageDrawable = getDrawable(drawableId);

            PageImage pageImage = new PageImage(imageDrawable);

            pageImages.add(pageImage);
        }
        return pageImages;
    }

    private ArrayList<PageImage> getPageImageTitles(int roomNumber, int pageNumber, ArrayList<PageImage> pageImages)
    {
        pageNumber += 1;

        String imageTitleLocation = "room"+roomNumber+"Page"+pageNumber+"ImagesTitles";

        int pageImagesTitleId = getResources().getIdentifier(imageTitleLocation, "array", getPackageName());
        String[] pageImagesTitle = getResources().getStringArray(pageImagesTitleId);

        //Adding image titles to page images
        for(int j = 0; j < pageImagesTitle.length; j++)
        {
            String title = pageImagesTitle[j];
            pageImages.get(j).setImageTitle(title);
        }

        return pageImages;
    }
}
