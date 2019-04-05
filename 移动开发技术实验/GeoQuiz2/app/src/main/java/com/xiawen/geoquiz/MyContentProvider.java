package com.xiawen.geoquiz;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;

public class MyContentProvider extends ContentProvider {
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    private static final int SUCCESS = 1;
    private QuizDBOpenHelper helper;
    static {
        mUriMatcher.addURI("com.xiawen.geoquiz","info",SUCCESS);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        helper = new QuizDBOpenHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //select
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int code = mUriMatcher.match(uri);
        if(code == SUCCESS){
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query("info",projection,selection,selectionArgs,null,null,sortOrder);
        }else {
            throw new IllegalArgumentException("路径错误");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
