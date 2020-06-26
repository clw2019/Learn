package com.clw.sys.service.impl;

import com.clw.sys.domain.Student;
import com.clw.sys.mapper.StudentMapper;
import com.clw.sys.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author clw
 * @since 2020-06-26
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
