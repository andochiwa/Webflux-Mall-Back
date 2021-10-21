package com.github.product.service

import com.github.product.dao.AttrGroupRelationDao
import com.github.product.entity.AttrGroupRelation
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Service
class AttrGroupRelationService {
    @Autowired
    lateinit var attrGroupRelationDao: AttrGroupRelationDao

    @Autowired
    lateinit var attrService: AttrService

    suspend fun getById(id: Long): AttrGroupRelation? {
        return attrGroupRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(attrGroupRelation: AttrGroupRelation): AttrGroupRelation {
        return attrGroupRelationDao.save(attrGroupRelation)
    }

    suspend fun deleteById(id: Long) {
        attrGroupRelationDao.deleteById(id)
    }

    fun getAll(): Flow<AttrGroupRelation> {
        return attrGroupRelationDao.findAll()
    }

    suspend fun updateGroup(attrGroupId: Long, attrId: Long) {
        attrGroupRelationDao.updateGroup(attrGroupId, attrId)
    }

    suspend fun getGroupByAttrId(attrId: Long): AttrGroupRelation? {
        return attrGroupRelationDao.findByAttrId(attrId)
    }

    suspend fun deleteByAttrIds(attrIds: List<Long>) {
        attrGroupRelationDao.deleteAllByAttrId(attrIds)
    }

    suspend fun deleteAttrAndGroupRelation(attrIds: Long, attrGroupIds: Long) {
        attrGroupRelationDao.deleteByAttrIdAndAttrGroupId(attrIds, attrGroupIds)
    }

    suspend fun countAttr(attrId: Long): Long {
        return attrGroupRelationDao.countByAttrId(attrId)
    }
}

