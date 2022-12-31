package com.example.myproducts.viewModels

 import androidx.arch.core.executor.testing.InstantTaskExecutorRule
 import androidx.lifecycle.viewModelScope
 import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myproducts.pojo.models.Products
import com.example.myproducts.pojo.models.RatingProducts
import com.example.myproducts.pojo.presentation.ProductsViewModel
import com.example.myproducts.rule.MainCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class productsViewModel : CoroutineScope {
    val job = Job()

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutine = MainCoroutineRule()

    private lateinit var viewModel: ProductsViewModel

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Unconfined

    @Before
    fun setup() {
        viewModel = ProductsViewModel()
    }

    @Test
    fun addProductTesting() = runBlocking {
        val numberOfItems = viewModel.products.value?.size
        viewModel.getProductsList()
        assert(viewModel.products.value?.size == numberOfItems?.plus(1))
    }

    @Test
    fun displayDetailsTesting() = runBlocking {
        val product = Products(1001, "bag", 30.500, " small bag", "0", "testing", RatingProducts(5.0,3))
        viewModel.displayPropertyDetails(product)
        TestCase.assertEquals(viewModel.navigateToSelectedProperty.value?.title,"bag")
        TestCase.assertEquals(viewModel.navigateToSelectedProperty.value?.rating?.rate,5.0)
        TestCase.assertEquals(viewModel.navigateToSelectedProperty.value?.price,30.500)
    }


    @Test
    fun displayPropertyDetailsCompleteTesting() = runBlocking {
        val navigationValue = viewModel.navigateToSelectedProperty.value
        viewModel.displayPropertyDetailsComplete()
        assert(navigationValue == null)
    }
}