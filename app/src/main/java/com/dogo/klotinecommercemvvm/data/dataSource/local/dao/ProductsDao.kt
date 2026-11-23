package com.dogo.klotinecommercemvvm.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.ProductEntity
import com.dogo.klotinecommercemvvm.domain.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun findAll(): Flow<List<ProductEntity>>
//CAMELPIECASE
    @Query("SELECT * FROM products WHERE id_category = :idCategory")
    fun findByCategory(idCategory: String): Flow<List<ProductEntity>>



    @Query("UPDATE products SET name = :name, " + "description = :description, image1 = :image1, image2 = :image2, price= :price WHERE id = :id")
    suspend fun update(id: String, name: String, description: String, image1: String, image2: String, price: Double)


    @Query("DELETE FROM products WHERE id = :id")
    suspend fun delete(id: String)

    //al eliminar una categoría sus productos quedan ocultos también en la caché local
    @Query("DELETE FROM products WHERE id_category = :idCategory")
    suspend fun deleteByCategory(idCategory: String)

}
