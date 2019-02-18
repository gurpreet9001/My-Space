package com.example.seeme.managers;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.example.seeme.ApplicationHelper;
import com.example.seeme.managers.listeners.OnDataChangedListener;
import com.example.seeme.managers.listeners.OnPostCreatedListener;
import com.example.seeme.model.Post;
import com.example.seeme.utils.LogUtil;

import java.io.File;

public class PostManager {

    private static final String TAG = PostManager.class.getSimpleName();
    private static PostManager instance;

    private Context context;

    public static PostManager getInstance(Context context) {
        if (instance == null) {
            instance = new PostManager(context);
        }

        return instance;
    }

    private PostManager(Context context) {
        this.context = context;
    }

    public void createOrUpdatePost(Post post) {
        try {
            ApplicationHelper.getDatabaseHelper().createOrUpdatePost(post);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void getPosts(OnDataChangedListener<Post> onDataChangedListener) {
        ApplicationHelper.getDatabaseHelper().getPostList(onDataChangedListener);
    }

    public void createPostWithImage(String filePath, final OnPostCreatedListener onPostCreatedListener, final Post post) {
        Uri localImageUri = Uri.fromFile(new File(filePath));
        // Register observers to listen for when the download is done or if it fails
        DatabaseHelper databaseHelper = ApplicationHelper.getDatabaseHelper();
        UploadTask uploadTask = databaseHelper.uploadImage(localImageUri);

        if (uploadTask != null) {
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    onPostCreatedListener.onPostCreated(false);

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                    LogUtil.logDebug(TAG, "successful upload image, image url: " + String.valueOf(downloadUrl));

                    post.setImagePath(String.valueOf(downloadUrl));
                    createOrUpdatePost(post);

                    onPostCreatedListener.onPostCreated(true);
                }
            });
        }
    }

}


