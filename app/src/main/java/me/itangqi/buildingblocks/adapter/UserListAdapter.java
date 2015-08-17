package me.itangqi.buildingblocks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.bean.User;
import me.itangqi.buildingblocks.data.RequestManager;
import me.itangqi.buildingblocks.gson.ZhuanLanApi;
import me.itangqi.buildingblocks.widget.CircleImageView;

/**
 * Created by tangqi on 7/11/15.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.CardViewHolder> {
    private List<User> mUserList;
    private Context mContext;

    public UserListAdapter(List<User> mUserList) {
        this.mUserList = mUserList;
        setHasStableIds(true);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_user_info, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(itemView);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        User user = mUserList.get(position);
        String id = user.getAuthor().getAvatar().getId();
        String picUrl = ZhuanLanApi.getAuthorAvatarUrl(user.getAuthor().getAvatar().getTemplate(),
                id, ZhuanLanApi.PIC_SIZE_XL);
        holder.imageView.setImageUrl(picUrl, RequestManager.getImageLoader());
        holder.name.setText(user.getName());
        holder.follower.setText(mContext.getString(R.string.user_list_follower, user.getFollowerCount()));
        holder.postCount.setText(mContext.getString(R.string.user_list_post_count, user.getPostCount()));
        holder.description.setText(user.getDescription());
    }

    public void add(User user) {
        mUserList.add(user);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.avatar) CircleImageView imageView;
        @Bind(R.id.tv_name) TextView name;
        @Bind(R.id.tv_follower) TextView follower;
        @Bind(R.id.tv_post_count) TextView postCount;
        @Bind(R.id.tv_description) TextView description;

        public CardViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // TODO do what you want
        }
    }
}