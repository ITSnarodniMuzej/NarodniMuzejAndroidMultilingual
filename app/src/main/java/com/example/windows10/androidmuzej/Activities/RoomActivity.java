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

    Room room;
    int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();

        String rNumber = intent.getStringExtra("roomNumber");
        int roomNumber = Integer.parseInt(rNumber.trim());

        room = new Room(roomNumber);

        getRoomLogo();
        setRoomLogo();

        getRoomTitle();
        setRoomTitle();

        getRoomPages();

        setPageCounter(currentPage);

        fillPage(currentPage);

        setPageNavigationButtons();
    }

    private void getRoomLogo()
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
            //Default logo
            room.setLogo(getDrawable(R.drawable.logo));
        }
    }

    private void setRoomLogo()
    {
        ImageView ivRoomLogo = findViewById(R.id.ivRoomLogo);
        ivRoomLogo.setImageDrawable(room.getLogo());
    }

    private void getRoomTitle()
    {
        try {
            String titleString = "room"+room.getRoomNumber()+"Title";
            int titleStringId = getResources().getIdentifier(titleString, "string", getPackageName());

            room.setTitle(getString(titleStringId));
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    private void setRoomTitle()
    {
        TextView tvRoomTitle = findViewById(R.id.tvRoomTitle);
        tvRoomTitle.setText(room.getTitle());
    }

    private void getRoomPages()
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

                getPageTitle(page);

                page.setText(pagesText[i]);

                ArrayList<PageImage> pageImages = new ArrayList<>();

                getPageImages(i, pageImages);

                getPageImageTitles(i, pageImages);

                page.setPageImages(pageImages);

                pages.add(page);
            }
        } catch (Resources.NotFoundException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

        room.setPages(pages);
    }

    private void getPageTitle(Page page)
    {
        try {
            int roomNumber = room.getRoomNumber();
            int pageNumber = page.getPageNumber();

            String pageTitle = "room"+roomNumber+"Page"+pageNumber+"Title";
            int pageTitleId = getResources().getIdentifier(pageTitle, "string", getPackageName());

            page.setTitle(getString(pageTitleId));

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    private void getPageImages(int pageNumber, ArrayList<PageImage> pageImages)
    {
        int roomNumber = room.getRoomNumber();

        pageNumber += 1;

        String imageLocation = "room"+roomNumber+"Page"+pageNumber+"Images";

        try {
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
        } catch (Resources.NotFoundException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    private void getPageImageTitles(int pageNumber, ArrayList<PageImage> pageImages)
    {
        int roomNumber = room.getRoomNumber();

        pageNumber += 1;

        try {
            String imageTitleLocation = "room"+roomNumber+"Page"+pageNumber+"ImagesTitles";

            int pageImagesTitleId = getResources().getIdentifier(imageTitleLocation, "array", getPackageName());
            String[] pageImagesTitle = getResources().getStringArray(pageImagesTitleId);

            //Adding image titles to page images
            for(int j = 0; j < pageImagesTitle.length; j++)
            {
                String title = pageImagesTitle[j];
                pageImages.get(j).setImageTitle(title);
            }
        } catch (Resources.NotFoundException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

    }

    private void setPageCounter(int currentPage)
    {
        TextView tvPageCounter = findViewById(R.id.tvRoomPageCount);

        int totalPages = room.getPages().size();
        String pagesCounter = currentPage+"/"+totalPages;

        tvPageCounter.setText(pagesCounter);
    }

    private void fillPage(int pageId)
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
        if(page.getTitle() == null)
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
}
