package com.github.product.service

import com.github.product.dao.AttrGroupRelationDao
import com.github.product.entity.Attr
import com.github.product.entity.AttrGroupRelation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
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

    suspend fun getAttrByAttrGroupId(attrGroupId: Long): List<Attr> {
        val attrGroupRelationList = attrGroupRelationDao.getAllByAttrGroupId(attrGroupId).toList()
        val attrIds = attrGroupRelationList.map { it.attrId!! }
        return attrService.getAllById(attrIds).toList()
    }
}

