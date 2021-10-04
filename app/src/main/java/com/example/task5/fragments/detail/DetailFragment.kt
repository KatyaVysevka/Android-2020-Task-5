package com.example.task5.fragments.detail

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.task5.R
import com.example.task5.data.CatPhoto
import com.example.task5.databinding.FragmentDetailBinding
import java.util.jar.Manifest

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = requireNotNull(_binding)
    private val ags by navArgs<DetailFragmentArgs>()

    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private lateinit var permissionsLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val photo = ags.detailPhoto

            Glide.with(this@DetailFragment)
                .load(photo.url)
                .error(R.drawable.ic_baseline_error_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false


                        return false
                    }
                })
                .into(imageView)


//            val uri = Uri.parse(photo.breeds.attributionUrl)
//            val intent = Intent(Intent.ACTION_VIEW, uri)

//            textViewCreator.apply {
//                text = "Photo by ${photo.user.name} on Unsplash"
//                setOnClickListener {
//                    context.startActivity(intent)
//                }
//                paint.isUnderlineText = true
//            }
        }

        // add menu
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_menu, menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val photo = ags.detailPhoto
//
//        when (item.itemId) {
//            R.id.save -> {
//                val filename = photo.url.substringAfterLast("/")
//                val request = DownloadManager.Request(Uri.parse(photo.url))
//                    .setTitle(filename)
//                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
//                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                (context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
//                    .enqueue(request)
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    private fun updateOrRequestPermissions() {
        val hasReadPermission = ContextCompat.checkSelfPermission(
            requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val hasWritePermission = ContextCompat.checkSelfPermission(
            requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission || minSdk29

        val permissionsToRequest = mutableListOf<String>()

        if (!writePermissionGranted) {
            permissionsToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!readPermissionGranted) {
            permissionsToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()) {
            permissionsLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }

//    private fun savePhotoToExternalStorage(catPhoto: CatPhoto): Boolean {
//        val imageCollection = sdk29AndUp {
//            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//
//        val contentValues = ContentValues().apply {
//            put (MediaStore.Images.Media.DISPLAY_NAME, "${catPhoto.id}")
//            put (MediaStore.Images.Media.MIME_TYPE, "image/jpg")
//            put (MediaStore.Images.Media.WIDTH, catPhoto.width)
//            put (MediaStore.Images.Media.HEIGHT, catPhoto.height)
//        }
//
//        return try {
//            requireActivity().contentResolver.insert(imageCollection,ContentValues)?.also {
//                uri ->
//                requireActivity().contentResolver.openOutputStream(uri).use {outputStream ->
//
//                    if()
//                }
//            }
//        } catch (exception: IOException) {
//            exception.printStackTrace()
//            false
//        }
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
