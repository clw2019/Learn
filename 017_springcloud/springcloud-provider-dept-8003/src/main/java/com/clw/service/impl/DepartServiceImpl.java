package com.clw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clw.entity.Depart;
import com.clw.mapper.DepartMapper;
import com.clw.service.IDepartService;
import org.springframework.stereotype.Service;

@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements IDepartService {

}
