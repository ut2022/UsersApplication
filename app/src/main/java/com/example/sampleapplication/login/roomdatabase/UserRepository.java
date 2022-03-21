package com.example.sampleapplication.login.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    public UserDao userDao;
    public List<UserEntity> getAllPosts;
    private UserDatabase userDatabase;

    public UserRepository(Application application){
        userDatabase=UserDatabase.getInstance(application);
        userDao= userDatabase.userDao();
    }
    public List<UserEntity> getAllPosts(){
        return userDao.getValues();
    }

    public void InsertTask(final UserEntity userEntity)
    {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                userDao.insert(userEntity);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Log.d("data","database created");
            }
        }.execute();
    }

    public List<UserEntity> GetTask(){
        try {
            return new GetUsersAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<UserEntity>>
    {
        @Override
        protected List<UserEntity> doInBackground(Void... url) {
            return userDao.getValues();
        }
    }
}
