package me.ricky.ospanalyze.textview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.ricky.ospanalyze.R;

public class TextViewActivity extends AppCompatActivity {
    private static final String TAG = "TextViewActivity";

    private Context mContext;

    private TextView mTvHtml;
    private TextView mTvSpanned;
    private TextView mTvSpannable;
    private TextView mTvSpannableStringBuilder;
    private TextView mTvBulletSpan;
    private TextView mTvCustomBulletSpan;
    private TextView mTvClickableSpan;
    private TextView mTvImageSpan;
    private TextView mTvFriendLikeSpan;
    private TextView mTvRelativeSizeSpan;
    private TextView mTvSubscriptSpan;
    private TextView mTvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        mContext = this;

        mTvDrawable = findViewById(R.id.tv_drawable);

        mTvHtml = findViewById(R.id.tv_html);
        mTvSpanned = findViewById(R.id.tv_spanned);
        mTvSpannable = findViewById(R.id.tv_spannable);
        mTvSpannableStringBuilder = findViewById(R.id.tv_spannable_string_builder);
        mTvBulletSpan = findViewById(R.id.tv_bullet_span);
        mTvCustomBulletSpan = findViewById(R.id.tv_custom_bullet_span);
        mTvClickableSpan = findViewById(R.id.tv_clickable_span);
        mTvImageSpan = findViewById(R.id.tv_image_span);
        mTvFriendLikeSpan = findViewById(R.id.tv_friend_like_span);
        mTvRelativeSizeSpan = findViewById(R.id.tv_relative_size_span);
        mTvSubscriptSpan = findViewById(R.id.tv_subscript_span);

        setDrawables();
        setDrawables1();

        setHtmlSpan();

        setSpanned();
        setSpannable();
        setSpannableStringBuilder();

        setBulletSpans();
        setCustomBulletSpans();
        setClickableSpans();
        setImageSpan();
        setFriendLikeSpan();
        setRelativeSizeSpan();
        setSubscriptSpan();
    }

    private void setCustomBulletSpans() {
        String text = "我想说两句\n";
        int gapWidth = 10;
        int color = Color.RED;
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        String first = "第一句\n";
        builder.append(first, new BulletPointSpan(gapWidth, color), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        String second = "第二句";
        builder.append(second, new BulletPointSpan(gapWidth, color), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvCustomBulletSpan.setText(builder);
    }

    private void setDrawables1() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_round);
        // 必须设置drawable的位置和大小
        drawable.setBounds(0, 0, 100, 100);
        // 如果没有要设置的drawable,可以传 null
        mTvDrawable.setCompoundDrawables(drawable, drawable, drawable, null);
    }

    private void setDrawables() {
        // 这个数组表示 左，上，右，下 四个方向的图标，如果没有图标，则会返回空
        Drawable[] drawables = mTvDrawable.getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        // 通过setBounds 指定图标的位置和大小
        leftDrawable.setBounds(50, 50, 100, 100);
    }

    private void setSubscriptSpan() {
        String text = "下面是水的分子式:\nH";
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        builder.append("2", new SubscriptSpan(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("O");
        mTvSubscriptSpan.setText(builder);
    }

    private void setRelativeSizeSpan() {
        String text = "下面是重点:\n";
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        builder.append("重点一\n", new RelativeSizeSpan(1.5f), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("重点二\n", new RelativeSizeSpan(1.5f), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("重点三\n", new RelativeSizeSpan(0.5f), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvRelativeSizeSpan.setText(builder);
    }

    private void setFriendLikeSpan() {
        String[] friends = {"Tom", "Jack", "Ricky", "Tony", "Robin", "周冬雨", "薇薇", "程序亦非猿", "Mr.S", "StarkSong", "Larry"};

        // 添加心形
        String imagePlaceholder = "[heart]";
        SpannableStringBuilder builder = new SpannableStringBuilder(imagePlaceholder);
        // 我们这里添加心形，有些大，所以通过 setBounds 对心形进行控制
        Drawable heart = getDrawable(R.drawable.icon_favorite);
        heart.setBounds(0, 0, 45, 45);
        ImageSpan imageSpan = new ImageSpan(heart);
        builder.setSpan(imageSpan, 0, imagePlaceholder.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int len = friends.length;
        for (int i = 0; i < len; i++) {
            String friend = friends[i];

            if (!TextUtils.isEmpty(friend)) {
                if (i < len - 1) {
                    friend += "，";
                }
                builder.append(friend, new FriendClickSpan(friends[i]),
                               Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        // 设置文字颜色为 蓝色
        mTvFriendLikeSpan.setTextColor(Color.BLUE);
        // 设置高亮显示颜色,这里设置透明色
        mTvFriendLikeSpan.setLinkTextColor(Color.BLUE);
        // 设置高亮显示颜色
        mTvFriendLikeSpan.setHighlightColor(Color.TRANSPARENT);
        mTvFriendLikeSpan.setMovementMethod(LinkMovementMethod.getInstance());
        mTvFriendLikeSpan.setText(builder);
    }

    private void setImageSpan() {
        String imagePlaceholder = "[heart]";
        SpannableStringBuilder builder = new SpannableStringBuilder(imagePlaceholder);
        ImageSpan imageSpan = new ImageSpan(mContext, R.drawable.icon_favorite);
        builder.setSpan(imageSpan, 0, imagePlaceholder.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvImageSpan.setText(builder);
    }

    // 添加心形
    //    String imagePlaceholder = "[heart]";
    //    SpannableStringBuilder builder = new SpannableStringBuilder(imagePlaceholder);
    //    ImageSpan imageSpan = new ImageSpan(mContext,R.drawable.icon_favorite);
    //        builder.setSpan(imageSpan, 0, imagePlaceholder.length(),
    //    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    private void setClickableSpans() {
        String[] friends = {"Tom", "Jack", "Ricky", "Tony", "Robin", "周冬雨", "薇薇", "程序亦非猿", "Mr.S", "StarkSong", "Larry"};

        SpannableStringBuilder builder = new SpannableStringBuilder();
        int len = friends.length;
        for (int i = 0; i < len; i++) {
            String friend = friends[i];

            if (!TextUtils.isEmpty(friend)) {
                if (i < len - 1) {
                    friend += "，";
                }
                builder.append(friend, new FriendClickSpan(friends[i]),
                               Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        // 设置文字颜色为 蓝色
        mTvClickableSpan.setTextColor(Color.BLUE);
        // 设置链接颜色
        mTvClickableSpan.setLinkTextColor(Color.BLUE);
        // 设置高亮显示颜色,这里设置透明色
        mTvClickableSpan.setHighlightColor(Color.TRANSPARENT);
        mTvClickableSpan.setMovementMethod(LinkMovementMethod.getInstance());
        mTvClickableSpan.setText(builder);

    }

    /**
     * ClickableSpan 为抽象类，所以我们需要先实现它未未实现的方法。
     */
    private class FriendClickSpan extends ClickableSpan {

        private String mFriend;

        public FriendClickSpan(String friend) {
            mFriend = friend;
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setUnderlineText(false);
        }

        /**
         * 点击指定Span执行的操作，在这里实现。
         *
         * @param widget TextView
         */
        @Override
        public void onClick(@NonNull View widget) {
            if (TextUtils.isEmpty(mFriend)) {
                return;
            }

            Toast
                    .makeText(mContext, mFriend, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void setSpanned() {
        String text = "我想说两句第一句第二句第三句";
        SpannedString spannedString = new SpannedString(text);
        mTvSpanned.setText(spannedString.subSequence(1, 3));
    }

    private void setSpannable() {
        String text = "我想说两句第一句第二句第三句";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 5,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new BackgroundColorSpan(Color.GREEN), 3, 7,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvSpannable.setText(spannableString);
    }

    private void setSpannableStringBuilder() {
        String text = "我想说两句第一句第二句第三句";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.GREEN), 5, 8,
                                       Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.insert(5, "insert");
        // 当使用 insert 插入内容后，标记的点就转移，结束点后会后移，所以我们如果要在结束点插入内容，也需要需要相应的后移
        // 后移距离为 insert 的长度，即为 6 + 8 = 14
        spannableStringBuilder.insert(14, "insert");
        mTvSpannableStringBuilder.setText(spannableStringBuilder);
    }

    private void setBulletSpans() {
        //        String lineFeed = "\n";
        //        String text = "我想说两句\n第一句\n第二句\n第三句";
        //        int start = text.indexOf(lineFeed) + 1;
        //        int end = start + 3;
        //        SpannableString spannableStringBuilder = new SpannableString(text);
        //        spannableStringBuilder.setSpan( new BulletSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        start = end + 1;
        //        end = start + 3;
        //        spannableStringBuilder.setSpan( new BulletSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        mTvBulletSpan.setText(spannableStringBuilder);

        String text = "我想说两句\n";
        int gapWidth = 10;
        int color = Color.RED;
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        String first = "第一句\n";
        builder.append(first, new BulletSpan(gapWidth, color), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        String second = "第二句";
        builder.append(second, new BulletSpan(gapWidth, color), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvBulletSpan.setText(builder);
    }

    private void setHtmlSpan() {
        String text = "<big><big>我想说两句</big></big> <font color='red'>第一句</font> <br> <font color='blue'>第二句</font> <br> <strong>第三句</strong>";

        text = "<blockquote>\n" + "Here is a long quotation here is a long quotation here is a long quotation \n" + "here is a long quotation here is a long quotation here is a long quotation \n" + "here is a long quotation here is a long quotation here is a long quotation.\n" + "</blockquote>";

        text = "<cite>参看文献</cite>";

        text = "<dfn>一个定义项目</dfn>";

        mTvHtml.setText(Html.fromHtml(text));
    }
}
