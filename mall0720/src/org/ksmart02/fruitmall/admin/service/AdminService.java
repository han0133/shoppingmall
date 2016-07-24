package org.ksmart02.fruitmall.admin.service;

import org.ksmart02.fruitmall.admin.model.AdminDao;

public class AdminService {

	AdminDao admindao;

	public void levelModifyForm() throws Exception{
		admindao = new AdminDao();
		admindao.levelModifyForm();
	}
	
}
