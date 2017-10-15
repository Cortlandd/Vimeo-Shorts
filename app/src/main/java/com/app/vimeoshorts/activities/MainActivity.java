package com.app.vimeoshorts.activities;

/* Android imports*/
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* App imports */

/* Vimeo Imports */
import com.app.vimeoshorts.R;
import com.app.vimeoshorts.VimeoShorts;
import com.app.vimeoshorts.adapter.VideoAdapter;
import com.app.vimeoshorts.classes.VideoRecyclerView;
import com.app.vimeoshorts.event.VideoInfoReceivedEvent;
import com.app.vimeoshorts.fragments.MainFragment;
import com.app.vimeoshorts.task.GetVimeoVideosTask;

public class MainActivity extends AppCompatActivity {

//    // region Listeners
//    private NavigationView.OnNavigationItemSelectedListener mNavigationViewOnNavigationItemSelectedListener
//            = new NavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(MenuItem menuItem) {
//            drawerLayout.closeDrawers();
//
//            String title = menuItem.getTitle().toString();
//            switch (title) {
//                case WATCH_NOW:
//                    if(!menuItem.isChecked()){
//                        menuItem.setChecked(true);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                                .replace(R.id.content_fl, WatchNowFragment.newInstance(), "")
//                                .commit();
//                    }
//                    break;
//                case LIKES:
//                    if(!menuItem.isChecked()) {
//                        menuItem.setChecked(true);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                                .replace(R.id.content_fl, LikedVideosFragment.newInstance(), "")
//                                .commit();
//                    }
//                    break;
//                case WATCH_LATER:
//                    if(!menuItem.isChecked()) {
//                        menuItem.setChecked(true);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                                .replace(R.id.content_fl, WatchLaterVideosFragment.newInstance(), "")
//                                .commit();
//                    }
//                    break;
//                case EXPLORE:
//                    if(!menuItem.isChecked()) {
//                        menuItem.setChecked(true);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                                .replace(R.id.content_fl, ExploreFragment.newInstance(), "")
//                                .commit();
//                    }
//                    break;
////                        case SETTINGS:
////                            getSupportFragmentManager()
////                                    .beginTransaction()
////                                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
////                                    .replace(R.id.content_fl, PlaceholderFragment.newInstance(), "")
////                                    .commit();
////                            break;
//                case HELP_AND_FEEDBACK:
//                    try {
//                        startActivity(EmailUtility.getEmailIntent(MainActivity.this));
//                    } catch (android.content.ActivityNotFoundException ex) {
//                        Snackbar.make(findViewById(android.R.id.content),
//                                TrestleUtility.getFormattedText("There are no email apps installed on your device", font, 16),
//                                Snackbar.LENGTH_LONG)
//                                .show();
//                    }
//                    break;
//                case LOGOUT:
//                    LoopPrefs.signOut(MainActivity.this);
//                    startActivity(new Intent(MainActivity.this, VimeoShorts.class));
//                default:
//                    break;
//            }
//            return true;
//        }
//    };
//    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assign view for recycler view
        //recyclerView = (VideoRecyclerView)findViewById(R.id.recycler_view);
        // TODO: Create comment here
        //refreshLayout = (SwipeRefreshLayout)findViewById(R.id.video_list_refresh);

        Toast.makeText(this, "Loading Content..", Toast.LENGTH_SHORT).show();

        showFragment(MainFragment.class);
    }


    private void showFragment(Class fragmentClass) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }
}
