package com.alezzgo.qrholder.data.mapper

import com.alezzgo.qrholder.data.QrCodeData
import com.alezzgo.qrholder.data.cache.db.QrCodeCache
import javax.inject.Inject

class QrCodeDataToCacheMapper @Inject constructor() : QrCodeData.Mapper<QrCodeCache> {
    override fun map(title: String, content: String, path: String) =
        QrCodeCache(title, content, path, System.currentTimeMillis())
}