package hu.t_bond.whocaniknow.model.contact

import android.net.Uri

data class ContactPicture(
    val large: Uri,
    val medium: Uri,
    val thumbnail: Uri,
)
