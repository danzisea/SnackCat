package com.wcc.snackcat.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wcc.snackcat.R;
import com.wcc.snackcat.fragment.DummyFragment;
import com.wcc.snackcat.helper.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context mContext = this;//初始化上下文环境
    private Button btn_main_home;//主页
    private Button btn_main_hot_sale;//热卖
    private Button btn_main_topic;//专题
    private Button btn_main_person;//个人
    private FragmentManager manager=null;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用初始化控件的方法
        initView();
        //调用初始化数据的方法
        initData();
        //调用初始化标签的方法
        initTabs();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 自定义方法集：初始化控件、数据、底部标签，点击返回键提示
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 初始化控件
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData() {
        //创建碎片管理器
        manager=getSupportFragmentManager();
        for (int i = 0; i <4; i++) {
            //动态创建碎片
            fragments.add(DummyFragment.newInstance(i));
        }
        //将创建的碎片替换掉和布局的FrameLayout
        FragmentHelper.replaceFragment(manager,fragments,0,R.id.layout_container,0,0);
    }

    /**
     * 初始化底部标签
     */
    private void initTabs() {
        //初始化RadioButton
        btn_main_home = (Button) findViewById(R.id.btn_main_home);
        btn_main_hot_sale = (Button) findViewById(R.id.btn_main_hot_sale);
        btn_main_topic = (Button) findViewById(R.id.btn_main_topic);
        btn_main_person = (Button) findViewById(R.id.btn_main_person);
        //设置第一个Button默认选中
        btn_main_home.setSelected(true);
        //按钮的点击事件
        btn_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主页面推荐碎片
                FragmentHelper.switchFragment(manager, fragments, 0, R.id.layout_container,
                        0, 0);
                btn_main_home.setSelected(true);
                btn_main_hot_sale.setSelected(false);
                btn_main_topic.setSelected(false);
                btn_main_person.setSelected(false);
            }
        });
        btn_main_hot_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主页面书架碎片
                FragmentHelper.switchFragment(manager, fragments, 1, R.id.layout_container,
                        0, 0);
                btn_main_hot_sale.setSelected(true);
                btn_main_topic.setSelected(false);
                btn_main_home.setSelected(false);
                btn_main_person.setSelected(false);
            }
        });
        btn_main_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主页面分类碎片
                FragmentHelper.switchFragment(manager, fragments, 2, R.id.layout_container,
                        0, 0);
                btn_main_topic.setSelected(true);
                btn_main_home.setSelected(false);
                btn_main_hot_sale.setSelected(false);
                btn_main_person.setSelected(false);
            }
        });
        btn_main_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主页面更多碎片
                FragmentHelper.switchFragment(manager, fragments, 3, R.id.layout_container,
                        0, 0);
                btn_main_person.setSelected(true);
                btn_main_topic.setSelected(false);
                btn_main_home.setSelected(false);
                btn_main_hot_sale.setSelected(false);
            }
        });
    }

    /**
     * 点击键盘返回键，弹出对话框
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗?");
            // 添加选择按钮并注册监听
            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            isExit.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // 显示对话框
            isExit.show();

        }
        return false;
    }
}
