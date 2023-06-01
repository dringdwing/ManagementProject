package com.yizhi.student.service.impl;

import com.yizhi.common.utils.ShiroUtils;
import com.yizhi.common.utils.weixin.WeiXinJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhi.student.dao.StudentInfoDao;
import com.yizhi.student.domain.StudentInfoDO;
import com.yizhi.student.service.StudentInfoService;


@Service
public class StudentInfoServiceImpl implements StudentInfoService {


    @Autowired
    private StudentInfoDao studentInfoDao;

    @Override
    public StudentInfoDO get(Integer id) {
        System.out.println("======service层中传递过来的id参数是：" + id + "======");
        return studentInfoDao.get(id);
    }


    @Override
    public List<StudentInfoDO> list(Map<String, Object> map) {
        return studentInfoDao.list(map);
    }

    //"===================================================================================="


    @Override
    public int count(Map<String, Object> map) {
        return studentInfoDao.count(map);
    }

    @Override
    public int save(StudentInfoDO studentInfo) {
        LocalDateTime rightNow = LocalDateTime.now();
        String result3 = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date date = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            date = sf.parse(result3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        studentInfo.setAddTime(date);
        Long userId = ShiroUtils.getUserId();
        studentInfo.setAddUserid(Integer.parseInt(String.valueOf(userId)));
        return studentInfoDao.save(studentInfo);
    }

    @Override
    public int update(StudentInfoDO studentInfo) {
        LocalDateTime rightNow = LocalDateTime.now();
        String result3 = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date date = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            date = sf.parse(result3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        studentInfo.setEditTime(date);
        Long userId = ShiroUtils.getUserId();
        studentInfo.setEditUserid(Integer.parseInt(String.valueOf(userId)));
        return studentInfoDao.update(studentInfo);
    }

    @Override
    public int remove(Integer id) {
        return studentInfoDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return studentInfoDao.batchRemove(ids);
    }

}
