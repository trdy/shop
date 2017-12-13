package com.yt.shop.dao;

import com.yt.shop.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionJpa extends JpaRepository<Permission,Long> {

    @Query(value = "select * from permission p where p.user_type_id=?1",nativeQuery = true)
    public List<Permission> findPermissionByUserTypeId(Long userTypeId) ;

    @Query(value = "delete from permission where user_type_id=?1 and menu_id=?2",nativeQuery = true)
    public void deletePermission(String userTypeId, String menuId) ;

    @Query(value = "insert into permission(user_type_id,menu_id) values(?1,?2)",nativeQuery = true)
    public void insertPermission(String userTypeId, String menuId);

    @Query(value = "from Permission p where p.userType.userTypeId=?1 and p.menu.menuId=?2")
    public Permission findPermissionByUserTypeIdAndMenuId(int userTypeId, int menuId);

}
