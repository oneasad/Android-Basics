package com.oneasad.videotry3

import android.app.Activity
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import java.util.UUID


inline fun ComponentActivity.askPermissions(
    vararg permissions: String,
    cleanPermissionName: Boolean = false,
    crossinline onPermissionGranted: (permission: String) -> Unit,
    crossinline onPermissionDenied: (permission: String) -> Unit,
    crossinline onPermissionsResultCallback: (List<PermissionsResult>) -> Unit,
) {

    val permissionsLauncher = registerActivityResultLauncher(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        callback = { permissionsResult ->
            permissionsResult.forEach {
                val permission = if (cleanPermissionName) it.key.toCleanPermissionName() else it.key
                val isGranted = it.value

                if (isGranted)
                    onPermissionGranted(permission)
                else
                    onPermissionDenied(permission)
            }
            val result = permissionsResult.map {
                PermissionsResult(it.key, it.value)
            }
            onPermissionsResultCallback(result)
        }
    )


    permissionsLauncher.launch(permissions.toList().toTypedArray())

}


fun Activity.checkPermissions(
    vararg permissions: String,
): Boolean {
    return permissions.all {
        checkSelfPermission(it) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }
}

data class PermissionsResult(
    val permission: String,
    val isGranted: Boolean,
)

 fun String.toCleanPermissionName(): String {
    return this.replace("android.permission.", "")
}


object Permissions {
    const val PERMISSION_READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE
    const val PERMISSION_WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val PERMISSION_MEDIA_AUDIO = android.Manifest.permission.READ_MEDIA_AUDIO

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val PERMISSION_MEDIA_IMAGES = android.Manifest.permission.READ_MEDIA_IMAGES

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val PERMISSION_MEDIA_VIDEO = android.Manifest.permission.READ_MEDIA_VIDEO

    const val PERMISSION_CAMERA = android.Manifest.permission.CAMERA
    const val PERMISSION_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
    const val PERMISSION_LOCATION_COARSE = android.Manifest.permission.ACCESS_COARSE_LOCATION
    const val PERMISSION_CALL_PHONE = android.Manifest.permission.CALL_PHONE
    const val PERMISSION_READ_PHONE_STATE = android.Manifest.permission.READ_PHONE_STATE
    const val PERMISSION_SEND_SMS = android.Manifest.permission.SEND_SMS
    const val PERMISSION_READ_SMS = android.Manifest.permission.READ_SMS
    const val PERMISSION_RECEIVE_SMS = android.Manifest.permission.RECEIVE_SMS
    const val PERMISSION_READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
    const val PERMISSION_WRITE_CONTACTS = android.Manifest.permission.WRITE_CONTACTS
    const val PERMISSION_RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    const val PERMISSION_READ_CALENDAR = android.Manifest.permission.READ_CALENDAR
    const val PERMISSION_WRITE_CALENDAR = android.Manifest.permission.WRITE_CALENDAR
    const val PERMISSION_READ_CALL_LOG = android.Manifest.permission.READ_CALL_LOG
    const val PERMISSION_WRITE_CALL_LOG = android.Manifest.permission.WRITE_CALL_LOG

    @RequiresApi(Build.VERSION_CODES.Q)
    const val PERMISSION_ACCESS_MEDIA_LOCATION = android.Manifest.permission.ACCESS_MEDIA_LOCATION
    const val PERMISSION_BODY_SENSORS = android.Manifest.permission.BODY_SENSORS
    const val PERMISSION_USE_SIP = android.Manifest.permission.USE_SIP
    const val PERMISSION_PROCESS_OUTGOING_CALLS = android.Manifest.permission.PROCESS_OUTGOING_CALLS
    const val PERMISSION_ADD_VOICEMAIL = android.Manifest.permission.ADD_VOICEMAIL

    @RequiresApi(Build.VERSION_CODES.O)
    const val PERMISSION_ANSWER_PHONE_CALLS = android.Manifest.permission.ANSWER_PHONE_CALLS

    @RequiresApi(Build.VERSION_CODES.P)
    const val PERMISSION_ACCEPT_HANDOVER = android.Manifest.permission.ACCEPT_HANDOVER

    @RequiresApi(Build.VERSION_CODES.Q)
    const val PERMISSION_ACCESS_BACKGROUND_LOCATION =
        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
    const val PERMISSION_ACT_AS_PACKAGE_USAGE_STATS =
        android.Manifest.permission.PACKAGE_USAGE_STATS
    const val PERMISSION_REQUEST_INSTALL_PACKAGES =
        android.Manifest.permission.REQUEST_INSTALL_PACKAGES

    @RequiresApi(Build.VERSION_CODES.O)
    const val PERMISSION_MANAGE_OWN_CALLS = android.Manifest.permission.MANAGE_OWN_CALLS


}


/**
 * Registers an ActivityResultLauncher with the specified contract and callback.
 * @param contract The ActivityResultContract for the launcher.
 * @param callback The callback to handle the result of the launched activity.
 * @return The registered ActivityResultLauncher.
 */
fun <I, O> ComponentActivity.registerActivityResultLauncher(
    contract: ActivityResultContract<I, O>,
    callback: ActivityResultCallback<O>,
): ActivityResultLauncher<I> {
    val key = UUID.randomUUID().toString()
    return activityResultRegistry.register(key, contract, callback)
}