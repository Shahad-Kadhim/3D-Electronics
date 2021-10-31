package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel

class AboutViewModel :BaseViewModel(), BaseInteractionListener{

    var vendors = Repository.getVendors()
    var otherVendors = Repository.getOtherVendors()

}