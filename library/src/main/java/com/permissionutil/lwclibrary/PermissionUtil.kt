package com.permissionutil.lwclibrary

import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import java.security.Permissions

/**
 * @Description:
 * @author 95683
 * @date 2023/8/10 16:42
 */
object PermissionUtil {
    private const val TAG = "InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permissions:String,callback:PermissionsCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if(existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }

}