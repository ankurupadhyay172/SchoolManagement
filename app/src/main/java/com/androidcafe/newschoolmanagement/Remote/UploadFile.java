package com.androidcafe.newschoolmanagement.Remote;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.androidcafe.newschoolmanagement.Common.LoadingClass;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadFile {

    StorageReference storageReference;
    OnImageUpload onImageUpload;

    public UploadFile(OnImageUpload onImageUpload) {
        this.onImageUpload = onImageUpload;
    }

    public void uploadonfirestorage(Uri saveUri, Context context)
    {

        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        String id = UUID.randomUUID().toString();
        storageReference = FirebaseStorage.getInstance().getReference("notice_file").child("test");
        // Create name for image
        UploadTask uploadTask = storageReference.putFile(saveUri);

        //create Task


        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {

                   // Toast.makeText(Send_notice.this, "Failed", Toast.LENGTH_SHORT).show();
                    onImageUpload.getUrl(task.getException().getMessage());
                }

                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> task) {

                test.dismiss();

                if (task.isSuccessful()) {
                    String url = task.getResult().toString();
                    Log.d("PROFILE_DIRECTLINK", url);


                    onImageUpload.getUrl(url);


                }

            }
        });

    }



    public interface OnImageUpload
    {
        public void getUrl(String url);
    }

}
