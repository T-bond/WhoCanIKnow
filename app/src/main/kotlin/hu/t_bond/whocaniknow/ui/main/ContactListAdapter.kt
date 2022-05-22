package hu.t_bond.whocaniknow.ui.main

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import hu.t_bond.whocaniknow.R
import hu.t_bond.whocaniknow.component.network.model.contact.Contact
import hu.t_bond.whocaniknow.databinding.ContactRowItemBinding

class ContactListAdapter(
    contacts: Map<Int, Contact> = emptyMap(),
    private val onClick: ((Int, View, View) -> Unit)? = null
) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    var contacts = contacts
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(
        private val binding: ContactRowItemBinding,
        onClick: ((Int, View, View) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        var contact: Contact?
            get() = binding.contact
            set(value) {
                binding.contact = value
            }

        var contactId: Int = -1

        init {
            onClick?.let {
                binding.root.setOnClickListener {
                    binding.contactImage.transitionName = "contactImage"
                    binding.contactName.transitionName = "contactName"

                    it(contactId, binding.contactImage, binding.contactName)
                }
            }
        }

        companion object {

            @JvmStatic
            @BindingAdapter("loadImageFromUri")
            fun loadImageFromUri(view: ImageView, imageUri: Uri?) {
                if (imageUri == null) {
                    return
                }

                Glide.with(view.context)
                    .load(imageUri)
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContactRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val entry = contacts.entries.toList()[position]
        viewHolder.contactId = entry.key
        viewHolder.contact = entry.value
    }

    override fun getItemCount() = contacts.size

}
