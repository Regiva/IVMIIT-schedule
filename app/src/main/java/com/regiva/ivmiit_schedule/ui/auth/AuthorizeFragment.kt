package com.regiva.ivmiit_schedule.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.regiva.ivmiit_schedule.Constants
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.Screens
import com.regiva.ivmiit_schedule.model.interactors.AuthInteractor
import com.regiva.ivmiit_schedule.model.system.FlowRouter
import com.regiva.ivmiit_schedule.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_authorize.*

class AuthorizeFragment : BaseFragment() {

    private val flowRouter: FlowRouter by lazy {
        scope.getInstance(FlowRouter::class.java)
    }

    private val authInteractor: AuthInteractor by lazy {
        scope.getInstance(AuthInteractor::class.java)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_authorize

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openAuthPage()
    }

    private fun openAuthPage() {
        wv_auth.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.d("rere", "wow")
                if ((url?.contains("https://oauth.vk.com/blank.html")) == true) {
                    val token = url
                        .substringAfter("access_token=")
                        .substringBefore("&expires_in")
                    authInteractor.storeToken(token)
                    flowRouter.newRootFlow(Screens.Main())
                }
                else {
                    //todo
                    Log.d("rere", "vot tak vot)")
                }
                return false
            }
        }
        wv_auth.loadUrl("https://oauth.vk.com/authorize?client_id=${Constants.Api.APP_ID}&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=wall,groups&response_type=token&v=5.60")
    }

}