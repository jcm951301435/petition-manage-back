package com.ssy.petition.util;

import com.ssy.petition.common.MapperOperateType;
import com.ssy.petition.config.entity.SysUserDetails;
import com.ssy.petition.entity.base.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

/**
 * baseEntity 操作工具类
 *
 * @author jcm
 */
public class EntityUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityUtils.class);

    public static long generateId() {
        return SnowFlakeUtils.uniqueLong();
    }

    /**
     * 初始化
     *
     * @param entity baseEntity
     * @param type   操作类型
     * @return 处理结果
     */
    private static boolean initEntity(BaseEntity entity, MapperOperateType type) {
        boolean initFlag = true;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
            Long currentUserId = sysUserDetails.getUser().getId();
            Date now = DateUtils.now();
            switch (type) {
                case INSERT:
                    entity.setId(generateId());
                    entity.setInsertOn(now);
                    entity.setInsertBy(currentUserId);
                    entity.setDeleteFlag(false);
                    break;
                case UPDATE:
                    entity.setUpdateOn(now);
                    entity.setUpdateBy(currentUserId);
                    break;
                case DELETE:
                    entity.setDeleteOn(now);
                    entity.setDeleteBy(currentUserId);
                    entity.setDeleteFlag(true);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.warn("初始化失败，错误信息：{}", e.getMessage());
            initFlag = false;
        }
        return initFlag;
    }

    /**
     * 初始化新增
     *
     * @param entity baseEntity
     * @return 处理结果
     */
    public static boolean initInsertEntity(BaseEntity entity) {
        return initEntity(entity, MapperOperateType.INSERT);
    }

    /**
     * 初始化修改
     *
     * @param entity baseEntity
     * @return 处理结果
     */
    public static boolean initUpdateEntity(BaseEntity entity) {
        return initEntity(entity, MapperOperateType.UPDATE);
    }

    /**
     * 初始化删除
     *
     * @param entity baseEntity
     * @return 处理结果
     */
    public static boolean initDeleteEntity(BaseEntity entity) {
        return initEntity(entity, MapperOperateType.DELETE);
    }

}
