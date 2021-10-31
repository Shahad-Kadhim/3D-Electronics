package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel

class AboutViewModel :BaseViewModel(), BaseInteractionListener{

    var companies = Repository.getVendors()
    var otherCompanies = Repository.getOtherVendors()

    var video = "https://www.3d-iraq.com/static/media/vedio-3d.146c63e2.mp4"
}