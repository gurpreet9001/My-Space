package com.example.seeme.managers.listeners;
import java.util.List;

public interface OnDataChangedListener<T> {

    public void onListChanged(List<T> list);
}
