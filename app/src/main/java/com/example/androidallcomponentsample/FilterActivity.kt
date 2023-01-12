package com.example.androidallcomponentsample

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.View
import androidx.work.WorkInfo
import com.example.androidallcomponentsample.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
/*
    private val viewModel : FilterViewModel by viewModels { FilterViewModelFactory(application) }
*/
    private var outputImageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityFilterBinding.inflate(layoutInflater).run {
            setContentView(root)
            bindViews(this)
            viewModel.workInfo.observer(this@FilterActivity) { info ->
                if (info.size == 0)
                    return@observer
                else
                    onStateChange(info[0], this)
            }
        }

    }

    private fun bindViews(binding: ActivityFilterBinding){
        with(binding){
            val imageUri : Uri = Uri.parse(intent.getStringExtra(Constants.KEY_IMAGE_URI))
            Glide.with(this@FilterActivity).load(imageUri).into(imageView)

            val multipleDestinationsPossible = Constants.IMGUR_CLIENTS_ID.isNotEmpty()
            if(!multipleDestinationsPossible){
                destinationsGroup.visibility = View.GONE
            }

            apply.setOnClickListener{
                val applyWaterColor = filterWatercolor.isChecked
                val applyGrayScale = filterGrayscale.isChecked
                val applyBlur = filterBlur.isChecked
                val save = save.isChecked

                val imageOperation = ImageOperations(
                    applicationContext, imageUri,
                    applyWaterColor,applyGrayScale,
                    applyBlur,save
                )

                viewModel.apply(imageOperation)

            }

            output.setOnClickListener{
                if(outputImageUri != null){
                    val viewOutput = Intent(Intent.ACTION_VIEW,outputImageUri)
                    if(viewOutput.resolveActivity(packageManager) != null){
                        startActivity(viewOutput)
                    }
                }
            }
            cancel.setOnClickListener{
                viewModel.cancel()
            }
        }
    }

    private fun onStateChange(info : WorkInfo, binding : ActivityFilterBinding){
        val finished = info.state.isFinished

        with(binding){
            if(!finished){
                progressBar.visibility = View.VISIBLE
                cancel.visibility = View.VISIBLE
                apply.visibility = View.GONE
                output.visibility = View.GONE
            }
            else{
                progressBar.visibility = View.GONE
                cancel.visibility = View.GONE
                apply.visibility = View.VISIBLE
            }
        }

        val outputData = info.outputData
        outputData.getString(Constants.KEY_IMAGE_URI)?.let {
            outputImageUri = Uri.parse(it)
            binding.output.visibility = View.VISIBLE
        }

    }

    companion object {
        internal fun newIntent(context: Context,imageUri: Uri) =
            Intent(context,FilterActivity::class.java).putExtra(
                Constants.KEY_IMAGE_URI,imageUri.toString()
            )
    }
}
