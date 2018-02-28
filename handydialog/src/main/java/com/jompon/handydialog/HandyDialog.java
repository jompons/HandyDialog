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

package com.jompon.handydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

public class HandyDialog {

    private Context context;
    private AlertDialog.Builder simple;
    private AlertDialog.Builder confirm;
    private AlertDialog.Builder list;
    private AlertDialog.Builder multiChoice;
    private AlertDialog.Builder gps;
    private AlertDialog.Builder permission;
    private OnDialogSimpleClickListener onDialogSimpleClickListener;
    private OnDialogConfirmClickListener onDialogConfirmClickListener;
    private OnDialogCancelClickListener onDialogCancelClickListener;
    private OnDialogItemClickListener onDialogItemClickListener;
    private OnDialogMultiChoiceListener onDialogMultiChoiceListener;

    public interface OnDialogSimpleClickListener{

        void onSimple(int id);
    }

    public interface OnDialogConfirmClickListener{

        void onConfirm(int id);
    }

    public interface OnDialogCancelClickListener{

        void onCancel(int id);
    }

    public interface  OnDialogItemClickListener{

        void onItem(int id, int which);
    }

    public interface  OnDialogMultiChoiceListener{

        void onChecked(int id, int which, boolean isChecked);
    }

    public void setOnDialogSimpleClickListener(OnDialogSimpleClickListener onDialogSimpleClickListener)
    {
        this.onDialogSimpleClickListener = onDialogSimpleClickListener;
    }

    public void setOnDialogConfirmClickListener(OnDialogConfirmClickListener onDialogConfirmClickListener)
    {
        this.onDialogConfirmClickListener = onDialogConfirmClickListener;
    }

    public void setOnDialogCancelClickListener(OnDialogCancelClickListener onDialogCancelClickListener)
    {
        this.onDialogCancelClickListener = onDialogCancelClickListener;
    }

    public void setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener)
    {
        this.onDialogItemClickListener = onDialogItemClickListener;
    }

    public void setOnDialogMultiChoiceListener(OnDialogMultiChoiceListener onDialogMultiChoiceListener)
    {
        this.onDialogMultiChoiceListener = onDialogMultiChoiceListener;
    }

    public HandyDialog(Context context)
    {
        this.context = context;
    }

    public void alertSimpleDialog(int resIcon, String title, String message, int positiveButton)
    {
        if( simple != null )      return;

        simple = new AlertDialog.Builder(context);
        simple.setIcon(resIcon);
        simple.setTitle(title);
        simple.setMessage(message);
        simple.setCancelable(false);
        simple.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        simple.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                simple = null;
            }
        });
        AlertDialog dialog = simple.create();
        dialog.show();
    }

    public void alertSimpleDialog(final int id, int resIcon, String title, String message, int positiveButton)
    {
        if( simple != null )      return;

        simple = new AlertDialog.Builder(context);
        simple.setIcon(resIcon);
        simple.setTitle(title);
        simple.setMessage(message);
        simple.setCancelable(false);
        simple.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (onDialogSimpleClickListener != null)
                    onDialogSimpleClickListener.onSimple(id);
            }
        });
        simple.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                simple = null;
            }
        });
        AlertDialog dialog = simple.create();
        dialog.show();
    }

    public void alertConfirmDialog(final int id, int resIcon, String title, String message, int positiveButton, int negativeButton)
    {
        if( confirm != null )      return;

        confirm = new AlertDialog.Builder(context);
        confirm.setIcon(resIcon);
        confirm.setTitle(title);
        confirm.setMessage(message);
        confirm.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (onDialogConfirmClickListener != null)
                    onDialogConfirmClickListener.onConfirm(id);
            }
        });
        confirm.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (onDialogCancelClickListener != null)
                    onDialogCancelClickListener.onCancel(id);
            }
        });
        confirm.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                confirm = null;
            }
        });
        AlertDialog dialog = confirm.create();
        dialog.show();
    }

    public void alertListDialog(final int id, int resIcon, String title, String[] items)
    {
        if( list != null )         return;

        list = new AlertDialog.Builder(context);
        list.setIcon(resIcon);
        list.setTitle(title);
        list.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if( onDialogItemClickListener != null )
                    onDialogItemClickListener.onItem(id, which);
            }
        });
        list.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                list = null;
            }
        });
        AlertDialog dialog = list.create();
        dialog.show();
    }

    public void alertMultiClickDialog(final int id, int resIcon, String title, String[] items, boolean[] checkedItems, int positiveButton)
    {
        if( multiChoice != null )         return;

        multiChoice = new AlertDialog.Builder(context);
        multiChoice.setIcon(resIcon);
        multiChoice.setTitle(title);
        multiChoice.setCancelable(false);
        multiChoice.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if( onDialogMultiChoiceListener != null )
                    onDialogMultiChoiceListener.onChecked(id, which, isChecked);
            }
        });
        multiChoice.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                if (onDialogConfirmClickListener != null)
                    onDialogConfirmClickListener.onConfirm(id);
            }
        });
        multiChoice.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                multiChoice = null;
            }
        });
        AlertDialog dialog = multiChoice.create();
        dialog.show();
    }

    public void alertGPSDialog(int resIcon, String title, String message, int positiveButton)
    {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if ( gps != null || manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) || manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ) {
            return;
        }

        gps = new AlertDialog.Builder(context);
        gps.setIcon(resIcon);
        gps.setTitle(title);
        gps.setMessage(message);
        gps.setCancelable(false);
        gps.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        gps.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                dialog.dismiss();
                gps = null;
            }
        });
        AlertDialog dialog = gps.create();
        dialog.show();
    }

    public void alertPermissionDialog(int resIcon, String title, String message, int positiveButton)
    {
        if( permission != null )      return;

        permission = new AlertDialog.Builder(context);
        permission.setIcon(resIcon);
        permission.setTitle(title);
        permission.setMessage(message);
        permission.setCancelable(false);
        permission.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + context.getPackageName()));
                context.startActivity(myAppSettings);
                dialog.dismiss();
                permission = null;
            }
        });
        permission.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                permission = null;
            }
        });
        AlertDialog dialog = permission.create();
        dialog.show();
    }

    public void clear( )
    {
        gps = null;
        permission = null;
        multiChoice = null;
        list = null;
        confirm = null;
        simple = null;
    }
}
