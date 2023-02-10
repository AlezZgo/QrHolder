package com.alezzgo.qrholder.data

import com.alezzgo.qrholder.data.cache.SharedPrefs

class TestSharedPrefs : SharedPrefs {

    var permissionNeverShow = false

    override fun savePermissionNeverShow(neverShow: Boolean) {
        permissionNeverShow = neverShow
    }

    override fun fetchPermissionNeverShow(): Boolean {
        return permissionNeverShow
    }

}
