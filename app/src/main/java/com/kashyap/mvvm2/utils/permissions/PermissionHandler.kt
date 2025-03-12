package com.kashyap.mvvm2.utils.permissions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kashyap.mvvm2.R
import com.kashyap.mvvm2.databinding.DialogPermissionSettingsBinding
import com.kashyap.mvvm2.di.dialog.BaseCustomDialog

class PermissionHandler(val activity: AppCompatActivity, val arlPermission: ArrayList<String> = arrayListOf(), val message: String = "", val onResult: () -> (Unit)) {

    fun execute(showWithDetails: Boolean) {
        if (showWithDetails) {
            showForceSettingsDialog()
        } else {
            activity.runWithPermissions(arlPermission.joinToString(", "), options = options) {
                onResult()
            }
        }
    }

    val options = QuickPermissionsOptions(
        handleRationale = false,
        handlePermanentlyDenied = false,
    )

    //Show settings dialog for force open the permission screen of app
    private fun showForceSettingsDialog() {
        var dialogGalleryRational: BaseCustomDialog<DialogPermissionSettingsBinding>? = null
        dialogGalleryRational = BaseCustomDialog(activity, R.layout.dialog_permission_settings, object : BaseCustomDialog.Listener {
            override fun onViewClick(view: View) {
                when (view.id) {
                    R.id.clMain -> {
                        dialogGalleryRational?.dismiss()
                    }

                    R.id.txtConfirm -> {
                        dialogGalleryRational?.dismiss()
                        if (activity.shouldShowRequestPermissionRationale(arlPermission.first())) {
                            val intent = Intent()
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package", activity.packageName, null)
                            intent.setData(uri)
                            ContextCompat.startActivity(activity, intent, null)
                        } else {
                            activity.runWithPermissions(*arlPermission.toTypedArray(), options = options) {
                                onResult()
                            }
                        }
                    }

                    R.id.txtCancel -> {
                        dialogGalleryRational?.dismiss()
                    }
                }
            }
        })

        dialogGalleryRational.show()
        dialogGalleryRational.getBinding()?.heading = "Permission required"
        dialogGalleryRational.getBinding()?.message = message
        dialogGalleryRational.getBinding()?.buttonOK = "Grant"

        if (dialogGalleryRational.window != null) {
            val lWindowParams = WindowManager.LayoutParams()
            lWindowParams.copyFrom((dialogGalleryRational.window ?: return).attributes)
            lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
            lWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT
            (dialogGalleryRational.window ?: return).attributes = lWindowParams
        }
    }
}