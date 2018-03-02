/*
 * Copyright (C) 2018 jompons.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jompon.handydialog.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jompon.handydialog.HandyDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        HandyDialog.OnDialogConfirmClickListener,
        HandyDialog.OnDialogCancelClickListener,
        HandyDialog.OnDialogItemClickListener,
        HandyDialog.OnDialogCheckListener {

    private Button btnSimple;
    private Button btnConfirm;
    private Button btnList;
    private Button btnMultiChoice;
    private Button btnGps;
    private Button btnPermission;
    private HandyDialog handyDialog;
    private String[] list;
    private boolean[] checkedItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
        bindingData();
    }

    private void bindingView( )
    {
        btnSimple = (Button) findViewById(R.id.btn_simple);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnList = (Button) findViewById(R.id.btn_list);
        btnMultiChoice = (Button) findViewById(R.id.btn_multi_choice);
        btnGps = (Button) findViewById(R.id.btn_gps);
        btnPermission = (Button) findViewById(R.id.btn_permission);
    }

    private void bindingData( )
    {
        list = getResources().getStringArray(R.array.list);
        checkedItems = new boolean[list.length];
        handyDialog = new HandyDialog(this);
        handyDialog.setOnDialogConfirmClickListener(this);
        handyDialog.setOnDialogCancelClickListener(this);
        handyDialog.setOnDialogItemClickListener(this);
        handyDialog.setOnDialogCheckListener(this);
        btnSimple.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnMultiChoice.setOnClickListener(this);
        btnGps.setOnClickListener(this);
        btnPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if( v == btnSimple ){
            handyDialog.alertSimpleDialog(btnSimple.getId(), "Simple Dialog", "Message", R.string.button_ok);
        }
        if( v == btnConfirm ){
            handyDialog.alertConfirmDialog(btnConfirm.getId(), "Confirm Dialog", "Message", R.string.button_ok, R.string.button_cancel);
        }
        if( v == btnList ){
            handyDialog.alertListDialog(btnList.getId(), "List Dialog", list);
        }
        if( v == btnMultiChoice ){
            handyDialog.alertMultiChoiceDialog(btnMultiChoice.getId(), "Multi Click Dialog", list, checkedItems, R.string.button_ok);
        }
        if( v == btnGps ){
            handyDialog.alertGPSDialog(android.R.drawable.ic_dialog_alert, "GPS Dialog", "Please open Gps", R.string.button_setting);
        }
        if( v == btnPermission ){
            handyDialog.alertPermissionDialog(android.R.drawable.ic_dialog_alert, "Permission Dialog", "Please allowed permission", R.string.button_setting);
        }
    }

    @Override
    public void onConfirm(long id) {

        if( id == btnSimple.getId() ){
            Toast.makeText(this, "Click Simple", Toast.LENGTH_LONG).show();
        }
        if( id == btnConfirm.getId() ){
            Toast.makeText(this, "Click Confirm!!", Toast.LENGTH_LONG).show();
        }
        if( id == btnMultiChoice.getId() ){
            Toast.makeText(this, "Click MultiChoice!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancel(long id) {

        if( id == btnConfirm.getId() ){
            Toast.makeText(this, "Click Cancel!!", Toast.LENGTH_LONG).show();
        }
        if( id == btnMultiChoice.getId() ){
            Toast.makeText(this, "MultiChoice Cancel!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItem(long id, int which) {

        if( id == btnList.getId() ){
            Toast.makeText(this, list[which], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onChecked(long id, int which, boolean isChecked) {

        if( id == btnMultiChoice.getId() ){
            Toast.makeText(this, list[which]+" = "+checkedItems[which], Toast.LENGTH_LONG).show();
        }
    }
}
