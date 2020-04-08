package com.QuickMatch.quickmatchv110;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Matching_Fragment.onFragmentBtnSelected {
    //MainFragment에 onFragmentBtnSelected를 상속 ,NavigationView.OnNavigationItemSelectedListener 이거때문에 메뉴 아이콘을 못 바꾸는듯?
    DrawerLayout drawerLayout;//

    ActionBarDrawerToggle actionBarDrawerToggle;

    Toolbar toolbar;//appcompat이랑 그냥이랑 뭐가 다른지 찾아보기
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffff33")); //제목의 칼라
        toolbar.setTitle(R.string.title);
        toolbar.setSubtitle(R.string.subtitle); //부제목 넣기
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);//
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        //분석
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, (R.string.open), (R.string.close));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //네비게이션바 부르는 아이콘 클릭시 모양이 바뀜


        //load default fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_contant_fragment, new Home_fragment());
        fragmentTransaction.commit();

        //기본 프레그먼트
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }//앱을 시작할때 옵션메뉴 생성(정렬, 세팅)*/


    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }//뒤로가기 버튼을 눌렀을때 Drawerlayout이 열려있다면 닫아줘라. 안열려있다면 뒤로가라
    //이 함수가 없으면 네비게이션이 열려 있어도 앱이 나가짐 이 함수가 있어야 네비게이션만 없어짐


    //----------------------------------네비게이션 버튼 클릭 fragment 화면전환 ----------------------------------------------
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {//아마 네비게이션에서 아이템을 선택할때인듯?
        drawerLayout.closeDrawer(GravityCompat.START);//아이템클릭을 하면 화면이 바뀌고 네비게이션 바가 들어감
        if (menuItem.getItemId() == R.id.btn_home) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_contant_fragment, new Home_fragment());
            fragmentTransaction.commit();

        }
        if (menuItem.getItemId() == R.id.btn_matching) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_contant_fragment, new Matching_Fragment());
            fragmentTransaction.commit();
        }

        if (menuItem.getItemId() == R.id.btn_team) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_contant_fragment, new Teampage_Fragment());
            fragmentTransaction.commit();
        }


        return false;
    }

    @Override
    public void onButtonSelected() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_contant_fragment, new Matching_mapCalendar());
        fragmentTransaction.commit();
    }
    //----------------------------------fragment를 이용한 화면전환 공간 끝----------------------------------------------


}