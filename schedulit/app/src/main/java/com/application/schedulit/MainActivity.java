package com.application.schedulit;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.location.LocationServices;

import com.application.schedulit.consts.Constants;
import com.application.schedulit.recivers.AddressResultReceiver;
import com.application.schedulit.services.FetchAddressIntentService;
import com.application.schedulit.services.GcmSchedulitService;
import com.application.schedulit.ui.ItemOffsetDecoration;
import com.application.schedulit.ui.SchedulitContactItem;
import com.application.schedulit.ui.SchedulitItem;
import com.application.schedulit.ui.SchedulitRecyclerAdapter;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private List<SchedulitItem> feedsList;
    private RecyclerView mRecyclerView;
    private SchedulitRecyclerAdapter adapter;
    private AddressResultReceiver mResultReceiver;

    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;

    private static final int GPS_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (isGoogleApiAvailability() == false)
            return;

        mResultReceiver = new AddressResultReceiver(this, new Handler());
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        SchedulitItem sitem = new SchedulitItem();
        sitem.setMsgText("hi bye");
        List<SchedulitContactItem> contactItem1s = new ArrayList<>();
        SchedulitContactItem contact1 = new SchedulitContactItem();
        contact1.setPhone("0533402673");
        contactItem1s.add(contact1);
        sitem.setContacts(contactItem1s);
        sitem.setMsgText("hi, this is test from schedulit");
        sitem.setInterval(60);
        //GcmSchedulitService.scheduleRepeat(getBaseContext(), sitem);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchedulitItem item = new SchedulitItem();
                item.setType(SchedulitItem.Type.Edit);
                adapter.AddItem(item);
            }
        });
        feedsList = new ArrayList<SchedulitItem>();
        SchedulitItem item = new SchedulitItem();
        item.setMsgText("hi bye");
        List<SchedulitContactItem> contactItems = new ArrayList<>();
        SchedulitContactItem contact = new SchedulitContactItem();
        contact.setPhone("0533402673");
        contact.setName("בילי ונועה");
        contactItems.add(contact);
        item.setContacts(contactItems);

        item.setType(SchedulitItem.Type.View);

       /* SchedulitItem item3= new SchedulitItem();
        item3.setMsgText("hi bye");
        item3.setPhone("0533402673");
        item3.setContactName("בילי הגאונה");
        item3.setActive(true);
        item3.setType(SchedulitItem.Type.View);*/

        SchedulitItem item2 = new SchedulitItem();


        //feedsList.add(item2);
        feedsList.add(item);
        //feedsList.add(item3);

        adapter = new SchedulitRecyclerAdapter(getBaseContext(), feedsList);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);
        setRecyclerViewAnimation();

    }

    protected void setRecyclerViewAnimation() {
        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                // Do Stuff


            }

        });
        swipeToDismissTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    @Override
    public void onConnected(Bundle bundle) {
// Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, R.string.no_geocoder_available, Toast.LENGTH_LONG).show();
                return;
            }
            // It is possible that the user presses the button to get the address before the
            // GoogleApiClient object successfully connects. In such a case, mAddressRequested
            // is set to true, but no attempt is made to fetch the address (see
            // fetchAddressButtonHandler()) . Instead, we start the intent service here if the
            // user has requested an address, since we now have a connection to GoogleApiClient.
            /*if (mAddressRequested) {
                startIntentService();
            }*/
            startIntentService();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onDestroy() {

        GcmSchedulitService.cancelRepeat(this);
        super.onDestroy();

    }

    protected boolean isGoogleApiAvailability() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int errorCheck = api.isGooglePlayServicesAvailable(this);
        if (errorCheck == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(errorCheck)) {
            //GPS_REQUEST_CODE = 1000, and is used in onActivityResult
            api.showErrorDialogFragment(this, errorCheck, GPS_REQUEST_CODE);

            return false;
        } else {

            return false;
        }
    }
}
