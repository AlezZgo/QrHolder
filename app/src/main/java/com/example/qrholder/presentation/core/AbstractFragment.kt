package com.example.qrholder.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.qrholder.core.ui.AbstractViewModel

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class AbstractFragment<B : ViewBinding, V : AbstractViewModel>(
    private val inflate: Inflate<B>,
    private val clazz: Class<V>
) : Fragment() {

    protected var isSharedViewModel: Boolean = false
    lateinit var viewModel: V

    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = if (isSharedViewModel) {
            ViewModelProvider(requireActivity())[clazz]
        } else {
            ViewModelProvider(this)[clazz]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _viewBinding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        observe()
        viewModel.init(savedInstanceState == null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    open fun setupViews() = Unit

    open fun setupListeners() = Unit

    open fun observe() = Unit

}