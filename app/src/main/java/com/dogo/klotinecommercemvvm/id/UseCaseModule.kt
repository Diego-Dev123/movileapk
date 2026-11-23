package com.dogo.klotinecommercemvvm.id

import com.dogo.klotinecommercemvvm.domain.repository.AuthRepository
import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository
import com.dogo.klotinecommercemvvm.domain.repository.UsersRepository
import com.dogo.klotinecommercemvvm.domain.useCase.auth.AuthUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.GetSessionDataUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.LoginUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.RegisterUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.SaveSessionUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.UpdateSessionUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.auth.logoutUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.CategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.CreateCategoryUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.DeleteCategoryUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.GetCategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.GetTrashCategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.RestoreCategoryUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.GetTrashProductsUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.RestoreProductUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.UpdateCategoryUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.categories.UpdateCategoryWithImageUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.CreateProductUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.DeleteProductUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.FindAllUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.FindByCategoryUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.UpdateProductUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.UpdateWithImageProductUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.users.UpdateUsersUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.users.UpdateUsersWithImageUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository) = AuthUseCase(
        login = LoginUseCase(authRepository),
        register = RegisterUseCase(authRepository),
        saveSession = SaveSessionUseCase(authRepository),
        getSessionData = GetSessionDataUseCase(authRepository),
        logout = logoutUseCase(authRepository),
        updateSession = UpdateSessionUseCase(authRepository)

    )

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository) = UsersUseCase(
        updateUser = UpdateUsersUseCase(usersRepository),
        updateUserWithImage = UpdateUsersWithImageUseCase(usersRepository)
    )

    @Provides
    fun provideCategoryUseCase(categoriesRepository: CategoriesRepository) = CategoriesUseCase(
        createCategory = CreateCategoryUseCase(categoriesRepository),
        getCategories = GetCategoriesUseCase(categoriesRepository),
        updateCategory = UpdateCategoryUseCase(categoriesRepository),
        updateCategoryWithImage = UpdateCategoryWithImageUseCase(categoriesRepository),
        deleteCategory = DeleteCategoryUseCase(categoriesRepository),
        getTrash = GetTrashCategoriesUseCase(categoriesRepository),
        restoreCategory = RestoreCategoryUseCase(categoriesRepository)
    )

    @Provides
    fun provideProductsUseCase(productRepository: ProductRepository) = ProductsUseCase(
        createProduct = CreateProductUseCase(productRepository),
        FindByCategoryUseCase(productRepository),
        updateProduct = UpdateProductUseCase(productRepository),
        updateWithImageProduct = UpdateWithImageProductUseCase(productRepository),
        deleteProduct = DeleteProductUseCase(productRepository),
        findAll = FindAllUseCase(productRepository),
        getTrash = GetTrashProductsUseCase(productRepository),
        restoreProduct = RestoreProductUseCase(productRepository)
    )


}