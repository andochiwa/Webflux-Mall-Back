package com.github.product.service

import com.github.product.dao.AttrDao
import com.github.product.dao.AttrGroupDao
import com.github.product.dao.AttrGroupRelationDao
import com.github.product.entity.Attr
import com.github.product.entity.AttrGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Service
class AttrGroupService {
    @Autowired
    lateinit var attrGroupDao: AttrGroupDao

    @Autowired
    lateinit var attrGroupRelationDao: AttrGroupRelationDao

    @Autowired
    lateinit var attrDao: AttrDao

    suspend fun getById(id: Long): AttrGroup? {
        return attrGroupDao.findById(id)
    }

    suspend fun saveOrUpdate(attrGroup: AttrGroup): AttrGroup {
        return attrGroupDao.save(attrGroup)
    }

    suspend fun deleteById(id: Long) {
        attrGroupDao.deleteById(id)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        attrGroupDao.deleteAllById(ids)
    }

    fun getAll(): Flow<AttrGroup> {
        return attrGroupDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        val attrgroupList =
            key?.run { attrGroupDao.findByAttrGroupNameContaining(key, pageRequest).toList() }
                ?: run { attrGroupDao.findBy(pageRequest).toList() }
        return mutableMapOf<String, Any>().apply {
            this["attrgroup"] = attrgroupList
            this["totalCount"] = attrGroupDao.count()
        }
    }

    suspend fun getPaginationByCategoryId(page: Int, limit: Int, key: String?, categoryId: Long): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        val attrGroupList =
            key?.run { attrGroupDao.findByAttrGroupNameContainingAndCatelogId(key, categoryId, pageRequest).toList() }
                ?: run { attrGroupDao.findByCatelogId(categoryId, pageRequest).toList() }
        return mutableMapOf<String, Any>().apply {
            this["attrgroup"] = attrGroupList
            this["totalCount"] = attrGroupDao.countByCatelogId(categoryId)
        }
    }

    suspend fun getAttrRelation(attrGroupId: Long): List<Attr> {
        val attrGroupRelationList = attrGroupRelationDao.getAllByAttrGroupId(attrGroupId)
        val attrIds = attrGroupRelationList.map { it.attrId!! }
        return attrDao.findAllById(attrIds).toList()
    }

    suspend fun getNoAttrRelation(attrGroupId: Long, page: Int, limit: Int, key: String?): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        val catelogId = attrGroupDao.findById(attrGroupId)?.catelogId!!
        // 当前分类下的所有分组
        val attrGroupIds =
            attrGroupDao.findByCatelogId(catelogId).map { it.attrGroupId!! }
        // 分组关联的所有属性
        val attrIds = attrGroupRelationDao.getAllByAttrGroupIdIn(attrGroupIds.toList()).map { it.attrId!! }.toList()

        val totalCount: Long
        val attrList: List<Attr>
        // 查出所有未被关联的属性
        if (key != null) {
            totalCount =
                attrDao.countAllByAttrIdNotInAndCatelogIdAndAttrNameContainingAndAttrType(attrIds, catelogId, key)
            attrList = attrDao.findAllByAttrIdNotInAndCatelogIdAndAttrNameContainingAndAttrType(
                attrIds, catelogId, key, pageRequest
            ).toList()
        } else {
            totalCount = attrDao.countAllByAttrIdNotInAndCatelogIdAndAttrType(attrIds, catelogId)
            attrList = attrDao.findAllByAttrIdNotInAndCatelogIdAndAttrType(attrIds, catelogId, pageRequest).toList()
        }
        return mutableMapOf<String, Any>().apply {
            this["attr"] = attrList
            this["totalCount"] = totalCount
        }
    }
}

