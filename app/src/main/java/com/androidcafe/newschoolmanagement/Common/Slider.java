package com.androidcafe.newschoolmanagement.Common;

import android.content.Context;
import android.graphics.Color;


import com.androidcafe.newschoolmanagement.Adapters.PagerAdapter;
import com.androidcafe.newschoolmanagement.Model.Banner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Slider {



    Context context;

    BannerInterface bannerInterface;

    public Slider(Context context,BannerInterface bannerInterface) {
        this.context = context;
        this.bannerInterface = bannerInterface;
    }

    CollectionReference bannerRef;
    public  List<Banner> slider = new ArrayList<>();


    public List<Banner> getSlider() {
        return slider;
    }

    public void setSlider(List<Banner> slider) {
        this.slider = slider;
    }

    public void initFirestore()
    {
        bannerRef = FirebaseFirestore.getInstance().collection("Banner");
        final List<Banner> banners = new ArrayList<>();




        bannerRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {

                       if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot bannerSnapShot:task.getResult() )
                            {
                                Banner banner = bannerSnapShot.toObject(Banner.class);
                                banners.add(banner);
                            }


                            bannerInterface.getBanner(banners);

                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {


            }
        });




    }





    public void setBanner(SliderView sliderView,List<Banner> banners)
    {



        PagerAdapter adapter = new PagerAdapter(context,banners);
        sliderView.setSliderAdapter(adapter);


        sliderView.setPadding(10,10,10,10);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();




    }





    public interface  BannerInterface
    {
        public void getBanner(List<Banner> banners);
    }


}
