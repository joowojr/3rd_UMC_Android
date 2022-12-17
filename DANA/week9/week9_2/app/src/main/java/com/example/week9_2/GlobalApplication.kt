package com.example.week9_2

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "d7062489d6666ad57244c6ae70bddefc")
    }
}