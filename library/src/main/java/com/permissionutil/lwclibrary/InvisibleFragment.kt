package com.permissionutil.lwclibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * @Description:
 * @author 95683
 * @date 2023/8/10 16:27
 */
typealias PermissionsCallback = (Boolean,List<String>)->Unit

class InvisibleFragment:Fragment() {

    private var callback:PermissionsCallback?=null

    fun requestNow(cb:PermissionsCallback,vararg permissions:String){
        callback = cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1){
            val deniedList = ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
                if(result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted,deniedList) }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}