package com.example.seeme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.seeme.adapters.PostsAdapter;
import com.example.seeme.managers.PostManager;
import com.example.seeme.managers.listeners.OnDataChangedListener;
import com.example.seeme.model.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView postsListView;
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postsListView = (ListView)findViewById(R.id.postsListView);
        postsAdapter = new PostsAdapter();
        postsListView.setAdapter(postsAdapter);

        OnDataChangedListener<Post> onPostsDataChangedListener = new OnDataChangedListener<Post>() {
            @Override
            public void onListChanged(List<Post> list) {
                postsAdapter.setList(list);
            }
        };

        PostManager.getInstance(getApplicationContext()).getPosts(onPostsDataChangedListener);

    }
}