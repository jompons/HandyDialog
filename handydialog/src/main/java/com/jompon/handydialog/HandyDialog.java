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
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

public class HandyDialog {

    private Context context;
    private AlertDialog.Builder simple;
    private AlertDialog.Builder confirm;
    private AlertDialog.Builder list;
    private AlertDialog.Builder multiChoice;
    private AlertDialog.Builder gps;
    private AlertDialog.Builder permission;
    private OnDialogConfirmClickListener onDialogConfirmClickListener;
    private OnDialogCancelClickListener onDialogCancelClickListener;
    private OnDialogItemClickListener onDialogItemClickListener;
    private OnDialogCheckListener onDialogCheckListener;

    public interface OnDialogConfirmClickListener{

        void onConfirm(long id);
    }

    public interface OnDialogCancelClickListener{

        void onCancel(long id);
    }

    public interface  OnDialogItemClickListener{

        void onItem(long id, int which);
    }

    public interface  OnDialogCheckListener{

        void onChecked(long id, int which, boolean isChecked);
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

    public void setOnDialogCheckListener(OnDialogCheckListener onDialogCheckListener)
    {
        this.onDialogCheckListener = onDialogCheckListener;
    }

    public HandyDialog(Context context)
    {
        this.context = context;
    }

    /**
     * Show simple alert dialog.
     * @param message source
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(String message, @StringRes int positiveButton)
    {
        alertSimpleDialog("", message, positiveButton);
    }

    /**
     * Show simple alert dialog.
     * @param title source
     * @param message source
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(String title, String message, @StringRes int positiveButton)
    {
        alertSimpleDialog(title, message, 0, positiveButton);
    }

    /**
     * Show simple alert dialog.
     * @param title source
     * @param message source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(String title, String message, @DrawableRes int resIcon, @StringRes int positiveButton)
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

    /**
     * Show simple alert dialog with confirm listener.
     * @param id need to tag
     * @param message source
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(final long id, String message, @StringRes int positiveButton)
    {
        alertSimpleDialog(id, "", message, positiveButton);
    }
    
    /**
     * Show simple alert dialog with confirm listener.
     * @param id need to tag
     * @param title source
     * @param message source
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(final long id, String title, String message, @StringRes int positiveButton)
    {
        alertSimpleDialog(id, title, message, 0, positiveButton);
    }

    /**
     * Show simple alert dialog with confirm listener.
     * @param id need to tag
     * @param title source
     * @param message source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     */
    public void alertSimpleDialog(final long id, String title, String message, @DrawableRes int resIcon, @StringRes int positiveButton)
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
                if (onDialogConfirmClickListener != null)
                    onDialogConfirmClickListener.onConfirm(id);
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

    /**
     * Show confirm alert dialog with confirm listener.
     * @param id need to tag
     * @param message source
     * @param positiveButton string resource
     * @param negativeButton string resource
     */
    public void alertConfirmDialog(final long id, String message, @StringRes int positiveButton, @StringRes int negativeButton)
    {
        alertConfirmDialog(id, "", message, positiveButton, negativeButton);
    }

    /**
     * Show confirm alert dialog with confirm listener.
     * @param id need to tag
     * @param title source
     * @param message source
     * @param positiveButton string resource
     * @param negativeButton string resource
     */
    public void alertConfirmDialog(final long id, String title, String message, @StringRes int positiveButton, @StringRes int negativeButton)
    {
        alertConfirmDialog(id, title, message, 0, positiveButton, negativeButton);
    }

    /**
     * Show confirm alert dialog with confirm listener.
     * @param id need to tag
     * @param title source
     * @param message source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     * @param negativeButton string resource
     */
    public void alertConfirmDialog(final long id, String title, String message, @DrawableRes int resIcon, @StringRes int positiveButton, @StringRes int negativeButton)
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

    /**
     * Show list alert dialog with item listener.
     * @param id need to tag
     * @param items source
     */
    public void alertListDialog(final long id, String[] items)
    {
        alertListDialog(id, "", items);
    }

    /**
     * Show list alert dialog with item listener.
     * @param id need to tag
     * @param title source
     * @param items source
     */
    public void alertListDialog(final long id, String title, String[] items)
    {
        alertListDialog(id, title, items, 0);
    }

    /**
     * Show list alert dialog with item listener.
     * @param id need to tag
     * @param title source
     * @param items source
     * @param resIcon drawable or mipmap resource
     */
    public void alertListDialog(final long id, String title, String[] items, @DrawableRes int resIcon)
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

    /**
     * Show multi choice alert dialog with multi choice listener.
     * @param id need to tag
     * @param items source
     * @param checkedItems source
     * @param positiveButton string resource
     */
    public void alertMultiChoiceDialog(final long id, String[] items, boolean[] checkedItems, @StringRes int positiveButton)
    {
        alertMultiChoiceDialog(id, "", items, checkedItems, positiveButton);
    }

    /**
     * Show multi choice alert dialog with multi choice listener.
     * @param id need to tag
     * @param title source
     * @param items source
     * @param checkedItems source
     * @param positiveButton string resource
     */
    public void alertMultiChoiceDialog(final long id, String title, String[] items, boolean[] checkedItems, @StringRes int positiveButton)
    {
        alertMultiChoiceDialog(id, title, items, checkedItems, 0, positiveButton);
    }

    /**
     * Show multi choice alert dialog with multi choice listener.
     * @param id need to tag
     * @param title source
     * @param checkedItems source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     */
    public void alertMultiChoiceDialog(final long id, String title, String[] items, boolean[] checkedItems, @DrawableRes int resIcon, @StringRes int positiveButton)
    {
        if( multiChoice != null )         return;

        multiChoice = new AlertDialog.Builder(context);
        multiChoice.setIcon(resIcon);
        multiChoice.setTitle(title);
        multiChoice.setCancelable(false);
        multiChoice.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if( onDialogCheckListener != null )
                    onDialogCheckListener.onChecked(id, which, isChecked);
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

    /**
     * Show gps alert dialog with intent to gps setting
     * @param title source
     * @param message source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     */
    public void alertGPSDialog(String title, String message, @DrawableRes int resIcon, @StringRes int positiveButton)
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

    /**
     * Show permission alert dialog with intent to permission setting
     * @param title source
     * @param message source
     * @param resIcon drawable or mipmap resource
     * @param positiveButton string resource
     */
    public void alertPermissionDialog(String title, String message, @DrawableRes int resIcon, @StringRes int positiveButton)
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

    /**
     * reset all alert dialog object to null
     */
    public void clear( )
    {
        simple = null;
        confirm = null;
        list = null;
        multiChoice = null;
        gps = null;
        permission = null;
    }
}
