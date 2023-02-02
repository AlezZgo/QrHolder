package com.example.qrholder.data

import com.example.qrholder.data.cache.SharedPrefs

class TestSharedPrefs : SharedPrefs {

    var permissionNeverShow = false

    override fun savePermissionNeverShow(neverShow: Boolean) {
        permissionNeverShow = neverShow
    }

    override fun fetchPermissionNeverShow(): Boolean {
        return permissionNeverShow
    }

}
