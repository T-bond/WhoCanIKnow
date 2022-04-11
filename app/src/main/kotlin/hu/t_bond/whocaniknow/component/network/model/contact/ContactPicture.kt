package hu.t_bond.whocaniknow.component.network.model.contact

import android.net.Uri

data class ContactPicture(
    val large: Uri,
    val medium: Uri,
    val thumbnail: Uri,
)
