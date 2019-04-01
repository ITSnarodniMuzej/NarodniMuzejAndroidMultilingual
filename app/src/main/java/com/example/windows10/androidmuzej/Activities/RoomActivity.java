package com.example.windows10.androidmuzej.Activities;

import android.content.Intent;
import android.content.res.Resources;
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

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();

        String rNumber = intent.getStringExtra("roomNumber");
        int roomNumber = Integer.parseInt(rNumber.trim());

        Room room = new Room(roomNumber);

        getRoomLogo(room);
        setRoomLogo(room);

        getRoomTitle(room);
        setRoomTitle(room);

        getRoomPages(room);

        setPageCounter(room, currentPage);

        fillPage(room, currentPage);

        setPageNavigationButtons(room);

        setImageNavigationButtons();
    }

    private void getRoomLogo(Room room)
    {
        try {
            //Get name of logo from string.xml
            String logoString = "room"+room.getRoomNumber()+"Logo";
            int logoStringId = getResources().getIdentifier(logoString, "string", getPackageName());

            //Get drawable with name from string.xml
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
            String titleString = "room"+room.getRoomNumber()+"Title";
            int titleStringId = getResources().getIdentifier(titleString, "string", getPackageName());

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

    private void getRoomPages(Room room)
    {
        ArrayList<Page> pages = new ArrayList<>();

        int roomNumber = room.getRoomNumber();

        try {
            String pageText = "room"+roomNumber+"Text";
            int pagesTextId = getResources().getIdentifier(pageText, "array", getPackageName());
            String[] pagesText = getResources().getStringArray(pagesTextId);

            for(int i = 0; i < pagesText.length; i++)
            {
                int pageNumber = i + 1;

                Page page = new Page(pageNumber);
                getPageTitle(room, page);

                page.setText(pagesText[i]);

                getPageImages(room, page);

                getPageImageTitles(room, page);

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

            String pageTitle = "room"+roomNumber+"Page"+pageNumber+"Title";
            int pageTitleId = getResources().getIdentifier(pageTitle, "string", getPackageName());

            page.setTitle(getString(pageTitleId));

        } catch (Exception e) {
            Log.e("getPageTitle", "Page title not found.");
        }
    }

    private void getPageImages(Room room, Page page)
    {
        int roomNumber = room.getRoomNumber();

        int pageNumber = page.getPageNumber();

        String imageLocation = "room"+roomNumber+"Page"+pageNumber+"Images";
        Log.i("PageImageLocation", imageLocation);

        try {
            int pageImagesId = getResources().getIdentifier(imageLocation, "array", getPackageName());
            String[] pageImagesLocation = getResources().getStringArray(pageImagesId);

            //Adding images to page
            for (String location : pageImagesLocation) {
                //Get drawable id from string
                int drawableId = getResources().getIdentifier(location, "drawable", getPackageName());

                Drawable imageDrawable = getDrawable(drawableId);

                PageImage pageImage = new PageImage(imageDrawable);

                page.getPageImages().add(pageImage);
            }
        } catch (Resources.NotFoundException e) {
            Log.e("getPageImages", "Images not found.");
        }
    }

    private void getPageImageTitles(Room room, Page page)
    {
        int roomNumber = room.getRoomNumber();
        int pageNumber = page.getPageNumber();

        try {
            String imageTitleLocation = "room"+roomNumber+"Page"+pageNumber+"ImagesTitles";

            int pageImagesTitleId = getResources().getIdentifier(imageTitleLocation, "array", getPackageName());
            String[] pageImagesTitle = getResources().getStringArray(pageImagesTitleId);

            //Adding image titles to page images
            for(int j = 0; j < pageImagesTitle.length; j++)
            {
                String title = pageImagesTitle[j];
                page.getPageImages().get(j).setImageTitle(title);
            }
        } catch (Resources.NotFoundException e) {
            Log.e("getPageImageTitles", "Images title not found.");
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

    private void setImageNavigationButtons()
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
    }
}
