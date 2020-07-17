package com.androidcafe.newschoolmanagement.Common;

import android.Manifest;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;



public class PdfViewer extends AppCompatActivity {

    PDFView pdfView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener(){


                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();



        pdfView = (PDFView)findViewById(R.id.pdf_viewer);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);


        Intent intent = getIntent();


        if(intent.hasExtra("url"))
        {


            loadfromInternet(intent.getStringExtra("url"));





        }





        //viewOffline();




    }

    private void viewOffline() {


        pdfView.fromAsset("a.pdf")
                .password(null)
                .defaultPage(0)
                .enableSwipe(false)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                }).onDrawAll(new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

            }
        }).onPageError(new OnPageErrorListener() {
            @Override
            public void onPageError(int page, Throwable t) {
                Toast.makeText(PdfViewer.this, "Error while open page"+page, Toast.LENGTH_SHORT).show();
            }
        }).onPageChange(new OnPageChangeListener() {
            @Override
            public void onPageChanged(int page, int pageCount) {

            }
        }).onTap(new OnTapListener() {
            @Override
            public boolean onTap(MotionEvent e) {
                return true;
            }
        }).onRender(new OnRenderListener() {
            @Override
            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                pdfView.fitToWidth();
            }
        })
                .enableAnnotationRendering(true)
                .invalidPageColor(Color.WHITE)
                .load();

    }

    private void loadfromInternet(String url) {



        progressBar.setVisibility(View.VISIBLE);    //show progress bar

        FileLoader.with(this)
                .load(url)
                .fromDirectory("Download", FileLoader.DIR_EXTERNAL_PUBLIC)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {

                        progressBar.setVisibility(View.GONE);

                        // Toast.makeText(PdfViewertest.this, "Load file Online", Toast.LENGTH_SHORT).show();
                        File pdfFile = response.getBody();



                        pdfView.fromFile(pdfFile)
                                .password(null)
                                .defaultPage(0)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .onDraw(new OnDrawListener() {
                                    @Override
                                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                    }
                                }).onDrawAll(new OnDrawListener() {
                            @Override
                            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                            }
                        }).onPageError(new OnPageErrorListener() {
                            @Override
                            public void onPageError(int page, Throwable t) {

                                Toast.makeText(PdfViewer.this, "Error while open page "+page, Toast.LENGTH_SHORT).show();
                            }
                        }).onPageChange(new OnPageChangeListener() {
                            @Override
                            public void onPageChanged(int page, int pageCount) {

                            }
                        }).onTap(new OnTapListener() {
                            @Override
                            public boolean onTap(MotionEvent e) {
                                return true;
                            }
                        }).onRender(new OnRenderListener() {
                            @Override
                            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                pdfView.fitToWidth();   //fix screen size


                            }
                        }).enableAnnotationRendering(true)
                                .invalidPageColor(Color.WHITE)
                                .load();



                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {


                        Toast.makeText(PdfViewer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);













                    }
                });

    }
}

