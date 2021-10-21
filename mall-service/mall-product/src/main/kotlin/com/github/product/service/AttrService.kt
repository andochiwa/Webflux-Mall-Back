package com.github.product.service

import com.github.product.dao.AttrDao
import com.github.product.dto.AttrDto
import com.github.product.entity.Attr
import com.github.product.entity.AttrGroupRelation
import com.github.product.vo.AttrVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.beans.BeanUtils
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
class AttrService {
    @Autowired
    lateinit var attrDao: AttrDao

    @Autowired
    lateinit var attrGroupService: AttrGroupService

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var attrGroupRelationService: AttrGroupRelationService

    suspend fun getById(id: Long): AttrDto? {
        val attrDto = AttrDto()
        val attr = attrDao.findById(id) ?: throw NullPointerException("查询为空")
        BeanUtils.copyProperties(attr, attrDto)
        attrDto.attrGroupId = attrGroupRelationService.getGroupByAttrId(id)?.attrGroupId
        attrDto.catelogName = categoryService.getById(attr.catelogId!!)?.name
        attrDto.catelogPath = categoryService.getCatelogPath(attr.catelogId!!)
        return attrDto
    }

    suspend fun deleteById(id: Long) {
        attrDao.deleteById(id)
    }

    fun getAll(): Flow<Attr> {
        return attrDao.findAll()
    }

    fun getAllById(attrIds: List<Long>): Flow<Attr> {
        return attrDao.findAllById(attrIds)
    }

    suspend fun getBaseAttrList(
        attrType: Int,
        catelogId: Long,
        page: Int,
        limit: Int,
        key: String?
    ): MutableMap<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        lateinit var attrList: List<Attr>
        var count = 0L
        if (catelogId == 0L) {
            key?.run {
                attrList = attrDao.findByAttrTypeAndAttrNameContaining(attrType, key, pageRequest).toList()
                count = attrDao.countByAttrTypeAndAttrNameContaining(attrType, key)
            } ?: run {
                attrList = attrDao.findByAttrType(attrType, pageRequest).toList()
                count = attrDao.countByAttrType(attrType)
            }
        } else {
            key?.run {
                attrList = attrDao.findByAttrTypeAndCatelogIdAndAttrNameContaining(
                    attrType, catelogId, key, pageRequest
                ).toList()
                count = attrDao.countByAttrTypeAndCatelogIdAndAttrNameContaining(attrType, catelogId, key)
            } ?: run {
                attrList = attrDao.findByAttrTypeAndCatelogId(attrType, catelogId, pageRequest).toList()
                count = attrDao.countByAttrTypeAndCatelogId(attrType, catelogId)
            }
        }
        // todo: optimize
        val attrDtoList = attrList.map {
            val attrDto = AttrDto()
            BeanUtils.copyProperties(it, attrDto)
            // set groupName and catelogName
            val groupId = attrGroupRelationService.getGroupByAttrId(it.id!!)?.attrGroupId
            attrDto.groupName = groupId?.run { attrGroupService.getById(groupId)?.attrGroupName }
            attrDto.catelogName = categoryService.getById(it.catelogId!!)?.name
            attrDto
        }
        return mutableMapOf<String, Any>().apply {
            this["list"] = attrDtoList
            this["totalCount"] = count
        }
    }

    @Transactional
    suspend fun save(attrVo: AttrVo) {
        val attr = Attr()
        BeanUtils.copyProperties(attrVo, attr)
        attrDao.save(attr)
        // save to attr group relation
        val attrGroupRelation = AttrGroupRelation(attrId = attr.id!!, attrGroupId = attrVo.attrGroupId!!, attrSort = 0)
        attrGroupRelationService.saveOrUpdate(attrGroupRelation)
    }

    @Transactional
    suspend fun update(attrVo: AttrVo) {
        val attr = Attr().apply { attrId = attrVo.id }
        BeanUtils.copyProperties(attrVo, attr)
        attrDao.save(attr)
        attrGroupRelationService.updateGroup(attrVo.attrGroupId!!, attr.id!!)
    }
}

