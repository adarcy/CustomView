package com.lk.syxl.customview.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.syxl.customview.R;
import com.lk.syxl.customview.model.Phone;
import com.lk.syxl.customview.mvp.NumberBelongView;
import com.lk.syxl.customview.mvp.impl.NumberBelongPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by likun on 2017/10/11.
 */

public class NumberBelongActivity extends AppCompatActivity implements NumberBelongView {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_check)
    Button btCheck;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_carrier)
    TextView tvCarrier;
    NumberBelongPresenter numberBelongPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_belong);
        ButterKnife.bind(this);

        numberBelongPresenter = new NumberBelongPresenter(this);
        numberBelongPresenter.onAttach(this);
    }

    @OnClick(R.id.bt_check)
    public void onViewClicked() {
        numberBelongPresenter.searchPhoneInfo(etInput.getText().toString());
    }

    @Override
    public void showLoading() {
        if (progressDialog == null){
            progressDialog = ProgressDialog.show(this,"","正在加载");
        }
        progressDialog.show();
    }

    @Override
    public void closeLoading() {
        if (progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateView() {
        Phone phoneInfo = numberBelongPresenter.getPhoneInfo();
        tvPhone.setText("手机号码："+phoneInfo.getTelString());
        tvProvince.setText("省份："+phoneInfo.getProvince());
        tvType.setText("运营商："+phoneInfo.getCatName());
        tvCarrier.setText("归属地："+phoneInfo.getCarrier());
    }
}
