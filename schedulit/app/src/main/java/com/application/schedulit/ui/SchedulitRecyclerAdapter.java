package com.application.schedulit.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ex.chips.BaseRecipientAdapter;
import com.android.ex.chips.RecipientEditTextView;
import com.android.ex.chips.recipientchip.DrawableRecipientChip;
import com.application.schedulit.R;
import com.application.schedulit.services.ContactsService;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yosinoa on 21/06/2016.
 */
public class SchedulitRecyclerAdapter extends RecyclerView.Adapter<SchedulitRecyclerAdapter.ViewHolder>  {
    private List<SchedulitItem> feedItemList;
    private Context mContext;

    public static final int VIEW_MODE = 1;
    public static final int EDIT_MODE = 2;

    public SchedulitRecyclerAdapter(Context context, List<SchedulitItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class ListViewHolder extends ViewHolder {

        protected TextView contactName;
        protected ImageView iconActive;

        public ListViewHolder(View view) {
            super(view);

            this.contactName = (TextView) view.findViewById(R.id.contact_name);
            this.iconActive = (ImageView) view.findViewById(R.id.icon_active);

        }
    }

    public class EditViewHolder extends ViewHolder {

        protected TextView contactName;
        protected TextView textView2;
        protected Button button;
        protected RecipientEditTextView recipientEditTextView;

        public EditViewHolder(View view) {
            super(view);

            this.button = (Button)view.findViewById(R.id.btnsave);
            this.recipientEditTextView = (RecipientEditTextView)view.findViewById(R.id.recipentEditTextView);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if(viewType == VIEW_MODE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card, null);
            return new ListViewHolder(view);
        }
        else
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edit_card, null);
            return new EditViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        if(viewHolder.getItemViewType()== VIEW_MODE)
        {
            SchedulitItem feedItem = (SchedulitItem)feedItemList.get(position);
            ListViewHolder holder = (ListViewHolder) viewHolder;

            List<String> names = new ArrayList<>();
            for(SchedulitContactItem contact :feedItem.getContacts())
            {
                names.add(contact.getName());
            }

            holder.contactName.setText(TextUtils.join(System.getProperty("line.separator"), names));
            Drawable myDrawable;
            if(feedItem.getActive()==true)
            {
                myDrawable = mContext.getResources().getDrawable(R.drawable.placeholder_active);

            }
            else
            {
                myDrawable = mContext.getResources().getDrawable(R.drawable.placeholder);
            }
            holder.iconActive.setImageDrawable(myDrawable);
        }
        else
        {
            SchedulitItem feedItem = (SchedulitItem)feedItemList.get(position);
            final EditViewHolder holder = (EditViewHolder)viewHolder;
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecipientEditTextView r = holder.recipientEditTextView;
                    DrawableRecipientChip[] chips = r.getSortedRecipients();
                    for (DrawableRecipientChip chip : chips) {
                        Log.v("DrawableChip", chip.getEntry().getDisplayName() + " " + chip.getEntry().getDestination());
                        Toast.makeText (mContext, "Name: " + chip.getEntry().getDisplayName()
                                + ", Phone No: " + chip.getEntry().getDestination(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.recipientEditTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            holder.recipientEditTextView.setAdapter(new BaseRecipientAdapter(BaseRecipientAdapter.QUERY_TYPE_PHONE,mContext));
        }

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }



    @Override
    public int getItemViewType(int position) {

        if(feedItemList.get(position).getType()== SchedulitItem.Type.View)
        {
            return VIEW_MODE;
        }
        else
        {
            return EDIT_MODE;
        }
    }


    public  void AddItem(SchedulitItem item)
    {
        feedItemList.add(0,item);
        this.notifyItemInserted(0);
    }


}


