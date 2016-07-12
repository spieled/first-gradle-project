package com.mgj.core.user;

import com.mgj.base.socialinsurance.Profile;
import com.mgj.core.base.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yanqu on 2016/7/7.
 */
interface ProfileDao extends BaseDao<Profile, Long> {
    @Modifying
    @Query("update Profile as p set p.avatar=?2 where p.username=?1")
    void updateAvatarByUsername(String username, String avatar);

    @Modifying
    @Query("update Profile as p set p.idPositive=?2, p.idNegtive=?3 where p.username=?1")
    void updateIdPic(String username, String idPositive, String idNegtive);
}
