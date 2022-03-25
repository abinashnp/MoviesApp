package com.abinash.moviesapp.ui

import android.os.Bundle
import android.view.*
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.abinash.moviesapp.utils.extension.showMessageSnackBar
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject


abstract class BaseFragment <T : BaseViewModel, VB : ViewDataBinding>(val mContentLayoutId: Int) :
    DaggerFragment() {

    lateinit var binding: VB
    lateinit var viewModel: T
    var dialog: AlertDialog? = null


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    /**
     * @return view model instance
     */
    fun getBaseViewModel(): T {
        val types: Array<Type> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        return ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(types[0] as Class<T>)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, mContentLayoutId, container, false)

        setViewModelObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setViewModelObservers() {
        viewModel.apply {
            onErrorEvent.observe(viewLifecycleOwner, {onError(it)})
        }
    }


    @MainThread
    protected fun setProviderFactory(providerFactory: ViewModelProvider.Factory) {
        viewModelFactory = providerFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getBaseViewModel()
    }

    fun onError(error: String?) {
        error?.let { showMessageSnackBar(error) }
    }

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
    }
}
