package com.github.product.service

import com.github.product.dao.BrandDao
import com.github.product.entity.Brand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Service
class BrandService {
    @Autowired
    lateinit var brandDao: BrandDao

    @Autowired
    lateinit var categoryBrandRelationService: CategoryBrandRelationService

    suspend fun getById(id: Long): Brand? {
        return brandDao.findByBrandIdAndShowStatus(id)
    }

    suspend fun saveOrUpdate(brand: Brand): Brand {
        return brandDao.save(brand)
    }

    @Transactional
    suspend fun deleteByIds(id: List<Long>) {
        brandDao.deleteAllById(id)
        // todo: delete redundant data from other tables
        categoryBrandRelationService.deleteByBrandId(id)
    }

    fun getAll(): Flow<Brand> {
        return brandDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        key ?: run {
            val brandList = brandDao.findBy(pageRequest).toList()
            return mutableMapOf<String, Any>().apply {
                this["brand"] = brandList
                this["totalCount"] = brandDao.count()
            }
        }
        val brandList = brandDao.findByNameContaining(key, pageRequest).toList()
        return mutableMapOf<String, Any>().apply {
            this["brand"] = brandList
            this["totalCount"] = brandDao.count()
        }
    }

    @Transactional
    suspend fun updateDetail(brand: Brand) {
        brandDao.save(brand)
        // todo: update redundant data from other tables
        categoryBrandRelationService.updateBrandNameByBrandId(brand)
    }
}

